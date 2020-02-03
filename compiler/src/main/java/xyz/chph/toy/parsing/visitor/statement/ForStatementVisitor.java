package xyz.chph.toy.parsing.visitor.statement;

import xyz.chph.toy.antlr.ToyBaseVisitor;
import xyz.chph.toy.antlr.ToyParser;
import xyz.chph.toy.domain.node.expression.Expression;
import xyz.chph.toy.domain.node.statement.Assignment;
import xyz.chph.toy.domain.node.statement.RangedForStatement;
import xyz.chph.toy.domain.node.statement.Statement;
import xyz.chph.toy.domain.node.statement.VariableDeclaration;
import xyz.chph.toy.domain.scope.LocalVariable;
import xyz.chph.toy.domain.scope.Scope;
import xyz.chph.toy.parsing.visitor.expression.ExpressionVisitor;

public class ForStatementVisitor extends ToyBaseVisitor<RangedForStatement> {
    private Scope scope;
    private ExpressionVisitor expressionVisitor;

    public ForStatementVisitor(Scope scope) {
        this.scope = scope;
        expressionVisitor = new ExpressionVisitor(this.scope);
    }

    @Override
    public RangedForStatement visitForStatement(ToyParser.ForStatementContext ctx) {
        Scope newScope = new Scope(scope);
        ToyParser.ForConditionsContext forConditionsContext = ctx.forConditions();
        Expression startExpression = forConditionsContext.startExpr.accept(expressionVisitor);
        Expression endExpression = forConditionsContext.endExpr.accept(expressionVisitor);
        ToyParser.VariableReferenceContext iterator = forConditionsContext.iterator;
        StatementVisitor statementVisitor = new StatementVisitor(newScope);
        String varName = iterator.getText();
        if(newScope.isLocalVariableExists(varName)) {
            Statement iteratorVariable = new Assignment(varName, startExpression);
            Statement statement = ctx.statement().accept(statementVisitor);
            return new RangedForStatement(iteratorVariable, startExpression, endExpression,statement, varName, newScope);
        } else {
            newScope.addLocalVariable(new LocalVariable(varName, startExpression.getType()));
            Statement iteratorVariable = new VariableDeclaration(varName, startExpression);
            Statement statement = ctx.statement().accept(statementVisitor);
            return new RangedForStatement(iteratorVariable, startExpression, endExpression,statement, varName,newScope);
        }
    }
}
