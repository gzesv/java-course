package edu.hw4;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnimalTest {

    private static Stream<Arguments> animals() {
        return Stream.of(
            Arguments.of(new Animal("1", Animal.Type.DOG, Animal.Sex.MALE, 4, 99, 17000, true), 4),
            Arguments.of(new Animal("1", Animal.Type.CAT, Animal.Sex.MALE, 4, 99, 17000, true), 4),
            Arguments.of(new Animal("1", Animal.Type.BIRD, Animal.Sex.MALE, 4, 99, 17000, true), 2),
            Arguments.of(new Animal("1", Animal.Type.FISH, Animal.Sex.MALE, 4, 99, 17000, true), 0),
            Arguments.of(new Animal("1", Animal.Type.SPIDER, Animal.Sex.MALE, 4, 99, 17000, true), 8)
        );
    }

    @ParameterizedTest
    @MethodSource("animals")
    @DisplayName("Количество лап животных")
    void paws_test(Animal animal, int expectedPawsCount) {
        int pawsCount = animal.paws();

        assertThat(pawsCount).isEqualTo(expectedPawsCount);
    }

    @Test
    void name_test() {
        Animal animal = new Animal("1", Animal.Type.DOG, Animal.Sex.MALE, 4, 99, 17000, true);

        String name = animal.name();

        assertThat(name).isEqualTo("1");
    }

    @Test
    void type_test() {
        Animal animal = new Animal("1", Animal.Type.DOG, Animal.Sex.MALE, 4, 99, 17000, true);

        Animal.Type type = animal.type();

        assertThat(type).isEqualTo(Animal.Type.DOG);
    }

    @Test
    void sex_test() {
        Animal animal = new Animal("1", Animal.Type.DOG, Animal.Sex.MALE, 4, 99, 17000, true);

        Animal.Sex sex = animal.sex();

        assertThat(sex).isEqualTo(Animal.Sex.MALE);
    }

    @Test
    void age_test() {
        Animal animal = new Animal("1", Animal.Type.DOG, Animal.Sex.MALE, 4, 99, 17000, true);

        int age = animal.age();

        assertThat(age).isEqualTo(4);
    }

    @Test
    void height_test() {
        Animal animal = new Animal("1", Animal.Type.DOG, Animal.Sex.MALE, 4, 99, 17000, true);

        int height = animal.height();

        assertThat(height).isEqualTo(99);
    }

    @Test
    void weight() {
        Animal animal = new Animal("1", Animal.Type.DOG, Animal.Sex.MALE, 4, 99, 17000, true);

        int weight = animal.weight();

        assertThat(weight).isEqualTo(17000);
    }

    @Test
    void bites_test() {
        Animal animal = new Animal("1", Animal.Type.DOG, Animal.Sex.MALE, 4, 99, 17000, true);

        boolean bites = animal.bites();

        assertThat(bites).isTrue();
    }
}
