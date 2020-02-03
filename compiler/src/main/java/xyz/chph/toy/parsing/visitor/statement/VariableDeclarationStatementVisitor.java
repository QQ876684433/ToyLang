package xyz.chph.toy.parsing.visitor.statement;

import org.antlr.v4.runtime.misc.NotNull;
import xyz.chph.toy.antlr.ToyBaseVisitor;
import xyz.chph.toy.antlr.ToyParser;
import xyz.chph.toy.domain.node.expression.Expression;
import xyz.chph.toy.domain.node.statement.VariableDeclaration;
import xyz.chph.toy.domain.scope.LocalVariable;
import xyz.chph.toy.domain.scope.Scope;
import xyz.chph.toy.parsing.visitor.expression.ExpressionVisitor;

public class VariableDeclarationStatementVisitor  extends ToyBaseVisitor<VariableDeclaration> {
    private final ExpressionVisitor expressionVisitor;
    private final Scope scope;
    public VariableDeclarationStatementVisitor(ExpressionVisitor expressionVisitor, Scope scope) {
        this.expressionVisitor = expressionVisitor;
        this.scope = scope;
    }
    @Override
    public VariableDeclaration visitVariableDeclaration(@NotNull ToyParser.VariableDeclarationContext ctx) {
        String varName = ctx.name().getText();
        ToyParser.ExpressionContext expressionCtx = ctx.expression();
        Expression expression = expressionCtx.accept(expressionVisitor);
        scope.addLocalVariable(new LocalVariable(varName, expression.getType()));
        return new VariableDeclaration(varName, expression);
    }
}

