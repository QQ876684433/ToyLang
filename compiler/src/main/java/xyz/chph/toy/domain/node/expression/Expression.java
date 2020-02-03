package xyz.chph.toy.domain.node.expression;

import xyz.chph.toy.bytecodegenerator.expression.ExpressionGenerator;
import xyz.chph.toy.bytecodegenerator.statement.StatementGenerator;
import xyz.chph.toy.domain.node.statement.Statement;
import xyz.chph.toy.domain.type.Type;

public interface Expression extends Statement {
    Type getType();
    void accept(ExpressionGenerator generator);
    @Override
    void accept(StatementGenerator generator);
}
