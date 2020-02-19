package xyz.chph.toy.domain;

public class MetaData {
    // interface name, separated by .
    private String className;
    private String superClassName;

    public MetaData(String className, String superClassName) {
        this.className = className;
        this.superClassName = superClassName;
    }

    public String getClassName() {
        return className;
    }

    public String getSuperClassName() {
        return superClassName;
    }
}
