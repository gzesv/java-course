package edu.hw3.Task5;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public class Contact implements Comparable<Contact> {
    private String name;
    private String surname;

    public Contact(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Contact(String name) {
        this(name, null);
    }

    @Override
    public int compareTo(@NotNull Contact o) {
        if (this.surname == null || o.surname == null) {
            return this.name.compareTo(o.name);
        }

        return this.surname.compareTo(o.surname);
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        return hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }
}
