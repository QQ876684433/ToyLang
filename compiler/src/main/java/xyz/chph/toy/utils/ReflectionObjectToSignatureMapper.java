package xyz.chph.toy.utils;

import xyz.chph.toy.domain.node.expression.Parameter;
import xyz.chph.toy.domain.scope.FunctionSignature;
import xyz.chph.toy.domain.type.BuiltInType;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public final class ReflectionObjectToSignatureMapper {
    public static FunctionSignature fromMethod(Method method) {
        String name = method.getName();
        List<Parameter> parameters = Arrays.stream(method.getParameters())
                .map(p -> new Parameter(p.getName(), TypeResolver.getFromTypeName(p.getType().getCanonicalName()), Optional.empty()))
                .collect(toList());
        Class<?> returnType = method.getReturnType();
        return new FunctionSignature(name, parameters, TypeResolver.getFromTypeName(returnType.getCanonicalName()));
    }

    public static FunctionSignature fromConstructor(Constructor constructor) {
        String name = constructor.getName();
        List<Parameter> parameters = Arrays.stream(constructor.getParameters())
                .map(p -> new Parameter(p.getName(), TypeResolver.getFromTypeName(p.getType().getCanonicalName()), Optional.empty()))
                .collect(toList());
        return new FunctionSignature(name, parameters, BuiltInType.VOID);
    }
}
