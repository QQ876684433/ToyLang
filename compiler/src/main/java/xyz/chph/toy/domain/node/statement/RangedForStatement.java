package xyz.chph.toy.domain.node.statement;

import xyz.chph.toy.bytecodegenerator.statement.StatementGenerator;
import xyz.chph.toy.domain.node.expression.Expression;
import xyz.chph.toy.domain.scope.Scope;
import xyz.chph.toy.domain.type.Type;
import xyz.chph.toy.utils.TypeChecker;
import xyz.chph.toy.exception.UnsupportedRangedLoopTypes;

public class RangedForStatement implements Statement {
    private Statement iteratorVariable;
    private Expression startExpression;
    private Expression endExpression;
    private Statement statement;
    private String iteratorVarName;
    private Scope scope;

    public RangedForStatement(Statement iteratorVariable, Expression startExpression, Expression endExpression, Statement statement, String iteratorVarName, Scope scope) {
        this.scope = scope;
        Type startExpressionType = startExpression.getType();
        Type endExpressionType = endExpression.getType();
        boolean typesAreIntegers = TypeChecker.isInt(startExpressionType) || TypeChecker.isInt(endExpressionType);
        if(!typesAreIntegers) {
            throw new UnsupportedRangedLoopTypes(startExpression, endExpression);
        }
        this.iteratorVariable = iteratorVariable;
        this.startExpression = startExpression;
        this.endExpression = endExpression;
        this.statement = statement;
        this.iteratorVarName = iteratorVarName;
    }

    public Statement getIteratorVariableStatement() {
        return iteratorVariable;
    }

    public Expression getStartExpression() {
        return startExpression;
    }

    public Expression getEndExpression() {
        return endExpression;
    }

    public Statement getStatement() {
        return statement;
    }

    public String getIteratorVarName() {
        return iteratorVarName;
    }

    public Scope getScope() {
        return scope;
    }

    @Override
    public void accept(StatementGenerator generator) {
        generator.generate(this);
    }

    public Type getType() {
        return startExpression.getType();
    }
}
