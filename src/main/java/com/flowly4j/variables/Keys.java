package com.flowly4j.variables;

public class Keys {

    public static <T> Key<T> create(String identifier) {
        return () -> identifier;
    }

}
