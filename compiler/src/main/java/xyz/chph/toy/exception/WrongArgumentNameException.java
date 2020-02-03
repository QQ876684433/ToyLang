package xyz.chph.toy.exception;

import xyz.chph.toy.domain.node.expression.Argument;
import xyz.chph.toy.domain.node.expression.Parameter;

import java.util.List;

public class WrongArgumentNameException extends RuntimeException{
    public WrongArgumentNameException(Argument argument, List<Parameter> parameters) {
        super("You are trying to call method with argument name" + argument.getParameterName().get() + " where parameters = " + parameters);
    }
}
