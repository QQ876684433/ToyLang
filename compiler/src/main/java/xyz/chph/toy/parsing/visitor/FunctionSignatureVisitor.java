package xyz.chph.toy.parsing.visitor;

import org.antlr.v4.runtime.misc.NotNull;
import xyz.chph.toy.antlr.ToyBaseVisitor;
import xyz.chph.toy.antlr.ToyParser;
import xyz.chph.toy.domain.node.expression.Parameter;
import xyz.chph.toy.domain.scope.FunctionSignature;
import xyz.chph.toy.domain.scope.Scope;
import xyz.chph.toy.domain.type.Type;
import xyz.chph.toy.parsing.visitor.expression.ExpressionVisitor;
import xyz.chph.toy.parsing.visitor.expression.function.ParameterExpressionListVisitor;
import xyz.chph.toy.utils.TypeResolver;

import java.util.Collections;
import java.util.List;

public class FunctionSignatureVisitor extends ToyBaseVisitor<FunctionSignature> {
    private final ExpressionVisitor expressionVisitor;
    public FunctionSignatureVisitor(Scope scope) {
        this.expressionVisitor = new ExpressionVisitor(scope);
    }
    @Override
    public FunctionSignature visitFunctionDeclaration(@NotNull ToyParser.FunctionDeclarationContext ctx) {
        String functionName = ctx.functionName().getText();
        // todo 返回值类型需要回填成全限定名
        Type returnType = TypeResolver.getFromTypeContext(ctx.type());
        ToyParser.ParametersListContext parametersCtx = ctx.parametersList();
        if(parametersCtx != null) {
            // todo 参数列表需要回填
            List<Parameter> parameters = parametersCtx.accept(new ParameterExpressionListVisitor(expressionVisitor));
            return new FunctionSignature(functionName, parameters, returnType);
        }
        // todo 参数列表为空时不需要回填
        return new FunctionSignature(functionName, Collections.emptyList(), returnType);
    }
}
