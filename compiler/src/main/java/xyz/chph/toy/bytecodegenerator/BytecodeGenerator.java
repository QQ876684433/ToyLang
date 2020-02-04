package xyz.chph.toy.bytecodegenerator;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import xyz.chph.toy.domain.ClassDeclaration;
import xyz.chph.toy.domain.CompilationUnit;
import xyz.chph.toy.domain.type.ClassType;

public class BytecodeGenerator {
    private static final int CLASS_VERSION = 52;
    private ClassWriter classWriter;

    public BytecodeGenerator() {
        classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES + ClassWriter.COMPUTE_MAXS);
    }

    public byte[] generate(CompilationUnit compilationUnit) {

        String name = compilationUnit.getClassName();   // separated by '.'
        String internalName = new ClassType(name).getInternalName();    // separated by '/'

        classWriter.visit(
                CLASS_VERSION,
                Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER,
                internalName,
                null,
                "java/lang/Object",
                null
        );

        ClassDeclaration classDeclaration = compilationUnit.getClassDeclaration();
        ClassGenerator classGenerator = new ClassGenerator(classWriter);
        classGenerator.generate(classDeclaration);
        classWriter.visitEnd();

        return classWriter.toByteArray();
    }
}
