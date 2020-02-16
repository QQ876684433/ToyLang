package xyz.chph.toy.domain;

import xyz.chph.toy.domain.scope.Field;

import java.util.Collections;
import java.util.List;

public class ClassDeclaration {
    private String name;
    private List<Field> fields;
    private List<Function> methods;
    private String module;

    public ClassDeclaration(String name, List<Field> fields, List<Function> methods) {
        this.name = name;
        this.fields = fields;
        this.methods = methods;
        this.module = "";
    }

    public String getName() {
        return name;
    }

    public String getQualifiedName() {
        return module.replace(".", "/") + "/" + name;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public List<Field> getFields() {
        return Collections.unmodifiableList(fields);
    }

    public List<Function> getMethods() {
        return Collections.unmodifiableList(methods);
    }
}
