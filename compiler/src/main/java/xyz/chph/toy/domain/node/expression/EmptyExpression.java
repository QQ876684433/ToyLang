package xyz.chph.toy.domain.node.expression;

import xyz.chph.toy.bytecodegenerator.expression.ExpressionGenerator;
import xyz.chph.toy.bytecodegenerator.statement.StatementGenerator;
import xyz.chph.toy.domain.type.Type;

public class EmptyExpression implements Expression {
    private Type type;

    public EmptyExpression(Type type) {
        this.type = type;
    }

    @Override
    public void accept(ExpressionGenerator generator) {
        generator.generate(this);
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void accept(StatementGenerator generator) {
        generator.generate(this);
    }
}
