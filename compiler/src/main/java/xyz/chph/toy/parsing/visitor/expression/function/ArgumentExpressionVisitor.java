package xyz.chph.toy.parsing.visitor.expression.function;

import org.antlr.v4.runtime.misc.NotNull;
import xyz.chph.toy.antlr.ToyBaseVisitor;
import xyz.chph.toy.antlr.ToyParser;
import xyz.chph.toy.domain.node.expression.Argument;
import xyz.chph.toy.domain.node.expression.Expression;
import xyz.chph.toy.parsing.visitor.expression.ExpressionVisitor;

import java.util.Optional;

public class ArgumentExpressionVisitor extends ToyBaseVisitor<Argument> {
    private final ExpressionVisitor expressionVisitor;
    public ArgumentExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }
    @Override
    public Argument visitArgument(@NotNull ToyParser.ArgumentContext ctx) {
        Expression value = ctx.expression().accept(expressionVisitor);
        return new Argument(value, Optional.empty());
    }
    @Override
    public Argument visitNamedArgument(@NotNull ToyParser.NamedArgumentContext ctx) {
        Expression value = ctx.expression().accept(expressionVisitor);
        String name = ctx.name().getText();
        return new Argument(value, Optional.of(name));
    }
}
