package xyz.chph.toy.utils;

import com.google.common.primitives.Doubles;
import com.google.common.primitives.Floats;
import com.google.common.primitives.Ints;
import org.apache.commons.lang3.StringUtils;
import xyz.chph.toy.antlr.ToyParser;
import xyz.chph.toy.domain.type.BuiltInType;
import xyz.chph.toy.domain.type.ClassType;
import xyz.chph.toy.domain.type.Type;

import java.util.Arrays;
import java.util.Optional;

public final class TypeResolver {

    public static Type getFromTypeContext(ToyParser.TypeContext typeContext) {
        if(typeContext == null) return BuiltInType.VOID;
        return getFromTypeName(typeContext.getText());
    }


    public static Type getFromTypeName(String typeName) {
        if(typeName.equals("String")) return BuiltInType.STRING;
        Optional<? extends Type> builtInType = getBuiltInType(typeName);
        if(builtInType.isPresent()) return builtInType.get();
        return new ClassType(typeName);
    }

    public static Type getFromValue(ToyParser.ValueContext value) {
        String stringValue = value.getText();
        if (StringUtils.isEmpty(stringValue)) return BuiltInType.VOID;
        if (value.NUMBER() != null) {
            if (Ints.tryParse(stringValue) != null) {
                return BuiltInType.INT;
            } else if(Floats.tryParse(stringValue) != null) {
                return BuiltInType.FLOAT;
            } else if(Doubles.tryParse(stringValue) != null) {
                return BuiltInType.DOUBLE;
            }
        } else if (value.BOOL() != null) {
            return BuiltInType.BOOLEAN;
        }
        return BuiltInType.STRING;
    }

    public static Object getValueFromString(String stringValue, Type type) {
        if (TypeChecker.isInt(type)) {
            return Integer.valueOf(stringValue);
        }
        if (TypeChecker.isFloat(type)) {
            return Float.valueOf(stringValue);
        }
        if (TypeChecker.isDouble(type)) {
            return Double.valueOf(stringValue);
        }
        if (TypeChecker.isBool(type)) {
            return Boolean.valueOf(stringValue);
        }
        if (type == BuiltInType.STRING) {
            stringValue = StringUtils.removeStart(stringValue, "\"");
            stringValue = StringUtils.removeEnd(stringValue, "\"");
            return stringValue;
        }
        throw new AssertionError("Objects not yet implemented!");
    }

    private static Optional<BuiltInType> getBuiltInType(String typeName) {
        return Arrays.stream(BuiltInType.values())
                .filter(type -> type.getName().equals(typeName))
                .findFirst();
    }
}
