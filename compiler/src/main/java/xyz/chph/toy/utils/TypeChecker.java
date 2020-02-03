package xyz.chph.toy.utils;

import xyz.chph.toy.domain.type.BuiltInType;
import xyz.chph.toy.domain.type.Type;

public class TypeChecker {
    public static boolean isInt(Type type) {
        return type == BuiltInType.INT;
    }

    public static boolean isBool(Type type) {
        return type == BuiltInType.BOOLEAN;
    }

    public static boolean isFloat(Type type) {
        return type == BuiltInType.FLOAT;
    }

    public static boolean isDouble(Type type) {
        return type == BuiltInType.DOUBLE;
    }
}
