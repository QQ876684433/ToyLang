package xyz.chph.toy.bytecodegenerator.expression;

import xyz.chph.toy.domain.node.expression.Value;
import xyz.chph.toy.domain.type.Type;
import xyz.chph.toy.utils.TypeResolver;
import org.objectweb.asm.MethodVisitor;

public class ValueExpressionGenerator {
    private final MethodVisitor methodVisitor;
    public ValueExpressionGenerator(MethodVisitor methodVisitor) {
        this.methodVisitor = methodVisitor;
    }
    public void generate(Value value) {
        Type type = value.getType();
        String stringValue = value.getValue();
        Object transformedValue = TypeResolver.getValueFromString(stringValue, type);
        methodVisitor.visitLdcInsn(transformedValue);
    }
}
