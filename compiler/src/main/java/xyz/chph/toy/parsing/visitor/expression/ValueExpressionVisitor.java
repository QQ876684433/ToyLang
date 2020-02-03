package xyz.chph.toy.parsing.visitor.expression;

import org.antlr.v4.runtime.misc.NotNull;
import xyz.chph.toy.antlr.ToyBaseVisitor;
import xyz.chph.toy.antlr.ToyParser;
import xyz.chph.toy.domain.node.expression.Value;
import xyz.chph.toy.domain.type.Type;
import xyz.chph.toy.utils.TypeResolver;

public class ValueExpressionVisitor extends ToyBaseVisitor<Value> {
    @Override
    public Value visitValue(@NotNull ToyParser.ValueContext ctx) {
        String value = ctx.getText();
        Type type = TypeResolver.getFromValue(ctx);
        return new Value(type, value);
    }
}
