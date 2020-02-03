package xyz.chph.toy.domain;

import xyz.chph.toy.bytecodegenerator.MethodGenerator;
import xyz.chph.toy.domain.node.expression.Parameter;
import xyz.chph.toy.domain.scope.FunctionSignature;
import xyz.chph.toy.domain.node.statement.Statement;
import xyz.chph.toy.domain.type.Type;

import java.util.Collections;
import java.util.List;

public class Function {
    private FunctionSignature functionSignature;
    private Statement rootStatement;

    public Function(FunctionSignature functionSignature, Statement rootStatement) {
        this.functionSignature = functionSignature;
        this.rootStatement = rootStatement;
    }

    public String getName() {
        return functionSignature.getName();
    }

    public List<Parameter> getParameters() {
        return Collections.unmodifiableList(functionSignature.getParameters());
    }

    public Statement getRootStatement() {
        return rootStatement;
    }

    public Type getReturnType() {
        return functionSignature.getReturnType();
    }

    public void accept(MethodGenerator generator) {
        generator.generate(this);
    }
}
