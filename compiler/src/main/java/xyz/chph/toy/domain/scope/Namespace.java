package xyz.chph.toy.domain.scope;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by steve_chph, 2020/02/16 23:33
 */
public class Namespace {
    private Map<String, String> names;

    public Namespace(Map<String, String> names) {
        this.names = new HashMap<>(names);
    }

    public String getInterfaceName(String reference) {
        return names.getOrDefault(reference, reference);
    }

    public Map<String, String> getNames() {
        return ImmutableMap.copyOf(names);
    }
}
