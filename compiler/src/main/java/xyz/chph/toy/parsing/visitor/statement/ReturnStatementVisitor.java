package xyz.chph.toy.parsing.visitor.statement;

import org.antlr.v4.runtime.misc.NotNull;
import xyz.chph.toy.antlr.ToyBaseVisitor;
import xyz.chph.toy.antlr.ToyParser;
import xyz.chph.toy.domain.node.expression.EmptyExpression;
import xyz.chph.toy.domain.node.expression.Expression;
import xyz.chph.toy.domain.node.statement.ReturnStatement;
import xyz.chph.toy.domain.type.BuiltInType;
import xyz.chph.toy.parsing.visitor.expression.ExpressionVisitor;

public class ReturnStatementVisitor  extends ToyBaseVisitor<ReturnStatement> {
    private final ExpressionVisitor expressionVisitor;
    public ReturnStatementVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }
    @Override
    public ReturnStatement visitReturnVoid(@NotNull ToyParser.ReturnVoidContext ctx) {
        return new ReturnStatement(new EmptyExpression(BuiltInType.VOID));
    }
    @Override
    public ReturnStatement visitReturnWithValue(@NotNull ToyParser.ReturnWithValueContext ctx) {
        Expression expression = ctx.expression().accept(expressionVisitor);
        return new ReturnStatement(expression);
    }
}
