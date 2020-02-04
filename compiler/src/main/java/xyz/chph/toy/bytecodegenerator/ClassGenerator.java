package xyz.chph.toy.bytecodegenerator;

import xyz.chph.toy.domain.Function;
import xyz.chph.toy.domain.ClassDeclaration;
import xyz.chph.toy.domain.scope.Field;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.util.Collection;
import java.util.List;

public class ClassGenerator {
    private ClassWriter classWriter;

    public ClassGenerator(ClassWriter classWriter) {
        this.classWriter = classWriter;
    }

    public void generate(ClassDeclaration classDeclaration) {
        List<Function> methods = classDeclaration.getMethods();
        Collection<Field> fields = classDeclaration.getFields();
        FieldGenerator fieldGenerator = new FieldGenerator(classWriter);
        fields.forEach(f -> f.accept(fieldGenerator));
        MethodGenerator methodGenerator = new MethodGenerator(classWriter);
        methods.forEach(f -> f.accept(methodGenerator));
    }
}
