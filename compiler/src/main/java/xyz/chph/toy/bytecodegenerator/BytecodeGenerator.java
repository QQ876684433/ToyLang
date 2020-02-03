package xyz.chph.toy.bytecodegenerator;

import xyz.chph.toy.domain.ClassDeclaration;
import xyz.chph.toy.domain.CompilationUnit;

public class BytecodeGenerator {
    public byte[] generate(CompilationUnit compilationUnit) {
        ClassDeclaration classDeclaration = compilationUnit.getClassDeclaration();
        ClassGenerator classGenerator = new ClassGenerator();
        return classGenerator.generate(classDeclaration).toByteArray();
    }
}
