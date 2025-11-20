package com.flowly4j.example;

import java.time.Instant;
import java.util.Objects;

public final class Person {
    private final String name;
    private final Integer age;
    private final Instant bla;

    private Person(String name, Integer age, Instant bla) {
        this.name = name;
        this.age = age;
        this.bla = bla;
    }

    public static Person of(String name, Integer age, Instant bla) {
        return new Person(name, age, bla);
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Instant getBla() {
        return bla;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(age, person.age) &&
                Objects.equals(bla, person.bla);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, bla);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", bla=" + bla +
                '}';
    }
}

