package xyz.chph.toy.domain.node.expression;

import xyz.chph.toy.bytecodegenerator.expression.ExpressionGenerator;
import xyz.chph.toy.bytecodegenerator.statement.StatementGenerator;
import xyz.chph.toy.domain.scope.LocalVariable;
import xyz.chph.toy.domain.type.Type;

public class LocalVariableReference implements Reference {
    private final LocalVariable variable;

    public LocalVariableReference(LocalVariable variable) {
        this.variable = variable;
    }

    @Override
    public String geName() {
        return variable.getName();
    }

    @Override
    public void accept(ExpressionGenerator generator) {
        generator.generate(this);
    }

    @Override
    public Type getType() {
        return variable.getType();
    }

    @Override
    public void accept(StatementGenerator generator) {
        generator.generate(this);
    }
}
