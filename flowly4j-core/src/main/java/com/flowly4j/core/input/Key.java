package com.flowly4j.core.input;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Objects;

/**
 * Bind a type to a specific String
 */
public final class Key<T> {
    private final String identifier;
    private final TypeReference<T> typeReference;

    private Key(String identifier, TypeReference<T> typeReference) {
        this.identifier = identifier;
        this.typeReference = typeReference;
    }

    public static <T> Key<T> of(String identifier, TypeReference<T> typeReference) {
        return new Key<>(identifier, typeReference);
    }

    public String getIdentifier() {
        return identifier;
    }

    public TypeReference<T> getTypeReference() {
        return typeReference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key<?> key = (Key<?>) o;
        return Objects.equals(identifier, key.identifier) &&
                Objects.equals(typeReference, key.typeReference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, typeReference);
    }

    @Override
    public String toString() {
        return "Key{" +
                "identifier='" + identifier + '\'' +
                ", typeReference=" + typeReference +
                '}';
    }
}
