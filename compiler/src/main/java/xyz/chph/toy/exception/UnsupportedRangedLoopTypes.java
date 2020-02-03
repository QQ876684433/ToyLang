package xyz.chph.toy.exception;


import xyz.chph.toy.domain.node.expression.Expression;

public class UnsupportedRangedLoopTypes extends RuntimeException {
    public UnsupportedRangedLoopTypes(Expression startExpression, Expression endExpression) {
        super("Only integer types are supported so far");
    }
}
