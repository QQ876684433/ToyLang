package xyz.chph.toy.parsing.visitor.statement;

import org.antlr.v4.runtime.misc.NotNull;
import xyz.chph.toy.antlr.ToyBaseVisitor;
import xyz.chph.toy.antlr.ToyParser;
import xyz.chph.toy.domain.node.expression.Expression;
import xyz.chph.toy.domain.node.statement.IfStatement;
import xyz.chph.toy.domain.node.statement.Statement;
import xyz.chph.toy.parsing.visitor.expression.ExpressionVisitor;

public class IfStatementVisitor extends ToyBaseVisitor<IfStatement> {
    private final StatementVisitor statementVisitor;
    private final ExpressionVisitor expressionVisitor;
    public IfStatementVisitor(StatementVisitor statementVisitor, ExpressionVisitor expressionVisitor) {
        this.statementVisitor = statementVisitor;
        this.expressionVisitor = expressionVisitor;
    }
    @Override
    public IfStatement visitIfStatement(@NotNull ToyParser.IfStatementContext ctx) {
        ToyParser.ExpressionContext conditionalExpressionContext = ctx.expression();
        Expression condition = conditionalExpressionContext.accept(expressionVisitor);
        Statement trueStatement = ctx.trueStatement.accept(statementVisitor);
        if (ctx.falseStatement != null) {
            Statement falseStatement = ctx.falseStatement.accept(statementVisitor);
            return new IfStatement(condition, trueStatement, falseStatement);
        }
        return new IfStatement(condition, trueStatement);
    }
}
