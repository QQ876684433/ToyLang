package xyz.chph.toy.exception;

import xyz.chph.toy.domain.node.expression.Parameter;

import java.util.List;

public class ParameterForNameNotFoundException extends RuntimeException{
    public ParameterForNameNotFoundException(String name, List<Parameter> parameters) {
    }
}
