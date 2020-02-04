package xyz.chph.toy.domain.type;

public interface Type {
    // qualified name
    String getName();

    Class<?> getTypeClass();

    // type descriptor
    String getDescriptor();

    // internal name used in class file
    String getInternalName();

    // type name
    String getClassName();

    int getLoadVariableOpcode();

    int getStoreVariableOpcode();

    int getReturnOpcode();

    int getAddOpcode();

    int getSubstractOpcode();

    int getMultiplyOpcode();

    int getDivideOpcode();
}
