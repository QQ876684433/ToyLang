package xyz.chph.toy.domain.node.expression;

import xyz.chph.toy.bytecodegenerator.expression.ExpressionGenerator;
import xyz.chph.toy.bytecodegenerator.statement.StatementGenerator;
import xyz.chph.toy.domain.type.BuiltInType;
import xyz.chph.toy.domain.type.Type;

import java.util.Collections;
import java.util.List;

public class SuperCall implements Call {
    public static final String SUPER_IDENTIFIER = "super";
    private List<Argument> arguments;

    public SuperCall() {
        this(Collections.emptyList());
    }

    public SuperCall(List<Argument> arguments) {
        this.arguments = arguments;
    }

    @Override
    public List<Argument> getArguments() {
        return arguments;
    }

    @Override
    public String getIdentifier() {
        return SUPER_IDENTIFIER;
    }

    @Override
    public Type getType() {
        return BuiltInType.VOID;
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
