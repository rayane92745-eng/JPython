package com.jpython.environment;

import java.util.HashMap;
import java.util.Map;

public class Environment {
    private final Map<String, Object> values = new HashMap<>();

    public void define(String name, Object value) {
        values.put(name, value);
    }

    public void assign(String name, Object value) {
        if (!values.containsKey(name)) {
            throw new RuntimeException("Undefined variable '" + name + "'");
        }
        values.put(name, value);
    }

    public Object get(String name) {
        if (!values.containsKey(name)) {
            throw new RuntimeException("Undefined variable '" + name + "'");
        }
        return values.get(name);
    }
}