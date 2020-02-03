package xyz.chph.toy.exception;

import xyz.chph.toy.domain.type.Type;

public class MixedComparisonNotAllowedException extends RuntimeException {
    public MixedComparisonNotAllowedException(Type leftType, Type rightType) {
        super("Comparison between object and primitive is not supported  :" + leftType + "  |  " + rightType);
    }
}
