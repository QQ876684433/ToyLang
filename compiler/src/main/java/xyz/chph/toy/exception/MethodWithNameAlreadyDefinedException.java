package xyz.chph.toy.exception;

import xyz.chph.toy.domain.scope.FunctionSignature;

public class MethodWithNameAlreadyDefinedException extends RuntimeException{
    public MethodWithNameAlreadyDefinedException(FunctionSignature signature) {
        super("Method already defined in scope :" + signature);
    }
}
