package edu.hw4;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ValidationError {
    private final String message;
    private String field;
    public final static String AGE_ERROR = "Некорректный возраст";
    public final static String NAME_ERROR = "Некорректное имя";
    public final static String WEIGHT_ERROR = "Некорректный вес";
    public final static String HEIGHT_ERROR = "Некорректный рост";

    public ValidationError(String message) {
        this.message = message;
    }

    public ValidationError(String message, String field) {
        this.message = message;
        this.field = field;
    }

    public static Set<ValidationError> validateAnimal(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();

        if (animal.age() <= 0) {
            errors.add(new ValidationError(AGE_ERROR));
        }

        if (animal.name().isEmpty()) {
            errors.add(new ValidationError(NAME_ERROR));
        }

        if (animal.weight() <= 0) {
            errors.add(new ValidationError(WEIGHT_ERROR));
        }

        if (animal.height() <= 0) {
            errors.add(new ValidationError(HEIGHT_ERROR));
        }

        return errors;
    }

    public static String validateAnimalImproved(Animal animal) {
        StringBuilder errors = new StringBuilder();

        if (animal.age() <= 0) {
            errors.append(new ValidationError(AGE_ERROR, "age"));
        }

        if (animal.name().isEmpty()) {
            errors.append(new ValidationError(NAME_ERROR, "name"));
        }

        if (animal.weight() <= 0) {
            errors.append(new ValidationError(WEIGHT_ERROR, "weight"));
        }

        if (animal.height() <= 0) {
            errors.append(new ValidationError(HEIGHT_ERROR, "height"));
        }

        return errors.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ValidationError error = (ValidationError) o;

        if (!Objects.equals(message, error.message)) {
            return false;
        }
        return Objects.equals(field, error.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, field);
    }

    @Override
    public String toString() {
        return "ValidationError{"
            + "message='" + message + '\''
            + ", field='" + field + '\''
            + '}';
    }
}
