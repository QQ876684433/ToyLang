package xyz.chph.toy.domain.scope;

import xyz.chph.toy.bytecodegenerator.FieldGenerator;
import xyz.chph.toy.domain.type.ClassType;
import xyz.chph.toy.domain.type.Type;

public class Field implements Variable {
    private final String name;
    private final Type owner;
    private final Type type;


    public Field(String name, Type owner, Type type) {
        this.name = name;
        this.type = type;
        this.owner = owner;
    }

    // owner must be ClassType
    public void setOwner(String owner) {
        // TODO assert should be remove in release version
        assert this.owner instanceof ClassType : "owner of Field must be ClassType";
        ((ClassType) this.owner).setName(owner);
    }

    public void setType(String type) {
        if (this.type instanceof ClassType)
            ((ClassType) this.type).setName(type);
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getOwnerInternalName() {
        return owner.getInternalName();
    }

    public void accept(FieldGenerator generator) {
        generator.generate(this);
    }
}
