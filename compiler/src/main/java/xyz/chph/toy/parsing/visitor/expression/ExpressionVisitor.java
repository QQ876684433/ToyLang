package xyz.chph.toy.parsing.visitor.expression;

import org.antlr.v4.runtime.misc.NotNull;
import xyz.chph.toy.antlr.ToyBaseVisitor;
import xyz.chph.toy.antlr.ToyParser;
import xyz.chph.toy.domain.node.expression.ConditionalExpression;
import xyz.chph.toy.domain.node.expression.Expression;
import xyz.chph.toy.domain.scope.Scope;
import xyz.chph.toy.parsing.visitor.expression.function.CallExpressionVisitor;

public class ExpressionVisitor extends ToyBaseVisitor<Expression> {
    private final ArithmeticExpressionVisitor arithmeticExpressionVisitor;
    private final VariableReferenceExpressionVisitor variableReferenceExpressionVisitor;
    private final ValueExpressionVisitor valueExpressionVisitor;
    private final CallExpressionVisitor callExpressionVisitor;
    private final ConditionalExpressionVisitor conditionalExpressionVisitor;
    public ExpressionVisitor(Scope scope) {
        arithmeticExpressionVisitor = new ArithmeticExpressionVisitor(this);
        variableReferenceExpressionVisitor = new VariableReferenceExpressionVisitor(scope);
        valueExpressionVisitor = new ValueExpressionVisitor();
        callExpressionVisitor = new CallExpressionVisitor(this, scope);
        conditionalExpressionVisitor = new ConditionalExpressionVisitor(this);
    }
    @Override
    public Expression visitVarReference(@NotNull ToyParser.VarReferenceContext ctx) {
        return variableReferenceExpressionVisitor.visitVarReference(ctx);
    }
    @Override
    public Expression visitLiteral(@NotNull ToyParser.LiteralContext ctx) {
        return valueExpressionVisitor.visitLiteral(ctx);
    }
    @Override
    public Expression visitFunctionCall(@NotNull ToyParser.FunctionCallContext ctx) {
        return callExpressionVisitor.visitFunctionCall(ctx);
    }
    @Override
    public Expression visitConstructorCall(@NotNull ToyParser.ConstructorCallContext ctx) {
        return callExpressionVisitor.visitConstructorCall(ctx);
    }
    @Override
    public Expression visitSupercall(@NotNull ToyParser.SupercallContext ctx) {
        return callExpressionVisitor.visitSupercall(ctx);
    }
    @Override
    public Expression visitAdd(@NotNull ToyParser.AddContext ctx) {
        return arithmeticExpressionVisitor.visitAdd(ctx);
    }
    @Override
    public Expression visitMultiply(@NotNull ToyParser.MultiplyContext ctx) {
        return arithmeticExpressionVisitor.visitMultiply(ctx);
    }
    @Override
    public Expression visitSubstract(@NotNull ToyParser.SubstractContext ctx) {
        return arithmeticExpressionVisitor.visitSubstract(ctx);
    }
    @Override
    public Expression visitDivide(@NotNull ToyParser.DivideContext ctx) {
        return arithmeticExpressionVisitor.visitDivide(ctx);
    }
    @Override
    public ConditionalExpression visitConditionalExpression(@NotNull ToyParser.ConditionalExpressionContext ctx) {
        return conditionalExpressionVisitor.visitConditionalExpression(ctx);
    }
}
