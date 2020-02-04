package xyz.chph.toy.domain;

public class MetaData {
    private String moduleName;
    private String className;
    private String superClassName;

    public MetaData(String moduleName) {
        this.moduleName = moduleName;
    }

    public MetaData(String moduleName, String className, String superClassName) {
        this.moduleName = moduleName;
        this.className = className;
        this.superClassName = superClassName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getClassName() {
        return className;
    }

    public String getSuperClassName() {
        return superClassName;
    }
}
