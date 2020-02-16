package xyz.chph.toy.parsing.visitor.expression.function;

import com.google.common.collect.Lists;
import org.antlr.v4.runtime.misc.NotNull;
import xyz.chph.toy.antlr.ToyBaseVisitor;
import xyz.chph.toy.antlr.ToyParser;
import xyz.chph.toy.domain.node.expression.Parameter;
import xyz.chph.toy.parsing.visitor.expression.ExpressionVisitor;

import java.util.ArrayList;
import java.util.List;

public class ParameterExpressionListVisitor extends ToyBaseVisitor<List<Parameter>> {
    private final ExpressionVisitor expressionVisitor;
    public ParameterExpressionListVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }
    @Override
    public List<Parameter> visitParametersList(@NotNull ToyParser.ParametersListContext ctx) {
        List<ToyParser.ParameterContext> paramsCtx = ctx.parameter();
        ParameterExpressionVisitor parameterExpressionVisitor = new ParameterExpressionVisitor(expressionVisitor);
        List<Parameter> parameters = new ArrayList<>();
        if(paramsCtx != null) {
            // todo 需要回填
            List<Parameter> params = Lists.transform(paramsCtx, p -> p.accept(parameterExpressionVisitor));
            parameters.addAll(params);
        }
        List<ToyParser.ParameterWithDefaultValueContext> paramsWithDefaultValueCtx = ctx.parameterWithDefaultValue();
        if(paramsWithDefaultValueCtx != null) {
            // todo 需要回填
            List<Parameter> params = Lists.transform(paramsWithDefaultValueCtx, p -> p.accept(parameterExpressionVisitor));
            parameters.addAll(params);
        }
        return parameters;
    }
}
