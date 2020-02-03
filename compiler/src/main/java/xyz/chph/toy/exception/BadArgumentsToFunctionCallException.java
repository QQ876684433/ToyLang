package xyz.chph.toy.exception;

import xyz.chph.toy.domain.node.expression.Call;

public class BadArgumentsToFunctionCallException extends RuntimeException {
    public BadArgumentsToFunctionCallException(Call functionCall) {
    }
}
