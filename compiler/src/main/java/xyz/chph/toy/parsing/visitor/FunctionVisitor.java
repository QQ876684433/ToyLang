package xyz.chph.toy.parsing.visitor;

import org.antlr.v4.runtime.misc.NotNull;
import xyz.chph.toy.antlr.ToyBaseVisitor;
import xyz.chph.toy.antlr.ToyParser;
import xyz.chph.toy.domain.Constructor;
import xyz.chph.toy.domain.Function;
import xyz.chph.toy.domain.node.statement.Statement;
import xyz.chph.toy.domain.scope.FunctionSignature;
import xyz.chph.toy.domain.scope.LocalVariable;
import xyz.chph.toy.domain.scope.Scope;
import xyz.chph.toy.parsing.visitor.statement.StatementVisitor;

public class FunctionVisitor extends ToyBaseVisitor<Function> {
    private Scope scope;

    public FunctionVisitor(Scope scope) {
        this.scope = new Scope(scope);
    }

    @Override
    public Function visitFunction(@NotNull ToyParser.FunctionContext ctx) {
        FunctionSignature signature = ctx.functionDeclaration().accept(new FunctionSignatureVisitor(scope));
        // todo 每个方法中的this引用的类型需要回填为全限定名
        scope.addLocalVariable(new LocalVariable("this",scope.getClassType()));
        // todo 每个方法的局部变量（包括参数）的类型也需要回填为全限定名
        addParametersAsLocalVariables(signature);
        Statement block = getBlock(ctx);
        if(signature.getName().equals(scope.getClassName())) {
            return new Constructor(signature,block);
        }
        return new Function(signature, block);
    }


    private void addParametersAsLocalVariables(FunctionSignature signature) {
        signature.getParameters().stream()
                .forEach(param -> scope.addLocalVariable(new LocalVariable(param.getName(), param.getType())));
    }

    private Statement getBlock(ToyParser.FunctionContext functionContext) {
        StatementVisitor statementVisitor = new StatementVisitor(scope);
        ToyParser.BlockContext blockContext = functionContext.block();
        return blockContext.accept(statementVisitor);
    }


}
