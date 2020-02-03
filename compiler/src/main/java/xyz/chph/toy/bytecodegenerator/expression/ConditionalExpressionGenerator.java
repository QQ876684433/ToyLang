package xyz.chph.toy.bytecodegenerator.expression;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import xyz.chph.toy.domain.CompareSign;
import xyz.chph.toy.domain.node.expression.*;
import xyz.chph.toy.domain.scope.FunctionSignature;
import xyz.chph.toy.domain.type.BuiltInType;
import xyz.chph.toy.domain.type.ClassType;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ConditionalExpressionGenerator {
    private final ExpressionGenerator expressionGenerator;
    private final MethodVisitor methodVisitor;
    public ConditionalExpressionGenerator(ExpressionGenerator expressionGenerator, MethodVisitor methodVisitor) {
        this.expressionGenerator = expressionGenerator;
        this.methodVisitor = methodVisitor;
    }
    public void generate(ConditionalExpression conditionalExpression) {
        Expression leftExpression = conditionalExpression.getLeftExpression();
        Expression rightExpression = conditionalExpression.getRightExpression();
        CompareSign compareSign = conditionalExpression.getCompareSign();
        if (conditionalExpression.isPrimitiveComparison()) {
            generatePrimitivesComparison(leftExpression, rightExpression, compareSign);
        } else {
            generateObjectsComparison(leftExpression, rightExpression, compareSign);
        }
        Label endLabel = new Label();
        Label trueLabel = new Label();
        methodVisitor.visitJumpInsn(compareSign.getOpcode(), trueLabel);
        methodVisitor.visitInsn(Opcodes.ICONST_0);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, endLabel);
        methodVisitor.visitLabel(trueLabel);
        methodVisitor.visitInsn(Opcodes.ICONST_1);
        methodVisitor.visitLabel(endLabel);
    }

    private void generateObjectsComparison(Expression leftExpression, Expression rightExpression, CompareSign compareSign) {
        Parameter parameter = new Parameter("o", new ClassType("java.lang.Object"), Optional.empty());
        List<Parameter> parameters = Collections.singletonList(parameter);
        Argument argument = new Argument(rightExpression, Optional.empty());
        List<Argument> arguments = Collections.singletonList(argument);
        switch (compareSign) {
            case EQUAL:
            case NOT_EQUAL:
                FunctionSignature equalsSignature = new FunctionSignature("equals", parameters, BuiltInType.BOOLEAN);
                FunctionCall equalsCall = new FunctionCall(equalsSignature, arguments, leftExpression);
                equalsCall.accept(expressionGenerator);
                methodVisitor.visitInsn(Opcodes.ICONST_1);
                methodVisitor.visitInsn(Opcodes.IXOR);
                break;
            case LESS:
            case GREATER:
            case LESS_OR_EQUAL:
            case GRATER_OR_EQAL:
                FunctionSignature compareToSignature = new FunctionSignature("compareTo", parameters, BuiltInType.INT);
                FunctionCall compareToCall = new FunctionCall(compareToSignature, arguments, leftExpression);
                compareToCall.accept(expressionGenerator);
                break;
        }
    }

    private void generatePrimitivesComparison(Expression leftExpression, Expression rightExpression, CompareSign compareSign) {
        leftExpression.accept(expressionGenerator);
        rightExpression.accept(expressionGenerator);
        methodVisitor.visitInsn(Opcodes.ISUB);
    }

}
