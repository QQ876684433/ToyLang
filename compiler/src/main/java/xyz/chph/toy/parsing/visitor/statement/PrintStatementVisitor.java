package xyz.chph.toy.parsing.visitor.statement;

import org.antlr.v4.runtime.misc.NotNull;
import xyz.chph.toy.antlr.ToyBaseVisitor;
import xyz.chph.toy.antlr.ToyParser;
import xyz.chph.toy.domain.node.expression.Expression;
import xyz.chph.toy.domain.node.statement.PrintStatement;
import xyz.chph.toy.parsing.visitor.expression.ExpressionVisitor;

public class PrintStatementVisitor extends ToyBaseVisitor<PrintStatement> {
    private final ExpressionVisitor expressionVisitor;

    public PrintStatementVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Override
    public PrintStatement visitPrintStatement(@NotNull ToyParser.PrintStatementContext ctx) {
        int type = PrintStatement.TYPE_PRINT;
        if (ctx.PRINT() == null && ctx.PRINTLN() != null) type = PrintStatement.TYPE_PRINTLN;
        ToyParser.ExpressionContext expressionCtx = ctx.expression();
        Expression expression = expressionCtx.accept(expressionVisitor);
        return new PrintStatement(type, expression);
    }
}
