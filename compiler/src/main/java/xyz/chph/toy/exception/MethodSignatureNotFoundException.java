package xyz.chph.toy.exception;

import xyz.chph.toy.domain.node.expression.Argument;
import xyz.chph.toy.domain.scope.Scope;

import java.util.List;

public class MethodSignatureNotFoundException extends RuntimeException {
    public MethodSignatureNotFoundException(Scope scope, String methodName, List<Argument> parameterTypes) {
        super("There is no method '" + methodName + "' with parameters " + parameterTypes);
    }
}
