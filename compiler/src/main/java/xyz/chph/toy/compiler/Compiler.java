package xyz.chph.toy.compiler;

import xyz.chph.toy.domain.CompilationUnit;
import xyz.chph.toy.bytecodegenerator.BytecodeGenerator;
import xyz.chph.toy.parsing.Parser;
import xyz.chph.toy.validation.ARGUMENT_ERRORS;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Compiler {

    public static void main(String[] args) {
        try {
            new Compiler().compile(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void compile(String[] args) throws Exception {
        final ARGUMENT_ERRORS argumentsErrors = getArgumentValidationErrors(args);
        if (argumentsErrors != ARGUMENT_ERRORS.NONE) {
            System.out.println(argumentsErrors.getMessage());
            return;
        }
        final File toyFile = new File(args[0]);
        String fileAbsolutePath = toyFile.getAbsolutePath();
        final CompilationUnit compilationUnit = new Parser().getCompilationUnit(fileAbsolutePath);
        saveBytecodeToClassFile(compilationUnit);
    }

    private ARGUMENT_ERRORS getArgumentValidationErrors(String[] args) {
        if (args.length != 1) {
            return ARGUMENT_ERRORS.NO_FILE;
        }
        String filePath = args[0];
        if (!filePath.endsWith(".toy")) {
            return ARGUMENT_ERRORS.BAD_FILE_EXTENSION;
        }
        return ARGUMENT_ERRORS.NONE;
    }

    private static void saveBytecodeToClassFile(CompilationUnit compilationUnit) throws IOException {
        BytecodeGenerator bytecodeGenerator = new BytecodeGenerator();
        final byte[] byteCode = bytecodeGenerator.generate(compilationUnit);

        String moduleName = compilationUnit.getModuleName().replace(".", File.separator);
        String outPath = "build" + File.separator + moduleName + File.separator;
        File path = new File(outPath);
        if (!path.exists()) {
            path.mkdirs();
        }

        String className = compilationUnit.getClassName();
        String fileName = "build/" + className.replace('.', '/') + ".class";
        OutputStream os = new FileOutputStream(fileName);
        IOUtils.write(byteCode, os);
    }
}
