package xyz.chph.toy.domain.node.expression.arthimetic;

import xyz.chph.toy.domain.node.expression.Expression;
import xyz.chph.toy.domain.type.BuiltInType;
import xyz.chph.toy.domain.type.Type;

public abstract class ArthimeticExpression implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;
    private Type type;

    public ArthimeticExpression(Expression leftExpression, Expression rightExpression) {
        this.type = getCommonType(leftExpression, rightExpression);
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    private static Type getCommonType(Expression leftExpression, Expression rightExpression) {
        if (rightExpression.getType() == BuiltInType.STRING) return BuiltInType.STRING;
        return leftExpression.getType();
    }

    public Expression getLeftExpression() {
        return leftExpression;
    }

    public Expression getRightExpression() {
        return rightExpression;
    }

    @Override
    public Type getType() {
        return type;
    }
}
