package xyz.chph.toy.bytecodegenerator;

import xyz.chph.toy.domain.scope.Field;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;

public class FieldGenerator {
    private final ClassWriter classWriter;

    public FieldGenerator(ClassWriter classWriter) {
        this.classWriter = classWriter;
    }

    public void generate(Field field) {
        String name = field.getName();
        String descriptor = field.getType().getDescriptor();
        FieldVisitor fieldVisitor = classWriter.visitField(Opcodes.ACC_PUBLIC, name, descriptor, null, null);
        fieldVisitor.visitEnd();
    }
}
