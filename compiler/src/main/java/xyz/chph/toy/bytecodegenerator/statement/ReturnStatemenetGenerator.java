package xyz.chph.toy.bytecodegenerator.statement;

import xyz.chph.toy.bytecodegenerator.expression.ExpressionGenerator;
import xyz.chph.toy.domain.node.expression.Expression;
import xyz.chph.toy.domain.node.statement.ReturnStatement;
import xyz.chph.toy.domain.type.Type;
import org.objectweb.asm.MethodVisitor;

public class ReturnStatemenetGenerator {
    private final ExpressionGenerator expressionGenerator;
    private final MethodVisitor methodVisitor;
    public ReturnStatemenetGenerator(ExpressionGenerator expressionGenerator, MethodVisitor methodVisitor) {
        this.expressionGenerator = expressionGenerator;
        this.methodVisitor = methodVisitor;
    }
    public void generate(ReturnStatement returnStatement) {
        Expression expression = returnStatement.getExpression();
        Type type = expression.getType();
        expression.accept(expressionGenerator);
        methodVisitor.visitInsn(type.getReturnOpcode());
    }
}
