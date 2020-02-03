package xyz.chph.toy.domain.scope;

import xyz.chph.toy.domain.type.Type;

public class LocalVariable implements Variable{
    private String name;
    private Type type;

    public LocalVariable(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Type getType() {
        return type;
    }
}
