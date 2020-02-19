package xyz.chph.toy.bytecodegenerator.statement;

import xyz.chph.toy.bytecodegenerator.expression.ExpressionGenerator;
import xyz.chph.toy.domain.node.expression.Expression;
import xyz.chph.toy.domain.node.statement.PrintStatement;
import xyz.chph.toy.domain.type.ClassType;
import xyz.chph.toy.domain.type.Type;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class PrintStatementGenerator {
    private final MethodVisitor methodVisitor;
    private final ExpressionGenerator expressionGenerator;

    public PrintStatementGenerator(ExpressionGenerator expressionGenerator, MethodVisitor methodVisitor) {
        this.methodVisitor = methodVisitor;
        this.expressionGenerator = expressionGenerator;
    }

    public void generate(PrintStatement printStatement) {
        Expression expression = printStatement.getExpression();
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        expression.accept(expressionGenerator);
        Type type = expression.getType();
        String descriptor = "(" + type.getDescriptor() + ")V";
        ClassType owner = new ClassType("java.io.PrintStream");
        String fieldDescriptor = owner.getDescriptor();
        // add support for println
        String methodName = printStatement.getType() == PrintStatement.TYPE_PRINT ? "print" : "println";
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, fieldDescriptor, methodName, descriptor, false);
    }
}
