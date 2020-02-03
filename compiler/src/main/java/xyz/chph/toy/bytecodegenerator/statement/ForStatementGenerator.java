package xyz.chph.toy.bytecodegenerator.statement;

import xyz.chph.toy.bytecodegenerator.expression.ExpressionGenerator;
import xyz.chph.toy.domain.CompareSign;
import xyz.chph.toy.domain.node.expression.ConditionalExpression;
import xyz.chph.toy.domain.node.expression.Expression;
import xyz.chph.toy.domain.node.expression.LocalVariableReference;
import xyz.chph.toy.domain.node.statement.RangedForStatement;
import xyz.chph.toy.domain.node.statement.Statement;
import xyz.chph.toy.domain.scope.LocalVariable;
import xyz.chph.toy.domain.scope.Scope;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ForStatementGenerator {
    private final MethodVisitor methodVisitor;
    public ForStatementGenerator(MethodVisitor methodVisitor) {
        this.methodVisitor = methodVisitor;
    }
    public void generate(RangedForStatement rangedForStatement) {
        Scope newScope = rangedForStatement.getScope();
        StatementGenerator scopeGeneratorWithNewScope = new StatementGenerator(methodVisitor, newScope);
        ExpressionGenerator exprGeneratorWithNewScope = new ExpressionGenerator(methodVisitor, newScope);
        Statement iterator = rangedForStatement.getIteratorVariableStatement();
        Label incrementationSection = new Label();
        Label decrementationSection = new Label();
        Label endLoopSection = new Label();
        String iteratorVarName = rangedForStatement.getIteratorVarName();
        Expression endExpression = rangedForStatement.getEndExpression();
        LocalVariable variable = new LocalVariable(iteratorVarName,rangedForStatement.getType());
        Expression iteratorVariable = new LocalVariableReference(variable);
        ConditionalExpression iteratorGreaterThanEndConditional = new ConditionalExpression(iteratorVariable, endExpression, CompareSign.GREATER);
        ConditionalExpression iteratorLessThanEndConditional = new ConditionalExpression(iteratorVariable, endExpression, CompareSign.LESS);
        iterator.accept(scopeGeneratorWithNewScope);
        iteratorLessThanEndConditional.accept(exprGeneratorWithNewScope);
        methodVisitor.visitJumpInsn(Opcodes.IFNE, incrementationSection);
        iteratorGreaterThanEndConditional.accept(exprGeneratorWithNewScope);
        methodVisitor.visitJumpInsn(Opcodes.IFNE, decrementationSection);
        methodVisitor.visitLabel(incrementationSection);
        rangedForStatement.getStatement().accept(scopeGeneratorWithNewScope);
        methodVisitor.visitIincInsn(newScope.getLocalVariableIndex(iteratorVarName), 1);
        iteratorGreaterThanEndConditional.accept(exprGeneratorWithNewScope);
        methodVisitor.visitJumpInsn(Opcodes.IFEQ, incrementationSection);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, endLoopSection);
        methodVisitor.visitLabel(decrementationSection);
        rangedForStatement.getStatement().accept(scopeGeneratorWithNewScope);
        methodVisitor.visitIincInsn(newScope.getLocalVariableIndex(iteratorVarName), -1);
        iteratorLessThanEndConditional.accept(exprGeneratorWithNewScope);
        methodVisitor.visitJumpInsn(Opcodes.IFEQ, decrementationSection);
        methodVisitor.visitLabel(endLoopSection);
    }
}
