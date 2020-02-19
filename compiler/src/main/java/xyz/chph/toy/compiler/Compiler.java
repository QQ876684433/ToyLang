package xyz.chph.toy.compiler;

import org.apache.commons.cli.*;
import xyz.chph.toy.domain.CompilationUnit;
import xyz.chph.toy.bytecodegenerator.BytecodeGenerator;
import xyz.chph.toy.exception.TargetModuleNotDeclaredException;
import xyz.chph.toy.parsing.Parser;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Compiler {
    private Path source;
    private Path destination;
    private String target;
    private static final String TOY_SUFFIX = ".toy";

    private Compiler(String target, Path source, Path destination) {
        this.target = target;
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
                Option.builder("m")
                        .longOpt("module")
                        .hasArg()
                        .argName("module")
                        .desc("the module declared in ToyMake.json you want to build, " + "" +
                                "\notherwise all the modules will be built.")
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
        String target = "";
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
        if (result.hasOption("m")) {
            target = result.getOptionValue("m");
        }
        // 将编译输出目录添加到classpath
        String key = "java.class.path";
        String classpath = System.getProperty(key);
        System.setProperty(key, destination.toString() + ":" + classpath);

        // 开始编译项目
        try {
            new Compiler(target, source, destination).compile();
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

        ToyMake.Configuration configuration = new ToyMake()
                .getProjectConfiguration(this.source.toString() + "/ToyMake.json");
        Parser parser = new Parser();
        List<String> pipeline = new ArrayList<>();
        if (!this.target.isEmpty()) {
            Optional<ToyMake.Module> target = configuration.getModules()
                    .stream()
                    .filter(module -> module.getModule().equals(this.target))
                    .findFirst();
            if (!target.isPresent()) {
                throw new TargetModuleNotDeclaredException(this.target);
            }
            pipeline.addAll(target.get().getPipeline());
            pipeline.addAll(target.get().getOthers());
        } else {
            configuration.getModules().forEach(
                    module -> {
                        pipeline.addAll(module.getPipeline());
                        pipeline.addAll(module.getOthers());
                    }
            );
        }
        for (String toyFile : pipeline) {
            System.out.println(toyFile);
            String fileAbsolutePath = this.source.toString() + "/" + toyFile;
            String module = extractModule(toyFile);
            final CompilationUnit compilationUnit =
                    parser.getCompilationUnit(module.replace("/", "."), fileAbsolutePath);
            saveBytecodeToClassFile(compilationUnit);
        }
    }

    private void saveBytecodeToClassFile(CompilationUnit compilationUnit) throws IOException {
        BytecodeGenerator bytecodeGenerator = new BytecodeGenerator();
        final byte[] byteCode = bytecodeGenerator.generate(compilationUnit);
        String className = compilationUnit.getClassName();
        String fileName = className + ".class";
        File dir = new File(this.destination.toString() + "/" + extractModule(fileName));
        if (!dir.exists()) dir.mkdirs();
        File file = new File(this.destination.toString() + "/" + fileName);
        OutputStream os = new FileOutputStream(file);
        IOUtils.write(byteCode, os);
    }

    private String extractModule(String toyFile) {
        int index = toyFile.lastIndexOf('/');
        if (index < 0) return "";
        return toyFile.substring(0, index);
    }
}
