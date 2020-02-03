package xyz.chph.toy.domain;

import xyz.chph.toy.domain.scope.Field;

import java.util.Collections;
import java.util.List;

public class ClassDeclaration {
    private String name;
    private List<Field> fields;
    private List<Function> methods;

    public ClassDeclaration(String name, List<Field> fields, List<Function> methods) {
        this.name = name;
        this.fields = fields;
        this.methods = methods;
    }

    public String getName() {
        return name;
    }

    public List<Field> getFields() {
        return Collections.unmodifiableList(fields);
    }

    public List<Function> getMethods() {
        return Collections.unmodifiableList(methods);
    }
}
