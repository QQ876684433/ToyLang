package xyz.chph.toy.domain;

/**
 * Created by steve_chph, 2020/02/04 10:50
 */
public class UsingDeclaration {
    public static final int SUBMODULE = 0;
    public static final int QUALIFIED_NAME = 1;

    private int type;

    private String name;

    public UsingDeclaration(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
