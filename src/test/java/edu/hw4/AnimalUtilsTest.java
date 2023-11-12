package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.ValidationError.AGE_ERROR;
import static edu.hw4.ValidationError.HEIGHT_ERROR;
import static edu.hw4.ValidationError.NAME_ERROR;
import static edu.hw4.ValidationError.WEIGHT_ERROR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnimalUtilsTest {
    @Test
    @DisplayName("Сортировка по росту по возрастанию")
    void sortByHeightAsc_test() {
        List<Animal> animals = List.of(
            new Animal("Fish Male", Animal.Type.FISH, Animal.Sex.MALE, 2, 3, 50, false),
            new Animal("Cat Female", Animal.Type.CAT, Animal.Sex.FEMALE, 10, 27, 7000, false),
            new Animal("Dog Male", Animal.Type.DOG, Animal.Sex.MALE, 5, 40, 17000, false),
            new Animal("Bird Male", Animal.Type.BIRD, Animal.Sex.MALE, 3, 5, 500, false),
            new Animal("Spider Male", Animal.Type.SPIDER, Animal.Sex.MALE, 2, 1, 10, false)
        );

        List<Animal> animalsSorted = AnimalUtils.sortByHeightAsc(animals);

        assertThat(animalsSorted)
            .isEqualTo(List.of(
                new Animal("Spider Male", Animal.Type.SPIDER, Animal.Sex.MALE, 2, 1, 10, false),
                new Animal("Fish Male", Animal.Type.FISH, Animal.Sex.MALE, 2, 3, 50, false),
                new Animal("Bird Male", Animal.Type.BIRD, Animal.Sex.MALE, 3, 5, 500, false),
                new Animal("Cat Female", Animal.Type.CAT, Animal.Sex.FEMALE, 10, 27, 7000, false),
                new Animal("Dog Male", Animal.Type.DOG, Animal.Sex.MALE, 5, 40, 17000, false)
            ));
    }

    @Test
    @DisplayName("Сортировка по весу по убыванию")
    void sortByWeightWithLimitDesc_test() {
        List<Animal> animals = List.of(
            new Animal("Fish Male", Animal.Type.FISH, Animal.Sex.MALE, 2, 3, 50, false),
            new Animal("Cat Female", Animal.Type.CAT, Animal.Sex.FEMALE, 10, 27, 7000, false),
            new Animal("Dog Male", Animal.Type.DOG, Animal.Sex.MALE, 5, 40, 17000, false),
            new Animal("Bird Male", Animal.Type.BIRD, Animal.Sex.MALE, 3, 5, 500, false),
            new Animal("Spider Male", Animal.Type.SPIDER, Animal.Sex.MALE, 2, 1, 10, false)
        );
        int limit = 3;

        List<Animal> animalsSorted = AnimalUtils.sortByWeightWithLimitDesc(animals, limit);

        assertThat(animalsSorted)
            .isEqualTo(List.of(
                new Animal("Dog Male", Animal.Type.DOG, Animal.Sex.MALE, 5, 40, 17000, false),
                new Animal("Cat Female", Animal.Type.CAT, Animal.Sex.FEMALE, 10, 27, 7000, false),
                new Animal("Bird Male", Animal.Type.BIRD, Animal.Sex.MALE, 3, 5, 500, false)
            ));
    }

    @Test
    @DisplayName("Количество животных каждого вида")
    void countAnimalByTypes_test() {
        List<Animal> animals = List.of(
            new Animal("Fish Male", Animal.Type.FISH, Animal.Sex.MALE, 2, 3, 50, false),
            new Animal("Fish FEMALE", Animal.Type.FISH, Animal.Sex.FEMALE, 2, 3, 50, false),
            new Animal("Cat Female", Animal.Type.CAT, Animal.Sex.FEMALE, 10, 27, 7000, false),
            new Animal("Dog MaleSecond", Animal.Type.DOG, Animal.Sex.MALE, 5, 40, 17000, false),
            new Animal("Dog MaleFirst", Animal.Type.DOG, Animal.Sex.MALE, 5, 40, 17000, false),
            new Animal("Bird Male", Animal.Type.BIRD, Animal.Sex.MALE, 3, 5, 500, false),
            new Animal("Spider Male", Animal.Type.SPIDER, Animal.Sex.MALE, 2, 1, 10, false)
        );

        Map<Animal.Type, Long> animalsSorted = AnimalUtils.countAnimalByTypes(animals);

        assertThat(animalsSorted)
            .isEqualTo(Map.of(
                Animal.Type.CAT, 1L,
                Animal.Type.DOG, 2L,
                Animal.Type.BIRD, 1L,
                Animal.Type.FISH, 2L,
                Animal.Type.SPIDER, 1L
            ));
    }

    @Test
    @DisplayName("Животное с самыым длинным именем")
    void longestAnimalName_test() {
        List<Animal> animals = List.of(
            new Animal("Fish Male", Animal.Type.FISH, Animal.Sex.MALE, 2, 3, 50, false),
            new Animal("Fish FEMALE", Animal.Type.FISH, Animal.Sex.FEMALE, 2, 3, 50, false),
            new Animal("Cat Female", Animal.Type.CAT, Animal.Sex.FEMALE, 10, 27, 7000, false),
            new Animal("Dog MaleSecond", Animal.Type.DOG, Animal.Sex.MALE, 5, 40, 17000, false),
            new Animal("Dog MaleFirst", Animal.Type.DOG, Animal.Sex.MALE, 5, 40, 17000, false),
            new Animal("Bird Male", Animal.Type.BIRD, Animal.Sex.MALE, 3, 5, 500, false),
            new Animal("Spider Male", Animal.Type.SPIDER, Animal.Sex.MALE, 2, 1, 10, false)
        );

        Animal animal = AnimalUtils.longestAnimalName(animals);

        assertThat(animal)
            .isNotNull()
            .isEqualTo(new Animal("Dog MaleSecond", Animal.Type.DOG, Animal.Sex.MALE, 5, 40, 17000, false));
    }

    @Test
    @DisplayName("Каких животных больше: самцов или самок?")
    void mostCommonSex_test() {
        List<Animal> animals = List.of(
            new Animal("Fish Male", Animal.Type.FISH, Animal.Sex.MALE, 2, 3, 50, false),
            new Animal("Fish FEMALE", Animal.Type.FISH, Animal.Sex.FEMALE, 2, 3, 50, false),
            new Animal("Cat Female", Animal.Type.CAT, Animal.Sex.FEMALE, 10, 27, 7000, false),
            new Animal("Dog MaleSecond", Animal.Type.DOG, Animal.Sex.MALE, 5, 40, 17000, false),
            new Animal("Dog MaleFirst", Animal.Type.DOG, Animal.Sex.MALE, 5, 40, 17000, false),
            new Animal("Bird Male", Animal.Type.BIRD, Animal.Sex.MALE, 3, 5, 500, false),
            new Animal("Spider Male", Animal.Type.SPIDER, Animal.Sex.MALE, 2, 1, 10, false)
        );

        Animal.Sex commonSex = AnimalUtils.mostCommonSex(animals);

        assertThat(commonSex)
            .isNotNull()
            .isEqualTo(Animal.Sex.MALE);
    }

    @Test
    @DisplayName("Самое тяжелое животное каждого вида")
    void heaviestAnimalsByTypes_test() {
        List<Animal> animals = List.of(
            new Animal("Fish Male", Animal.Type.FISH, Animal.Sex.MALE, 2, 3, 53, false),
            new Animal("Fish FEMALE", Animal.Type.FISH, Animal.Sex.FEMALE, 2, 3, 50, false),
            new Animal("Cat Female", Animal.Type.CAT, Animal.Sex.FEMALE, 10, 27, 7000, false),
            new Animal("Cat Male", Animal.Type.CAT, Animal.Sex.MALE, 10, 27, 8000, false),
            new Animal("Dog MaleSecond", Animal.Type.DOG, Animal.Sex.MALE, 5, 40, 17000, false),
            new Animal("Dog MaleFirst", Animal.Type.DOG, Animal.Sex.MALE, 5, 40, 18000, false),
            new Animal("Bird Male", Animal.Type.BIRD, Animal.Sex.MALE, 3, 5, 550, false),
            new Animal("Bird FEMALE", Animal.Type.BIRD, Animal.Sex.FEMALE, 3, 5, 500, false),
            new Animal("Spider Male", Animal.Type.SPIDER, Animal.Sex.MALE, 2, 1, 11, false),
            new Animal("Spider FEMALE", Animal.Type.SPIDER, Animal.Sex.FEMALE, 2, 1, 10, false)
        );

        Map<Animal.Type, Animal> heaviestAnimals = AnimalUtils.heaviestAnimalsByTypes(animals);

        assertThat(heaviestAnimals)
            .isEqualTo(Map.of(
                Animal.Type.CAT, new Animal("Cat Male", Animal.Type.CAT, Animal.Sex.MALE, 10, 27, 8000, false),
                Animal.Type.DOG, new Animal("Dog MaleFirst", Animal.Type.DOG, Animal.Sex.MALE, 5, 40, 18000, false),
                Animal.Type.BIRD, new Animal("Bird Male", Animal.Type.BIRD, Animal.Sex.MALE, 3, 5, 550, false),
                Animal.Type.FISH, new Animal("Fish Male", Animal.Type.FISH, Animal.Sex.MALE, 2, 3, 53, false),
                Animal.Type.SPIDER, new Animal("Spider Male", Animal.Type.SPIDER, Animal.Sex.MALE, 2, 1, 11, false)
            ));
    }

    @Test
    @DisplayName("K-е самое старое животное")
    void oldestAnimal_test() {
        List<Animal> animals = List.of(
            new Animal("Fish Male", Animal.Type.FISH, Animal.Sex.MALE, 2, 3, 53, false),
            new Animal("Fish FEMALE", Animal.Type.FISH, Animal.Sex.FEMALE, 6, 3, 50, false),
            new Animal("Cat Female", Animal.Type.CAT, Animal.Sex.FEMALE, 11, 27, 7000, false),
            new Animal("Cat Male", Animal.Type.CAT, Animal.Sex.MALE, 12, 27, 8000, false),
            new Animal("Dog MaleSecond", Animal.Type.DOG, Animal.Sex.MALE, 7, 40, 17000, false),
            new Animal("Dog MaleFirst", Animal.Type.DOG, Animal.Sex.MALE, 8, 40, 18000, false),
            new Animal("Bird Male", Animal.Type.BIRD, Animal.Sex.MALE, 3, 5, 550, false),
            new Animal("Bird FEMALE", Animal.Type.BIRD, Animal.Sex.FEMALE, 3, 5, 500, false),
            new Animal("Spider Male", Animal.Type.SPIDER, Animal.Sex.MALE, 2, 1, 11, false),
            new Animal("Spider FEMALE", Animal.Type.SPIDER, Animal.Sex.FEMALE, 4, 1, 10, false)
        );

        Animal oldestAnimal = AnimalUtils.oldestAnimal(animals, 1);

        assertThat(oldestAnimal)
            .isNotNull()
            .isEqualTo(new Animal("Cat Male", Animal.Type.CAT, Animal.Sex.MALE, 12, 27, 8000, false));
    }

    @Test
    @DisplayName("Самое тяжелое животное среди животных ниже k см")
    void heaviestAnimalWithHeightBelowK_test() {
        List<Animal> animals = List.of(
            new Animal("Fish Male", Animal.Type.FISH, Animal.Sex.MALE, 2, 3, 53, false),
            new Animal("Fish FEMALE", Animal.Type.FISH, Animal.Sex.FEMALE, 6, 3, 50, false),
            new Animal("Cat Female", Animal.Type.CAT, Animal.Sex.FEMALE, 11, 27, 7000, false),
            new Animal("Cat Male", Animal.Type.CAT, Animal.Sex.MALE, 12, 28, 8000, false),
            new Animal("Dog MaleSecond", Animal.Type.DOG, Animal.Sex.MALE, 7, 40, 17000, false),
            new Animal("Dog MaleFirst", Animal.Type.DOG, Animal.Sex.MALE, 8, 42, 18000, false),
            new Animal("Bird Male", Animal.Type.BIRD, Animal.Sex.MALE, 3, 5, 550, false),
            new Animal("Bird FEMALE", Animal.Type.BIRD, Animal.Sex.FEMALE, 3, 4, 500, false),
            new Animal("Spider Male", Animal.Type.SPIDER, Animal.Sex.MALE, 2, 1, 11, false),
            new Animal("Spider FEMALE", Animal.Type.SPIDER, Animal.Sex.FEMALE, 4, 1, 10, false)
        );

        Animal heaviestAnimal = AnimalUtils.heaviestAnimalWithHeightBelowK(animals, 5).get();

        assertThat(heaviestAnimal)
            .isEqualTo(new Animal("Bird FEMALE", Animal.Type.BIRD, Animal.Sex.FEMALE, 3, 4, 500, false));
    }

    @Test
    @DisplayName("Количество лап в сумме у животных в списке")
    void countPawsAllAnimals_test() {
        List<Animal> animals = List.of(
            new Animal("Fish Male", Animal.Type.FISH, Animal.Sex.MALE, 2, 3, 53, false),
            new Animal("Fish FEMALE", Animal.Type.FISH, Animal.Sex.FEMALE, 6, 3, 50, false),
            new Animal("Cat Female", Animal.Type.CAT, Animal.Sex.FEMALE, 11, 27, 7000, false),
            new Animal("Cat Male", Animal.Type.CAT, Animal.Sex.MALE, 12, 28, 8000, false),
            new Animal("Dog MaleSecond", Animal.Type.DOG, Animal.Sex.MALE, 7, 40, 17000, false),
            new Animal("Dog MaleFirst", Animal.Type.DOG, Animal.Sex.MALE, 8, 42, 18000, false),
            new Animal("Bird Male", Animal.Type.BIRD, Animal.Sex.MALE, 3, 5, 550, false),
            new Animal("Bird FEMALE", Animal.Type.BIRD, Animal.Sex.FEMALE, 3, 4, 500, false),
            new Animal("Spider Male", Animal.Type.SPIDER, Animal.Sex.MALE, 2, 1, 11, false),
            new Animal("Spider FEMALE", Animal.Type.SPIDER, Animal.Sex.FEMALE, 4, 1, 10, false)
        );

        int pawsCount = AnimalUtils.countPawsAllAnimals(animals);

        assertThat(pawsCount)
            .isEqualTo(36);
    }

    @Test
    @DisplayName("Список животных, возраст у которых не совпадает с количеством лап")
    void animalsWithAgeNotEqualToPaws_test() {
        List<Animal> animals = List.of(
            new Animal("Fish Male", Animal.Type.FISH, Animal.Sex.MALE, 2, 3, 53, false),
            new Animal("Fish FEMALE", Animal.Type.FISH, Animal.Sex.FEMALE, 6, 3, 50, false),
            new Animal("Cat Female", Animal.Type.CAT, Animal.Sex.FEMALE, 11, 27, 7000, false),
            new Animal("Cat Male", Animal.Type.CAT, Animal.Sex.MALE, 4, 28, 8000, false),
            new Animal("Dog MaleSecond", Animal.Type.DOG, Animal.Sex.MALE, 4, 40, 17000, false),
            new Animal("Dog MaleFirst", Animal.Type.DOG, Animal.Sex.MALE, 4, 42, 18000, false),
            new Animal("Bird Male", Animal.Type.BIRD, Animal.Sex.MALE, 2, 5, 550, false),
            new Animal("Bird FEMALE", Animal.Type.BIRD, Animal.Sex.FEMALE, 3, 4, 500, false),
            new Animal("Spider Male", Animal.Type.SPIDER, Animal.Sex.MALE, 2, 1, 11, false),
            new Animal("Spider FEMALE", Animal.Type.SPIDER, Animal.Sex.FEMALE, 8, 1, 10, false)
        );

        List<Animal> animalsActual = AnimalUtils.animalsWithAgeNotEqualToPaws(animals);

        assertThat(animalsActual)
            .isEqualTo(List.of(
                new Animal("Fish Male", Animal.Type.FISH, Animal.Sex.MALE, 2, 3, 53, false),
                new Animal("Fish FEMALE", Animal.Type.FISH, Animal.Sex.FEMALE, 6, 3, 50, false),
                new Animal("Cat Female", Animal.Type.CAT, Animal.Sex.FEMALE, 11, 27, 7000, false),
                new Animal("Bird FEMALE", Animal.Type.BIRD, Animal.Sex.FEMALE, 3, 4, 500, false),
                new Animal("Spider Male", Animal.Type.SPIDER, Animal.Sex.MALE, 2, 1, 11, false)
            ));
    }

    @Test
    @DisplayName("Животные, которые кусаются и выше метра")
    void animalsThatBitesAndHigherThanMeter_test() {
        List<Animal> animals = List.of(
            new Animal("Fish Male", Animal.Type.FISH, Animal.Sex.MALE, 2, 3, 53, true),
            new Animal("Fish FEMALE", Animal.Type.FISH, Animal.Sex.FEMALE, 6, 3, 50, true),
            new Animal("Cat Female", Animal.Type.CAT, Animal.Sex.FEMALE, 11, 27, 7000, true),
            new Animal("Cat Male", Animal.Type.CAT, Animal.Sex.MALE, 4, 28, 8000, true),
            new Animal("Dog MaleSecond", Animal.Type.DOG, Animal.Sex.MALE, 4, 99, 17000, true),
            new Animal("Dog MaleFirst", Animal.Type.DOG, Animal.Sex.MALE, 4, 104, 18000, true),
            new Animal("Dog FEMALEFirst", Animal.Type.DOG, Animal.Sex.FEMALE, 4, 103, 18000, true),
            new Animal("Dog FEMALESecond", Animal.Type.DOG, Animal.Sex.FEMALE, 4, 101, 18000, true),
            new Animal("Bird Male", Animal.Type.BIRD, Animal.Sex.MALE, 2, 5, 550, true),
            new Animal("Bird FEMALE", Animal.Type.BIRD, Animal.Sex.FEMALE, 3, 4, 500, true),
            new Animal("Spider Male", Animal.Type.SPIDER, Animal.Sex.MALE, 2, 1, 11, true),
            new Animal("Spider FEMALE", Animal.Type.SPIDER, Animal.Sex.FEMALE, 8, 1, 10, true)
        );

        List<Animal> animalsActual = AnimalUtils.animalsThatBitesAndHigherThanMeter(animals);

        assertThat(animalsActual)
            .isEqualTo(List.of(
                new Animal("Dog MaleFirst", Animal.Type.DOG, Animal.Sex.MALE, 4, 104, 18000, true),
                new Animal("Dog FEMALEFirst", Animal.Type.DOG, Animal.Sex.FEMALE, 4, 103, 18000, true),
                new Animal("Dog FEMALESecond", Animal.Type.DOG, Animal.Sex.FEMALE, 4, 101, 18000, true)
            ));
    }

    @Test
    @DisplayName("Количество животных, вес которых превышает рост")
    void countAnimalsWithWeightMoreThanHeight_test() {
        List<Animal> animals = List.of(
            new Animal("Fish Male", Animal.Type.FISH, Animal.Sex.MALE, 2, 3, 2, true),
            new Animal("Fish FEMALE", Animal.Type.FISH, Animal.Sex.FEMALE, 6, 3, 1, true),
            new Animal("Cat Female", Animal.Type.CAT, Animal.Sex.FEMALE, 11, 27, 7000, true),
            new Animal("Cat Male", Animal.Type.CAT, Animal.Sex.MALE, 4, 28, 8000, true),
            new Animal("Dog MaleSecond", Animal.Type.DOG, Animal.Sex.MALE, 4, 99, 17000, true),
            new Animal("Dog MaleFirst", Animal.Type.DOG, Animal.Sex.MALE, 4, 104, 18000, true),
            new Animal("Dog FEMALEFirst", Animal.Type.DOG, Animal.Sex.FEMALE, 4, 103, 18000, true),
            new Animal("Dog FEMALESecond", Animal.Type.DOG, Animal.Sex.FEMALE, 4, 101, 18000, true),
            new Animal("Bird Male", Animal.Type.BIRD, Animal.Sex.MALE, 2, 100, 550, true),
            new Animal("Bird FEMALE", Animal.Type.BIRD, Animal.Sex.FEMALE, 3, 4, 500, true),
            new Animal("Spider Male", Animal.Type.SPIDER, Animal.Sex.MALE, 2, 12, 11, true),
            new Animal("Spider FEMALE", Animal.Type.SPIDER, Animal.Sex.FEMALE, 8, 1, 10, true)
        );

        Long count = AnimalUtils.countAnimalsWithWeightMoreThanHeight(animals);

        assertThat(count)
            .isEqualTo(9L);
    }

    @Test
    @DisplayName("Животные с именами, состоящими из более чем двух слов")
    void animalsWithMoreThanTwoWordNames_test() {
        List<Animal> animals = List.of(
            new Animal("Fish Male", Animal.Type.FISH, Animal.Sex.MALE, 2, 3, 2, true),
            new Animal("Fish FEMALE", Animal.Type.FISH, Animal.Sex.FEMALE, 6, 3, 1, true),
            new Animal("Cat Female", Animal.Type.CAT, Animal.Sex.FEMALE, 11, 27, 7000, true),
            new Animal("Cat Male", Animal.Type.CAT, Animal.Sex.MALE, 4, 28, 8000, true),
            new Animal("Dog Male Second", Animal.Type.DOG, Animal.Sex.MALE, 4, 99, 17000, true),
            new Animal("Dog Male First", Animal.Type.DOG, Animal.Sex.MALE, 4, 104, 18000, true),
            new Animal("Dog FEMALE First", Animal.Type.DOG, Animal.Sex.FEMALE, 4, 103, 18000, true),
            new Animal("Dog FEMALE Second", Animal.Type.DOG, Animal.Sex.FEMALE, 4, 101, 18000, true),
            new Animal("Bird Male", Animal.Type.BIRD, Animal.Sex.MALE, 2, 100, 550, true),
            new Animal("Bird FEMALE", Animal.Type.BIRD, Animal.Sex.FEMALE, 3, 4, 500, true),
            new Animal("Spider Male", Animal.Type.SPIDER, Animal.Sex.MALE, 2, 12, 11, true),
            new Animal("Spider FEMALE", Animal.Type.SPIDER, Animal.Sex.FEMALE, 8, 1, 10, true)
        );

        List<Animal> animalsActual = AnimalUtils.animalsWithMoreThanTwoWordNames(animals);

        assertThat(animalsActual)
            .isEqualTo(List.of(
                new Animal("Dog Male Second", Animal.Type.DOG, Animal.Sex.MALE, 4, 99, 17000, true),
                new Animal("Dog Male First", Animal.Type.DOG, Animal.Sex.MALE, 4, 104, 18000, true),
                new Animal("Dog FEMALE First", Animal.Type.DOG, Animal.Sex.FEMALE, 4, 103, 18000, true),
                new Animal("Dog FEMALE Second", Animal.Type.DOG, Animal.Sex.FEMALE, 4, 101, 18000, true)
            ));
    }

    @Test
    @DisplayName("Есть ли в списке собака ростом более k см")
    void hasDogHigherThanK_test() {
        List<Animal> animals = List.of(
            new Animal("Fish Male", Animal.Type.FISH, Animal.Sex.MALE, 2, 3, 2, true),
            new Animal("Fish FEMALE", Animal.Type.FISH, Animal.Sex.FEMALE, 6, 3, 1, true),
            new Animal("Cat Female", Animal.Type.CAT, Animal.Sex.FEMALE, 11, 27, 7000, true),
            new Animal("Cat Male", Animal.Type.CAT, Animal.Sex.MALE, 4, 28, 8000, true),
            new Animal("Dog Male Second", Animal.Type.DOG, Animal.Sex.MALE, 4, 99, 17000, true),
            new Animal("Dog Male First", Animal.Type.DOG, Animal.Sex.MALE, 4, 104, 18000, true),
            new Animal("Dog FEMALE First", Animal.Type.DOG, Animal.Sex.FEMALE, 4, 103, 18000, true),
            new Animal("Dog FEMALE Second", Animal.Type.DOG, Animal.Sex.FEMALE, 4, 101, 18000, true),
            new Animal("Bird Male", Animal.Type.BIRD, Animal.Sex.MALE, 2, 100, 550, true),
            new Animal("Bird FEMALE", Animal.Type.BIRD, Animal.Sex.FEMALE, 3, 4, 500, true),
            new Animal("Spider Male", Animal.Type.SPIDER, Animal.Sex.MALE, 2, 12, 11, true),
            new Animal("Spider FEMALE", Animal.Type.SPIDER, Animal.Sex.FEMALE, 8, 1, 10, true)
        );

        Boolean result = AnimalUtils.hasDogHigherThanK(animals, 103);

        assertThat(result)
            .isTrue();

        result = AnimalUtils.hasDogHigherThanK(animals, 104);

        assertThat(result)
            .isFalse();
    }

    @Test
    @DisplayName("Найти суммарный вес животных каждого вида, которым от k до l лет")
    void sumWeightByTypeBetweenKL_test() {
        List<Animal> animals = List.of(
            new Animal("Fish Male", Animal.Type.FISH, Animal.Sex.MALE, 2, 3, 2, true),
            new Animal("Fish FEMALE", Animal.Type.FISH, Animal.Sex.FEMALE, 6, 3, 1, true),
            new Animal("Cat Female", Animal.Type.CAT, Animal.Sex.FEMALE, 11, 27, 7000, true),
            new Animal("Cat Male", Animal.Type.CAT, Animal.Sex.MALE, 4, 28, 8000, true),
            new Animal("Dog Male Second", Animal.Type.DOG, Animal.Sex.MALE, 4, 99, 17000, true),
            new Animal("Dog Male First", Animal.Type.DOG, Animal.Sex.MALE, 4, 104, 18000, true),
            new Animal("Dog FEMALE First", Animal.Type.DOG, Animal.Sex.FEMALE, 4, 103, 18000, true),
            new Animal("Dog FEMALE Second", Animal.Type.DOG, Animal.Sex.FEMALE, 4, 101, 18000, true),
            new Animal("Bird Male", Animal.Type.BIRD, Animal.Sex.MALE, 2, 100, 550, true),
            new Animal("Bird FEMALE", Animal.Type.BIRD, Animal.Sex.FEMALE, 5, 4, 500, true),
            new Animal("Spider Male", Animal.Type.SPIDER, Animal.Sex.MALE, 2, 12, 11, true),
            new Animal("Spider FEMALE", Animal.Type.SPIDER, Animal.Sex.FEMALE, 3, 1, 10, true)
        );

        Map<Animal.Type, Long> animalsActual = AnimalUtils.sumWeightByTypeBetweenKL(animals, 4, 7);

        assertThat(animalsActual)
            .isEqualTo(Map.of(
                Animal.Type.CAT, 1L,
                Animal.Type.DOG, 4L,
                Animal.Type.BIRD, 1L,
                Animal.Type.FISH, 1L
            ));
    }

    @Test
    @DisplayName("Список животных, отсортированный по виду, затем по полу, затем по имени")
    void sortByTypeThenSexThenName_test() {
        List<Animal> animals = List.of(
            new Animal("Fish Male", Animal.Type.FISH, Animal.Sex.MALE, 2, 3, 2, true),
            new Animal("Fish FEMALE", Animal.Type.FISH, Animal.Sex.FEMALE, 6, 3, 1, true),
            new Animal("Cat Female", Animal.Type.CAT, Animal.Sex.FEMALE, 11, 27, 7000, true),
            new Animal("Cat Male", Animal.Type.CAT, Animal.Sex.MALE, 4, 28, 8000, true),
            new Animal("Dog Male Second", Animal.Type.DOG, Animal.Sex.MALE, 4, 99, 17000, true),
            new Animal("Dog Male First", Animal.Type.DOG, Animal.Sex.MALE, 4, 104, 18000, true),
            new Animal("Dog FEMALE First", Animal.Type.DOG, Animal.Sex.FEMALE, 4, 103, 18000, true),
            new Animal("Dog FEMALE Second", Animal.Type.DOG, Animal.Sex.FEMALE, 4, 101, 18000, true),
            new Animal("Bird Male", Animal.Type.BIRD, Animal.Sex.MALE, 2, 100, 550, true),
            new Animal("Bird FEMALE", Animal.Type.BIRD, Animal.Sex.FEMALE, 5, 4, 500, true),
            new Animal("Spider Male", Animal.Type.SPIDER, Animal.Sex.MALE, 2, 12, 11, true),
            new Animal("Spider FEMALE", Animal.Type.SPIDER, Animal.Sex.FEMALE, 3, 1, 10, true)
        );

        List<Animal> animalsActual = AnimalUtils.sortByTypeThenSexThenName(animals);

        assertThat(animalsActual)
            .isEqualTo(List.of(
                new Animal("Cat Male", Animal.Type.CAT, Animal.Sex.MALE, 4, 28, 8000, true),
                new Animal("Cat Female", Animal.Type.CAT, Animal.Sex.FEMALE, 11, 27, 7000, true),
                new Animal("Dog Male First", Animal.Type.DOG, Animal.Sex.MALE, 4, 104, 18000, true),
                new Animal("Dog Male Second", Animal.Type.DOG, Animal.Sex.MALE, 4, 99, 17000, true),
                new Animal("Dog FEMALE First", Animal.Type.DOG, Animal.Sex.FEMALE, 4, 103, 18000, true),
                new Animal("Dog FEMALE Second", Animal.Type.DOG, Animal.Sex.FEMALE, 4, 101, 18000, true),
                new Animal("Bird Male", Animal.Type.BIRD, Animal.Sex.MALE, 2, 100, 550, true),
                new Animal("Bird FEMALE", Animal.Type.BIRD, Animal.Sex.FEMALE, 5, 4, 500, true),
                new Animal("Fish Male", Animal.Type.FISH, Animal.Sex.MALE, 2, 3, 2, true),
                new Animal("Fish FEMALE", Animal.Type.FISH, Animal.Sex.FEMALE, 6, 3, 1, true),
                new Animal("Spider Male", Animal.Type.SPIDER, Animal.Sex.MALE, 2, 12, 11, true),
                new Animal("Spider FEMALE", Animal.Type.SPIDER, Animal.Sex.FEMALE, 3, 1, 10, true)
            ));
    }

    @Test
    @DisplayName("Правда ли, что пауки кусаются чаще, чем собаки?")
    void isSpidersBitesOftenThanDogs_test() {
        List<Animal> animalsFalse = List.of(
            new Animal("Dog Male Second", Animal.Type.DOG, Animal.Sex.MALE, 4, 99, 17000, true),
            new Animal("Dog Male First", Animal.Type.DOG, Animal.Sex.MALE, 4, 104, 18000, true),
            new Animal("Dog FEMALE First", Animal.Type.DOG, Animal.Sex.FEMALE, 4, 103, 18000, true),
            new Animal("Dog FEMALE Second", Animal.Type.DOG, Animal.Sex.FEMALE, 4, 101, 18000, true),
            new Animal("Spider Male", Animal.Type.SPIDER, Animal.Sex.MALE, 2, 12, 11, true),
            new Animal("Spider FEMALE", Animal.Type.SPIDER, Animal.Sex.FEMALE, 3, 1, 10, true)
        );
        List<Animal> animalsTrue = List.of(
            new Animal("Dog FEMALE Second", Animal.Type.DOG, Animal.Sex.FEMALE, 4, 101, 18000, true),
            new Animal("Spider Male", Animal.Type.SPIDER, Animal.Sex.MALE, 2, 12, 11, true),
            new Animal("Spider FEMALE", Animal.Type.SPIDER, Animal.Sex.FEMALE, 3, 1, 10, true)
        );

        Boolean isSpiders = AnimalUtils.isSpidersBitesOftenThanDogs(animalsFalse);

        assertThat(isSpiders)
            .isFalse();

        isSpiders = AnimalUtils.isSpidersBitesOftenThanDogs(animalsTrue);

        assertThat(isSpiders)
            .isTrue();
    }

    @Test
    @DisplayName("Найти самую тяжелую рыбку в 2-х или более списках")
    void heaviestFishMoreThanInTwoLists_test() {
        List<List<Animal>> animals = List.of(
            List.of(
                new Animal("Bird FEMALE", Animal.Type.BIRD, Animal.Sex.FEMALE, 5, 4, 500, true),
                new Animal("Fish Male", Animal.Type.FISH, Animal.Sex.MALE, 2, 3, 5, true),
                new Animal("Fish FEMALE", Animal.Type.FISH, Animal.Sex.FEMALE, 6, 3, 1, true),
                new Animal("Spider Male", Animal.Type.SPIDER, Animal.Sex.MALE, 2, 12, 11, true)
            ),
            List.of(
                new Animal("Bird FEMALE", Animal.Type.BIRD, Animal.Sex.FEMALE, 5, 4, 500, true),
                new Animal("Fish Male", Animal.Type.FISH, Animal.Sex.MALE, 2, 3, 2, true),
                new Animal("Fish FEMALE", Animal.Type.FISH, Animal.Sex.FEMALE, 6, 33, 1, true),
                new Animal("Spider Male", Animal.Type.SPIDER, Animal.Sex.MALE, 2, 17, 11, true)
            ),
            List.of(
                new Animal("Bird FEMALE", Animal.Type.BIRD, Animal.Sex.FEMALE, 5, 4, 500, true),
                new Animal("Spider Male", Animal.Type.SPIDER, Animal.Sex.MALE, 2, 12, 11, true)
            ),
            List.of(
                new Animal("Dog FEMALE First", Animal.Type.DOG, Animal.Sex.FEMALE, 4, 103, 18000, true),
                new Animal("Dog FEMALE Second", Animal.Type.DOG, Animal.Sex.FEMALE, 4, 101, 18000, true)
            )
        );

        Animal animal = AnimalUtils.heaviestFishMoreThanInTwoLists(animals);

        assertThat(animal)
            .isEqualTo(new Animal("Fish Male", Animal.Type.FISH, Animal.Sex.MALE, 2, 3, 5, true));
    }

    @Test
    @DisplayName("Правда ли, что пауки кусаются чаще, чем собаки?")
    void validateAnimals_test() {
        List<Animal> animals = List.of(
            new Animal("", Animal.Type.DOG, Animal.Sex.MALE, 4, 99, 17000, true),
            new Animal("Dog Male First", Animal.Type.DOG, Animal.Sex.MALE, -1, 104, 18000, true),
            new Animal("Dog FEMALE First", Animal.Type.DOG, Animal.Sex.FEMALE, 4, 0, -121321, true),
            new Animal("Spider FEMALE", Animal.Type.SPIDER, Animal.Sex.FEMALE, 3, 1, 10, true)
        );

        Map<String, Set<ValidationError>> errors = AnimalUtils.validateAnimals(animals);

        assertThat(errors).isEqualTo(
            Map.of(
                "", Set.of(new ValidationError(NAME_ERROR)),
                "Dog Male First", Set.of(new ValidationError(AGE_ERROR)),
                "Dog FEMALE First", Set.of(
                    new ValidationError(HEIGHT_ERROR),
                    new ValidationError(WEIGHT_ERROR)
                ),
                "Spider FEMALE", Set.of()
            )
        );
    }

    @Test
    @DisplayName("Правда ли, что пауки кусаются чаще, чем собаки?")
    void validateAnimalsImproved_test() {
        List<Animal> animals = List.of(
            new Animal("", Animal.Type.DOG, Animal.Sex.MALE, 4, 99, 17000, true),
            new Animal("Dog Male First", Animal.Type.DOG, Animal.Sex.MALE, -1, 104, 18000, true),
            new Animal("Dog FEMALE First", Animal.Type.DOG, Animal.Sex.FEMALE, 4, 0, -121321, true),
            new Animal("Spider FEMALE", Animal.Type.SPIDER, Animal.Sex.FEMALE, 3, 1, 10, true)
        );

        Map<String, String> errors = AnimalUtils.validateAnimalsImproved(animals);

        assertThat(errors).isEqualTo(
            Map.of(
                "", String.valueOf(new ValidationError(NAME_ERROR, "name")),
                "Dog Male First", String.valueOf(new ValidationError(AGE_ERROR, "age")),
                "Dog FEMALE First", String.valueOf(new ValidationError(WEIGHT_ERROR, "weight"))
                    + String.valueOf(new ValidationError(HEIGHT_ERROR, "height")),
                "Spider FEMALE", ""
            )
        );
    }

}
