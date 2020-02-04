package xyz.chph.toy.domain;

/**
 * Created by steve_chph, 2020/02/03 22:24
 */
public class ModuleDeclaration {
    private String qualifiedName;

    public ModuleDeclaration(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    public String getQualifiedName() {
        return qualifiedName;
    }
}
