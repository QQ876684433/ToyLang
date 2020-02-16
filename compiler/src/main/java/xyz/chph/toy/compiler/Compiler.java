package xyz.chph.toy.compiler;

import org.apache.commons.cli.*;
import xyz.chph.toy.domain.ClassDeclaration;
import xyz.chph.toy.domain.CompilationUnit;
import xyz.chph.toy.bytecodegenerator.BytecodeGenerator;
import xyz.chph.toy.domain.Function;
import xyz.chph.toy.domain.ImportDeclaration;
import xyz.chph.toy.domain.node.expression.Parameter;
import xyz.chph.toy.domain.node.statement.Block;
import xyz.chph.toy.domain.node.statement.Statement;
import xyz.chph.toy.domain.scope.Field;
import xyz.chph.toy.domain.scope.FunctionSignature;
import xyz.chph.toy.domain.type.ClassType;
import xyz.chph.toy.domain.type.Type;
import xyz.chph.toy.parsing.Parser;
import xyz.chph.toy.validation.ARGUMENT_ERRORS;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Compiler {
    private Path source;
    private Path destination;
    private static final String TOY_SUFFIX = ".toy";

    private Compiler(Path source, Path destination) {
        this.source = source;
        this.destination = destination;
    }

    public static void main(String[] args) {
        // 创建选项集
        Options options = new Options();
        options.addOption(
                Option.builder("d")
                        .longOpt("dest")
                        .hasArg()
                        .argName("destination")
                        .desc("the output directory of the class files, " +
                                "\notherwise the root directory of project as default.")
                        .build()
        );
        options.addOption(
                Option.builder("s")
                        .longOpt("src")
                        .hasArg()
                        .argName("source")
                        .desc("the root directory of your project, " +
                                "\notherwise the workspace directory as default.")
                        .build()
        );
        options.addOption(Option.builder("h")
                .longOpt("help")
                .desc("show this help message and exit program")
                .build());

        // 解析器
        CommandLineParser parser = new DefaultParser();
        // 格式器
        HelpFormatter formatter = new HelpFormatter();
        // 解析结果
        CommandLine result = null;

        try {
            // 尝试解析命令行参数
            result = parser.parse(options, args);
        } catch (ParseException e) {
            // 打印解析异常
            System.err.println(e.getMessage());
            // 打印帮助信息
            formatter.printHelp("Compiler", options, true);
            // 退出程序，退出码为 1
            System.exit(1);
        }

        // 如果存在 -h --help 参数
        if (result.hasOption("h")) {
            formatter.printHelp("Compiler", options, true);
            System.exit(0);
        }

        // 处理编译参数
        Path destination = Paths.get(".");   // 默认目标路径
        Path source = Paths.get(".");    // 默认项目路径
        if (result.hasOption("s")) {
            source = Paths.get(result.getOptionValue("s"));
            if (!Files.exists(source)) {
                System.err.println("the directory of project \"" + result.getOptionValue("s") + "\" doesn't exist!");
                System.exit(1);
            }
        }
        if (result.hasOption("d")) {
            destination = Paths.get(result.getOptionValue("d"));
        } else {
            destination = Paths.get(source.toUri());
        }
        System.out.println("source: " + source.toString());
        System.out.println("destination: " + destination.toString());

        // 开始编译项目
        try {
            new Compiler(source, destination).compile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void compile() throws Exception {
        Queue<File> paths = new LinkedList<>();
        List<File> toyFiles = new ArrayList<>();
        paths.offer(this.source.toFile());
        while (!paths.isEmpty()) {
            File cur = paths.remove();
            for (File file : cur.listFiles()) {
                if (file.isDirectory()) paths.offer(file);
                else if (file.toString().endsWith(TOY_SUFFIX)) toyFiles.add(file);
            }
        }
        if (!Files.exists(this.destination)) {
            Files.createDirectory(this.destination);
        }

        Map<String, CompilationUnit> compilationUnitMap = new HashMap<>();
        for (final File toyFile : toyFiles) {
            String fileAbsolutePath = toyFile.getAbsolutePath();
            Path projectPath = Paths.get(this.source.toUri());
            Path toyFilePath = Paths.get(fileAbsolutePath).getParent();
            String module = projectPath.relativize(toyFilePath).toString().replace("/", ".");
            final CompilationUnit compilationUnit = new Parser().getCompilationUnit(fileAbsolutePath);
            compilationUnitMap.put(module, compilationUnit);
        }
        symbolBackFill(compilationUnitMap);
        compilationUnitMap.forEach((module, compilationUnit) -> {
            try {
                saveBytecodeToClassFile(module, compilationUnit);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        });
    }

    /**
     * 符号回填
     *
     * @param compilationUnitMap String: 当前编译单元的模块名，以.作为分隔符
     *                           CompilationUnit: 编译单元，即一个toy文件
     */
    private void symbolBackFill(Map<String, CompilationUnit> compilationUnitMap) {
        Map<String, List<Function>> interfaces = new HashMap<>();
        // 获取所有类的方法声明
        for (Map.Entry<String, CompilationUnit> entry : compilationUnitMap.entrySet()) {
            CompilationUnit compilationUnit = entry.getValue();
            ClassDeclaration classDeclaration = compilationUnit.getClassDeclaration();
            String className = entry.getKey() + "." + classDeclaration.getName();
            interfaces.put(className, classDeclaration.getMethods());
        }
        // 开始回填
        for (Map.Entry<String, CompilationUnit> entry : compilationUnitMap.entrySet()) {
            ClassDeclaration classDeclaration = entry.getValue().getClassDeclaration();
            ImportDeclaration importDeclaration = entry.getValue().getImportDeclaration();

            // 回填类的模块部分
            classDeclaration.setModule(entry.getKey());

            // 回填域部分
            List<Field> fields = classDeclaration.getFields();
            fields.forEach(field -> {
                if (field.getType() instanceof ClassType) {
                    Optional<String> interfaceName = importDeclaration.get(field.getType().getName());
                    interfaceName.ifPresent(field::setType);
                }
                field.setOwner(classDeclaration.getQualifiedName());
            });

            // 回填方法部分
            List<Function> methods = classDeclaration.getMethods();
            methods.forEach(method -> {
                List<Parameter> parameters = method.getParameters();
                Type returnType = method.getReturnType();
                Block block = (Block) method.getRootStatement();
                // 回填参数类型
                parameters.forEach(parameter -> {
                    if (parameter.getType() instanceof ClassType){
                        Optional<String> interfaceName = importDeclaration.get(parameter.getType().getName());
                        interfaceName.ifPresent(parameter::setType);
                    }
                });
                // 回填返回值类型
                if (returnType instanceof ClassType) {
                    Optional<String> interfaceName = importDeclaration.get(returnType.getName());
                    interfaceName.ifPresent(((ClassType) returnType)::setName);
                }
                // 回填函数体中的类型
                block.getStatements().forEach(statement -> {

                });
            });
        }
    }

    private void saveBytecodeToClassFile(String module, CompilationUnit compilationUnit) throws IOException {
        BytecodeGenerator bytecodeGenerator = new BytecodeGenerator();
        final byte[] byteCode = bytecodeGenerator.generate(compilationUnit);
        String className = compilationUnit.getClassName();
        String fileName = className + ".class";
        Path output = Paths.get(this.destination.toString(), module, fileName);
        OutputStream os = new FileOutputStream(output.toFile());
        IOUtils.write(byteCode, os);
    }
}
