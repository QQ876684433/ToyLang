package xyz.chph.toy.utils;

import xyz.chph.toy.domain.Function;
import xyz.chph.toy.domain.node.expression.Parameter;
import xyz.chph.toy.domain.scope.FunctionSignature;
import xyz.chph.toy.domain.type.Type;

import java.util.Collection;
import java.util.stream.Collectors;

public class DescriptorFactory {

    public static String getMethodDescriptor(Function function) {
        Collection<Parameter> parameters = function.getParameters();
        Type returnType = function.getReturnType();
        return getMethodDescriptor(parameters, returnType);
    }

    public static String getMethodDescriptor(FunctionSignature signature) {
        Collection<Parameter> parameters = signature.getParameters();
        Type returnType = signature.getReturnType();
        return getMethodDescriptor(parameters, returnType);
    }

    private static String getMethodDescriptor(Collection<Parameter> parameters, Type returnType) {
        String parametersDescriptor = parameters.stream()
                .map(parameter -> parameter.getType().getDescriptor())
                .collect(Collectors.joining("", "(", ")"));
        String returnDescriptor = returnType.getDescriptor();
        return parametersDescriptor + returnDescriptor;
    }
}
