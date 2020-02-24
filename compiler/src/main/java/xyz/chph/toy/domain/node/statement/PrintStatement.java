package xyz.chph.toy.domain.node.statement;


import xyz.chph.toy.bytecodegenerator.statement.StatementGenerator;
import xyz.chph.toy.domain.node.expression.Expression;

public class PrintStatement implements Statement {
    public static final int TYPE_PRINT = 0;
    public static final int TYPE_PRINTLN = 1;
    private int type;
    private Expression expression;

    public PrintStatement(int type, Expression expression) {
        this.type = type;
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public void accept(StatementGenerator generator) {
        generator.generate(this);
    }

    public int getType() {
        return type;
    }
}
