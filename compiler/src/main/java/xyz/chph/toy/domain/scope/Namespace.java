package xyz.chph.toy.domain.scope;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by steve_chph, 2020/02/04 10:37
 */
public class Namespace {
    // using xxx.yyy.*
    private Collection<String> subModules;

    // using xxx.yyy.zzz
    private Collection<String> qualifiedNames;

    public Namespace(Collection<String> subModules, Collection<String> qualifiedNames) {
        this.subModules = subModules;
        this.qualifiedNames = qualifiedNames;
    }

    public List<String> getSubModules() {
        return new ArrayList<>(subModules);
    }

    public List<String> getQualifiedNames() {
        return new ArrayList<>(qualifiedNames);
    }
}
