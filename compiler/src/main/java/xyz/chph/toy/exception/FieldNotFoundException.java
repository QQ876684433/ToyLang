package xyz.chph.toy.exception;

import xyz.chph.toy.domain.scope.Scope;

public class FieldNotFoundException extends RuntimeException {
    public FieldNotFoundException(Scope scope, String fieldName) {
        super("No field found for name " + fieldName + " found in scope" + scope);
    }
}
