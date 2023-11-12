package edu.hw3.Task5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("HideUtilityClassConstructor")
public class ContactParser {
    public static List<Contact> parseContacts(String[] namesAndSurnames, SortType sortType) {
        if (isArrayEmpty(namesAndSurnames)) {
            return List.of();
        }

        List<Contact> contacts = getContactsList(namesAndSurnames);

        sortContacts(contacts, sortType);

        return contacts;
    }

    private static boolean isArrayEmpty(String[] array) {
        return array == null || array.length == 0;
    }

    private static List<Contact> getContactsList(String[] namesAndSurnames) {
        List<Contact> contacts = new ArrayList<>(namesAndSurnames.length);

        for (String nameAndSurnameString : namesAndSurnames) {

            String[] nameAndSurname = nameAndSurnameString.split(" ");

            Contact contact = switch (nameAndSurname.length) {
                case 1 -> new Contact(nameAndSurname[0]);
                case 2 -> new Contact(nameAndSurname[0], nameAndSurname[1]);
                default -> throw new IllegalArgumentException();
            };
            contacts.add(contact);
        }

        return contacts;
    }

    private static void sortContacts(List<Contact> contacts, SortType sortType) {
        Comparator<Contact> comparator = switch (sortType) {
            case SortType.ASC -> Comparator.naturalOrder();
            case SortType.DESC -> Comparator.reverseOrder();
        };

        contacts.sort(comparator);
    }
}
