package xyz.chph.toy.bytecodegenerator.statement;

import xyz.chph.toy.bytecodegenerator.expression.ExpressionGenerator;
import xyz.chph.toy.domain.node.expression.Expression;
import xyz.chph.toy.domain.node.statement.Assignment;
import xyz.chph.toy.domain.node.statement.VariableDeclaration;

public class VariableDeclarationStatementGenerator {
    private final StatementGenerator statementGenerator;
    private final ExpressionGenerator expressionGenerator;
    public VariableDeclarationStatementGenerator(StatementGenerator statementGenerator, ExpressionGenerator expressionGenerator) {
        this.statementGenerator = statementGenerator;
        this.expressionGenerator = expressionGenerator;
    }
    public void generate(VariableDeclaration variableDeclaration) {
        Expression expression = variableDeclaration.getExpression();
        expression.accept(expressionGenerator);
        Assignment assignment = new Assignment(variableDeclaration);
        assignment.accept(statementGenerator);
    }
}
