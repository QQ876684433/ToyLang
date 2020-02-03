package xyz.chph.toy.parsing.visitor.expression.function;

import org.antlr.v4.runtime.misc.NotNull;
import xyz.chph.toy.antlr.ToyBaseVisitor;
import xyz.chph.toy.antlr.ToyParser;
import xyz.chph.toy.domain.node.expression.Argument;
import xyz.chph.toy.parsing.visitor.expression.ExpressionVisitor;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ArgumentExpressionsListVisitor extends ToyBaseVisitor<List<Argument>> {
    private final ExpressionVisitor expressionVisitor;
    public ArgumentExpressionsListVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }
    @Override
    public List<Argument> visitUnnamedArgumentsList(@NotNull ToyParser.UnnamedArgumentsListContext ctx) {
        ArgumentExpressionVisitor argumentExpressionVisitor = new ArgumentExpressionVisitor(expressionVisitor);
        return ctx.argument().stream()
                .map(a -> a.accept(argumentExpressionVisitor)).collect(toList());
    }
    @Override
    public List<Argument> visitNamedArgumentsList(@NotNull ToyParser.NamedArgumentsListContext ctx) {
        ArgumentExpressionVisitor argumentExpressionVisitor = new ArgumentExpressionVisitor(expressionVisitor);
        return ctx.namedArgument().stream()
                .map(a -> a.accept(argumentExpressionVisitor)).collect(toList());
    }
}
