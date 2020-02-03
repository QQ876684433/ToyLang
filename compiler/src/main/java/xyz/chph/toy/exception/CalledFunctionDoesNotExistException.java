package xyz.chph.toy.exception;

import xyz.chph.toy.domain.node.expression.FunctionCall;
import xyz.chph.toy.domain.scope.Scope;

public class CalledFunctionDoesNotExistException extends CompilationException {
    FunctionCall functionCall;

    public CalledFunctionDoesNotExistException(FunctionCall functionCall) {
        this.functionCall = functionCall;
    }

    public CalledFunctionDoesNotExistException(FunctionCall functionCall, ReflectiveOperationException e) {
        this(functionCall);
    }

    public CalledFunctionDoesNotExistException(FunctionCall functionCall, Scope scope) {
        this(functionCall);
    }

    @Override
    public String getMessage() {
        return "Function call" + functionCall.toString() + "does not exists";
    }
}
