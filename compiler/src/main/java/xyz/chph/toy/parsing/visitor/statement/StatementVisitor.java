package xyz.chph.toy.parsing.visitor.statement;

import org.antlr.v4.runtime.misc.NotNull;
import xyz.chph.toy.antlr.ToyBaseVisitor;
import xyz.chph.toy.antlr.ToyParser;
import xyz.chph.toy.domain.node.expression.ConditionalExpression;
import xyz.chph.toy.domain.node.expression.Expression;
import xyz.chph.toy.domain.node.statement.Statement;
import xyz.chph.toy.domain.scope.Scope;
import xyz.chph.toy.parsing.visitor.expression.ExpressionVisitor;

public class StatementVisitor extends ToyBaseVisitor<Statement> {
    private final ExpressionVisitor expressionVisitor;
    private final PrintStatementVisitor printStatementVisitor;
    private final VariableDeclarationStatementVisitor variableDeclarationStatementVisitor;
    private final ReturnStatementVisitor returnStatementVisitor;
    private final BlockStatementVisitor blockStatementVisitor;
    private final IfStatementVisitor ifStatementVisitor;
    private final ForStatementVisitor forStatementVisitor;
    private final AssignmentStatementVisitor assignmentStatementVisitor;

    public StatementVisitor(Scope scope) {
        expressionVisitor = new ExpressionVisitor(scope);
        printStatementVisitor = new PrintStatementVisitor(expressionVisitor);
        variableDeclarationStatementVisitor = new VariableDeclarationStatementVisitor(expressionVisitor, scope);
        returnStatementVisitor = new ReturnStatementVisitor(expressionVisitor);
        blockStatementVisitor = new BlockStatementVisitor(scope);
        ifStatementVisitor = new IfStatementVisitor(this, expressionVisitor);
        forStatementVisitor = new ForStatementVisitor(scope);
        assignmentStatementVisitor = new AssignmentStatementVisitor(expressionVisitor);
    }

    @Override
    public Statement visitPrintStatement(@NotNull ToyParser.PrintStatementContext ctx) {
        return printStatementVisitor.visitPrintStatement(ctx);
    }

    @Override
    public Statement visitVariableDeclaration(@NotNull ToyParser.VariableDeclarationContext ctx) {
        return variableDeclarationStatementVisitor.visitVariableDeclaration(ctx);
    }

    @Override
    public Statement visitReturnVoid(@NotNull ToyParser.ReturnVoidContext ctx) {
        return returnStatementVisitor.visitReturnVoid(ctx);
    }

    @Override
    public Statement visitReturnWithValue(@NotNull ToyParser.ReturnWithValueContext ctx) {
        return returnStatementVisitor.visitReturnWithValue(ctx);
    }

    @Override
    public Statement visitBlock(@NotNull ToyParser.BlockContext ctx) {
        return blockStatementVisitor.visitBlock(ctx);
    }

    @Override
    public Statement visitIfStatement(@NotNull ToyParser.IfStatementContext ctx) {
        return ifStatementVisitor.visitIfStatement(ctx);
    }

    @Override
    public Expression visitVarReference(@NotNull ToyParser.VarReferenceContext ctx) {
        return expressionVisitor.visitVarReference(ctx);
    }

    @Override
    public Expression visitValue(@NotNull ToyParser.ValueContext ctx) {
        return expressionVisitor.visitValue(ctx);
    }

    @Override
    public Expression visitFunctionCall(@NotNull ToyParser.FunctionCallContext ctx) {
        return expressionVisitor.visitFunctionCall(ctx);
    }

    @Override
    public Expression visitConstructorCall(@NotNull ToyParser.ConstructorCallContext ctx) {
        return expressionVisitor.visitConstructorCall(ctx);
    }

    @Override
    public Expression visitSupercall(@NotNull ToyParser.SupercallContext ctx) {
        return expressionVisitor.visitSupercall(ctx);
    }

    @Override
    public Expression visitAdd(@NotNull ToyParser.AddContext ctx) {
        return expressionVisitor.visitAdd(ctx);
    }

    @Override
    public Expression visitMultiply(@NotNull ToyParser.MultiplyContext ctx) {
        return expressionVisitor.visitMultiply(ctx);
    }

    @Override
    public Expression visitSubstract(@NotNull ToyParser.SubstractContext ctx) {
        return expressionVisitor.visitSubstract(ctx);
    }

    @Override
    public Expression visitDivide(@NotNull ToyParser.DivideContext ctx) {
        return expressionVisitor.visitDivide(ctx);
    }

    @Override
    public ConditionalExpression visitConditionalExpression(@NotNull ToyParser.ConditionalExpressionContext ctx) {
        return expressionVisitor.visitConditionalExpression(ctx);
    }

    @Override
    public Statement visitForStatement(@NotNull ToyParser.ForStatementContext ctx) {
        return forStatementVisitor.visitForStatement(ctx);
    }

    @Override
    public Statement visitAssignment(ToyParser.AssignmentContext ctx) {
        return assignmentStatementVisitor.visitAssignment(ctx);
    }
}
