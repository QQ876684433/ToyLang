package xyz.chph.toy.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by steve_chph, 21:55
 */
public class ImportDeclaration {
    private Map<String, String> imports;

    public ImportDeclaration() {
        imports = new HashMap<>();
    }

    public ImportDeclaration(ImportDeclaration import1, ImportDeclaration import2) {
        this();
        imports.putAll(import1.imports);
        imports.putAll(import2.imports);
    }

    public void add(String reference, String qualifiedName) {
        imports.put(reference, qualifiedName);
    }
}
