package com.flowly4j.core.input;

import java.util.Objects;

/**
 * Represent a pair key -> value
 */
public final class Param {
    private final Key key;
    private final Object value;

    private Param(Key key, Object value) {
        this.key = key;
        this.value = value;
    }

    public static <T> Param of(Key<T> key, T obj) {
        return new Param(key, obj);
    }

    public Key getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Param param = (Param) o;
        return Objects.equals(key, param.key) &&
                Objects.equals(value, param.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return "Param{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
