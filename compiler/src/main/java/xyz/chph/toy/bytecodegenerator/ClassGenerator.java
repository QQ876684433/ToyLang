package xyz.chph.toy.bytecodegenerator;

import xyz.chph.toy.domain.Function;
import xyz.chph.toy.domain.ClassDeclaration;
import xyz.chph.toy.domain.scope.Field;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.util.Collection;
import java.util.List;

import static org.objectweb.asm.Opcodes.V1_8;

public class ClassGenerator {
    private static final int CLASS_VERSION = V1_8;
    private ClassWriter classWriter;

    public ClassGenerator() {
        classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES + ClassWriter.COMPUTE_MAXS);
    }

    public ClassWriter generate(ClassDeclaration classDeclaration) {
        String name = classDeclaration.getName();
        classWriter.visit(
                CLASS_VERSION,
                Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER,
                name,
                null,
                "java/lang/Object",
                null
        );
        List<Function> methods = classDeclaration.getMethods();
        Collection<Field> fields = classDeclaration.getFields();
        FieldGenerator fieldGenerator = new FieldGenerator(classWriter);
        fields.forEach(f -> f.accept(fieldGenerator));
        MethodGenerator methodGenerator = new MethodGenerator(classWriter);
        methods.forEach(f -> f.accept(methodGenerator));
        classWriter.visitEnd();
        return classWriter;
    }
}
