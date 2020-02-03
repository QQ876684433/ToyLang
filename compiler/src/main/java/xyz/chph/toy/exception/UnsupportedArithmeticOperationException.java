package xyz.chph.toy.exception;

import xyz.chph.toy.domain.node.expression.arthimetic.ArthimeticExpression;
import xyz.chph.toy.domain.node.expression.Expression;

public class UnsupportedArithmeticOperationException extends RuntimeException{
    public UnsupportedArithmeticOperationException(ArthimeticExpression expression) {
        super(prepareMesage(expression));
    }
    private static String prepareMesage(ArthimeticExpression expression) {
        Expression leftExpression = expression.getLeftExpression();
        Expression rightExpression = expression.getRightExpression();
        return "Unsupported arthimetic operation between " + leftExpression +" and "+rightExpression;
    }
}
