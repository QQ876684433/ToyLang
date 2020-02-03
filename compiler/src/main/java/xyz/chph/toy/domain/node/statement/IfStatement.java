package xyz.chph.toy.domain.node.statement;


import xyz.chph.toy.bytecodegenerator.statement.StatementGenerator;
import xyz.chph.toy.domain.node.expression.Expression;

import java.util.Optional;

public class IfStatement implements Statement {
    private Expression condition;
    private Statement trueStatement;
    private Optional<Statement> falseStatement;

    public IfStatement(Expression condition, Statement trueStatement, Statement falseStatement) {
        this.condition = condition;
        this.trueStatement = trueStatement;
        this.falseStatement = Optional.ofNullable(falseStatement);
    }

    public IfStatement(Expression condition, Statement trueStatement) {
        this.condition = condition;
        this.trueStatement = trueStatement;
        this.falseStatement = Optional.empty();
    }

    public Expression getCondition() {
        return condition;
    }

    public Statement getTrueStatement() {
        return trueStatement;
    }

    public Optional<Statement> getFalseStatement() {
        return falseStatement;
    }

    @Override
    public void accept(StatementGenerator generator) {
        generator.generate(this);
    }
}
