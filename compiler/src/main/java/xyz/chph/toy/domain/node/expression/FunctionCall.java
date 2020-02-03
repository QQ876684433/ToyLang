package xyz.chph.toy.domain.node.expression;

import xyz.chph.toy.bytecodegenerator.expression.ExpressionGenerator;
import xyz.chph.toy.bytecodegenerator.statement.StatementGenerator;
import xyz.chph.toy.domain.scope.FunctionSignature;
import xyz.chph.toy.domain.type.Type;

import java.util.Collections;
import java.util.List;

public class FunctionCall implements Call {
    private Expression owner;
    private FunctionSignature signature;
    private List<Argument> arguments;
    private Type type;

    public FunctionCall(FunctionSignature signature, List<Argument> arguments, Expression owner) {
        this.type = signature.getReturnType();
        this.signature = signature;
        this.arguments = arguments;
        this.owner = owner;
    }

    public FunctionCall(FunctionSignature signature, List<Argument> arguments, Type ownerType) {
        this(signature, arguments, new EmptyExpression(ownerType));
    }


    public List<Argument> getArguments() {
        return Collections.unmodifiableList(arguments);
    }

    @Override
    public String getIdentifier() {
        return signature.getName();
    }

    public Type getOwnerType() {
        return owner.getType();
    }

    public Expression getOwner() {
        return owner;
    }

    public FunctionSignature getSignature() {
        return signature;
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
        return type;
    }
}

