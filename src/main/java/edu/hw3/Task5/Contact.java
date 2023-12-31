package edu.hw3.Task5;

import java.util.Objects;

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
    public int compareTo(Contact o) {
        if (this.surname == null || o.surname == null) {
            return this.name.compareTo(o.name);
        }

        return this.surname.compareTo(o.surname);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Contact contact = (Contact) o;

        if (!Objects.equals(name, contact.name)) {
            return false;
        }
        return Objects.equals(surname, contact.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }
}
