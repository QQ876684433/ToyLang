package xyz.chph.toy.parsing.visitor.expression;

import org.antlr.v4.runtime.misc.NotNull;
import xyz.chph.toy.antlr.ToyBaseVisitor;
import xyz.chph.toy.antlr.ToyParser;
import xyz.chph.toy.domain.CompareSign;
import xyz.chph.toy.domain.node.expression.ConditionalExpression;
import xyz.chph.toy.domain.node.expression.Expression;
import xyz.chph.toy.domain.node.expression.Value;
import xyz.chph.toy.domain.type.BuiltInType;

public class ConditionalExpressionVisitor extends ToyBaseVisitor<ConditionalExpression> {
    private final ExpressionVisitor expressionVisitor;
    public ConditionalExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }
    @Override
    public ConditionalExpression visitConditionalExpression(@NotNull ToyParser.ConditionalExpressionContext ctx) {
        ToyParser.ExpressionContext leftExpressionCtx = ctx.expression(0);
        ToyParser.ExpressionContext rightExpressionCtx = ctx.expression(1);
        Expression leftExpression = leftExpressionCtx.accept(expressionVisitor);
        Expression rightExpression = rightExpressionCtx != null ? rightExpressionCtx.accept(expressionVisitor) : new Value(BuiltInType.INT, "0");
        CompareSign cmpSign = ctx.cmp != null ? CompareSign.fromString(ctx.cmp.getText()) : CompareSign.NOT_EQUAL;
        return new ConditionalExpression(leftExpression, rightExpression, cmpSign);
    }
}
