package xyz.chph.toy.parsing.visitor.expression.function;

import org.antlr.v4.runtime.misc.NotNull;
import xyz.chph.toy.antlr.ToyBaseVisitor;
import xyz.chph.toy.antlr.ToyParser;
import xyz.chph.toy.domain.node.expression.Expression;
import xyz.chph.toy.domain.node.expression.Parameter;
import xyz.chph.toy.domain.scope.Scope;
import xyz.chph.toy.domain.type.Type;
import xyz.chph.toy.parsing.visitor.expression.ExpressionVisitor;
import xyz.chph.toy.utils.TypeResolver;

import java.util.Optional;

public class ParameterExpressionVisitor extends ToyBaseVisitor<Parameter> {
    private final ExpressionVisitor expressionVisitor;
    private Scope scope;

    public ParameterExpressionVisitor(ExpressionVisitor expressionVisitor, Scope scope) {
        this.expressionVisitor = expressionVisitor;
        this.scope = scope;
    }

    @Override
    public Parameter visitParameter(@NotNull ToyParser.ParameterContext ctx) {
        String name = ctx.ID().getText();
        Type type = TypeResolver.getFromTypeName(
                scope.getQualifiedNameFromTypeName(ctx.type().getText())
        );
        return new Parameter(name, type, Optional.empty());
    }

    @Override
    public Parameter visitParameterWithDefaultValue(@NotNull ToyParser.ParameterWithDefaultValueContext ctx) {
        String name = ctx.ID().getText();
        Type type = TypeResolver.getFromTypeName(
                scope.getQualifiedNameFromTypeName(ctx.type().getText())
        );
        Expression defaultValue = ctx.defaultValue.accept(expressionVisitor);
        return new Parameter(name, type, Optional.of(defaultValue));
    }
}
