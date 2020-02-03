package xyz.chph.toy.domain;

import xyz.chph.toy.bytecodegenerator.MethodGenerator;
import xyz.chph.toy.domain.scope.FunctionSignature;
import xyz.chph.toy.domain.node.statement.Statement;
import xyz.chph.toy.domain.type.BuiltInType;
import xyz.chph.toy.domain.type.Type;

public class Constructor extends Function {
    public Constructor(FunctionSignature functionSignature, Statement block) {
        super(functionSignature, block);
    }

    @Override
    public Type getReturnType() {
        return BuiltInType.VOID;
    }

    @Override
    public void accept(MethodGenerator generator) {
        generator.generate(this);
    }
}
