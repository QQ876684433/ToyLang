package xyz.chph.toy.domain.node.statement;


import xyz.chph.toy.bytecodegenerator.statement.StatementGenerator;
import xyz.chph.toy.domain.node.expression.Expression;

public class Assignment implements Statement {
    private String varName;
    private Expression expression;

    public Assignment(String varName, Expression expression) {
        this.varName = varName;
        this.expression = expression;
    }

    public Assignment(VariableDeclaration declarationStatement) {
        this.varName = declarationStatement.getName();
        this.expression = declarationStatement.getExpression();
    }


    public String getVarName() {
        return varName;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public void accept(StatementGenerator generator) {
        generator.generate(this);
    }
}
