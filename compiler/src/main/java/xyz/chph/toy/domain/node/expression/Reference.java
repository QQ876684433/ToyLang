package xyz.chph.toy.domain.node.expression;

import xyz.chph.toy.bytecodegenerator.expression.ExpressionGenerator;
import xyz.chph.toy.bytecodegenerator.statement.StatementGenerator;

public interface Reference extends Expression {
    String geName();

    @Override
    void accept(ExpressionGenerator generator);

    @Override
    void accept(StatementGenerator generator);
}
