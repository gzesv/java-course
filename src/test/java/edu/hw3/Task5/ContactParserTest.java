package edu.hw3.Task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ContactParserTest {
    @Test
    @DisplayName("Пустой исходный массив")
    void arrayEmpty_test() {
        String[] namesAndSurnames = {};
        SortType sortType = SortType.ASC;
        List<Contact> expected = List.of();

        List<Contact> contacts = ContactParser.parseContacts(namesAndSurnames, sortType);

        assertThat(contacts).isEqualTo(expected);
    }

    @Test
    @DisplayName("Исходный массив = null")
    void arrayIsNull_test() {
        String[] namesAndSurnames = null;
        SortType sortType = SortType.DESC;
        List<Contact> expected = List.of();

        List<Contact> contacts = ContactParser.parseContacts(namesAndSurnames, sortType);

        assertThat(contacts).isEqualTo(expected);
    }

    @Test
    @DisplayName("Сортировка по возрастанию")
    void parseContactsAsc_test() {
        String[] contactsList = new String[] {
            "John Locke",
            "Thomas Aquinas",
            "David Hume",
            "Rene Descartes"
        };
        SortType sortType = SortType.ASC;
        List<Contact> expected =  List.of(
            new Contact("Thomas", "Aquinas"),
            new Contact("Rene", "Descartes"),
            new Contact("David", "Hume"),
            new Contact("John", "Locke")
        );

        List<Contact> contacts = ContactParser.parseContacts(contactsList, sortType);
        assertThat(contacts).isNotNull().isEqualTo(expected);
    }

    @Test
    @DisplayName("Сортировка по убыванию")
    void parseContactsDesc_test() {
        String[] contactsList = new String[] {
            "John Locke",
            "Thomas Aquinas",
            "David Hume",
            "Rene Descartes"
        };
        SortType sortType = SortType.DESC;
        List<Contact> expected =  List.of(
            new Contact("John", "Locke"),
            new Contact("David", "Hume"),
            new Contact("Rene", "Descartes"),
            new Contact("Thomas", "Aquinas")
        );

        List<Contact> contacts = ContactParser.parseContacts(contactsList, sortType);

        assertThat(contacts).isNotNull().isEqualTo(expected);
    }

    @Test
    @DisplayName("Сортировка по возрастанию нет фамилии")
    void parseContactsDesc_noSurname_test() {
        String[] contactsList = {"Paul Erdos", "Leonhard Euler", "Carl"};
        SortType sortType = SortType.ASC;
        List<Contact> expected = List.of(
            new Contact("Carl", null),
            new Contact("Paul", "Erdos"),
            new Contact("Leonhard", "Euler")
        );

        List<Contact> contacts = ContactParser.parseContacts(contactsList, sortType);

        assertThat(contacts).isEqualTo(expected);
    }
}
