package xyz.chph.toy.bytecodegenerator.expression;

import xyz.chph.toy.domain.node.expression.Parameter;
import xyz.chph.toy.domain.scope.Scope;
import xyz.chph.toy.domain.type.Type;
import org.objectweb.asm.MethodVisitor;

public class ParameterExpressionGenerator {
    private final MethodVisitor methodVisitor;
    private final Scope scope;
    public ParameterExpressionGenerator(MethodVisitor methodVisitor, Scope scope) {
        this.methodVisitor = methodVisitor;
        this.scope = scope;
    }
    public void generate(Parameter parameter) {
        Type type = parameter.getType();
        int index = scope.getLocalVariableIndex(parameter.getName());
        methodVisitor.visitVarInsn(type.getLoadVariableOpcode(), index);
    }
}
