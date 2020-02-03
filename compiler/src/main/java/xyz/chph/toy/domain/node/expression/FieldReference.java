package xyz.chph.toy.domain.node.expression;

import xyz.chph.toy.bytecodegenerator.expression.ExpressionGenerator;
import xyz.chph.toy.bytecodegenerator.statement.StatementGenerator;
import xyz.chph.toy.domain.scope.Field;
import xyz.chph.toy.domain.type.Type;

public class FieldReference implements Reference {
    private Field field;

    public FieldReference(Field field) {
        this.field = field;
    }

    public String geName() {
        return field.getName();
    }

    @Override
    public void accept(ExpressionGenerator generator) {
        generator.generate(this);
    }


    @Override
    public void accept(StatementGenerator generator) {
        generator.generate(this);
    }

    @Override
    public Type getType() {
        return field.getType();
    }

    public String getOwnerInternalName() {
        return field.getOwnerInternalName();
    }
}
