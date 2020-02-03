package xyz.chph.toy.domain.node.expression.arthimetic;


import xyz.chph.toy.bytecodegenerator.expression.ExpressionGenerator;
import xyz.chph.toy.bytecodegenerator.statement.StatementGenerator;
import xyz.chph.toy.domain.node.expression.Expression;

public class Substraction extends ArthimeticExpression {
    public Substraction(Expression leftExpress, Expression rightExpress) {
        super(leftExpress, rightExpress);
    }

    @Override
    public void accept(ExpressionGenerator genrator) {
        genrator.generate(this);
    }

    @Override
    public void accept(StatementGenerator generator) {
        generator.generate(this);
    }
}
