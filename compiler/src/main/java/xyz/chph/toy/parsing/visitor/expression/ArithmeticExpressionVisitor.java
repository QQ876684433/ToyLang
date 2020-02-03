package xyz.chph.toy.parsing.visitor.expression;

import org.antlr.v4.runtime.misc.NotNull;
import xyz.chph.toy.antlr.ToyBaseVisitor;
import xyz.chph.toy.antlr.ToyParser;
import xyz.chph.toy.domain.node.expression.Expression;
import xyz.chph.toy.domain.node.expression.arthimetic.*;

public class ArithmeticExpressionVisitor extends ToyBaseVisitor<ArthimeticExpression> {
    private final ExpressionVisitor expressionVisitor;
    public ArithmeticExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }
    @Override
    public ArthimeticExpression visitAdd(@NotNull ToyParser.AddContext ctx) {
        ToyParser.ExpressionContext leftExpression = ctx.expression(0);
        ToyParser.ExpressionContext rightExpression = ctx.expression(1);
        Expression leftExpress = leftExpression.accept(expressionVisitor);
        Expression rightExpress = rightExpression.accept(expressionVisitor);
        return new Addition(leftExpress, rightExpress);
    }
    @Override
    public ArthimeticExpression visitMultiply(@NotNull ToyParser.MultiplyContext ctx) {
        ToyParser.ExpressionContext leftExpression = ctx.expression(0);
        ToyParser.ExpressionContext rightExpression = ctx.expression(1);
        Expression leftExpress = leftExpression.accept(expressionVisitor);
        Expression rightExpress = rightExpression.accept(expressionVisitor);
        return new Multiplication(leftExpress, rightExpress);
    }
    @Override
    public ArthimeticExpression visitSubstract(@NotNull ToyParser.SubstractContext ctx) {
        ToyParser.ExpressionContext leftExpression = ctx.expression(0);
        ToyParser.ExpressionContext rightExpression = ctx.expression(1);
        Expression leftExpress = leftExpression.accept(expressionVisitor);
        Expression rightExpress = rightExpression.accept(expressionVisitor);
        return new Substraction(leftExpress, rightExpress);
    }
    @Override
    public ArthimeticExpression visitDivide(@NotNull ToyParser.DivideContext ctx) {
        ToyParser.ExpressionContext leftExpression = ctx.expression(0);
        ToyParser.ExpressionContext rightExpression = ctx.expression(1);
        Expression leftExpress = leftExpression.accept(expressionVisitor);
        Expression rightExpress = rightExpression.accept(expressionVisitor);
        return new Division(leftExpress, rightExpress);
    }
}
