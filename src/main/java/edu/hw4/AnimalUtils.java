package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

@SuppressWarnings("HideUtilityClassConstructor")
public class AnimalUtils {

    public static List<Animal> sortByHeightAsc(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    public static List<Animal> sortByWeightWithLimitDesc(List<Animal> animals, int limit) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(limit)
            .toList();
    }

    public static Map<Animal.Type, Long> countAnimalByTypes(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(
                Animal::type,
                Collectors.counting()
            ));
    }

    public static Animal longestAnimalName(List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparingInt(animal -> animal.name().length()))
            .orElse(null);
    }

    public static Animal.Sex mostCommonSex(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()))
            .entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(null);
    }

    public static Map<Animal.Type, Animal> heaviestAnimalsByTypes(List<Animal> animals) {
        return animals.stream()
            .collect(
                Collectors.toMap(
                    Animal::type,
                    Function.identity(),
                    BinaryOperator.maxBy(Comparator.comparing(Animal::weight))
                ));
    }

    public static Animal oldestAnimal(List<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::age).reversed())
            .skip(k - 1)
            .findFirst()
            .orElse(null);
    }

    public static Optional<Animal> heaviestAnimalWithHeightBelowK(List<Animal> animals, int k) {
        return animals.stream()
            .filter(a -> a.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public static int countPawsAllAnimals(List<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public static List<Animal> animalsWithAgeNotEqualToPaws(List<Animal> animals) {
        return animals.stream()
            .filter(a -> a.paws() != a.age())
            .toList();
    }

    @SuppressWarnings("MagicNumber")
    public static List<Animal> animalsThatBitesAndHigherThanMeter(List<Animal> animals) {
        return animals.stream()
            .filter(a -> a.bites() && a.height() > 100)
            .toList();
    }

    public static Long countAnimalsWithWeightMoreThanHeight(List<Animal> animals) {
        return animals.stream()
            .filter(a -> a.weight() > a.height())
            .count();
    }

    public static List<Animal> animalsWithMoreThanTwoWordNames(List<Animal> animals) {
        return animals.stream()
            .filter(a -> a.name().split(" ").length > 2)
            .toList();
    }

    public static boolean hasDogHigherThanK(List<Animal> animals, int k) {
        return animals.stream()
            .anyMatch(a -> a.type() == Animal.Type.DOG && a.height() > k);
    }

    public static Map<Animal.Type, Long> sumWeightByTypeBetweenKL(List<Animal> animals, int k, int l) {
        return animals.stream()
            .filter(a -> a.age() >= k && a.age() <= l)
            .collect(Collectors.groupingBy(
                Animal::type,
                Collectors.counting()
            ));
    }

    public static List<Animal> sortByTypeThenSexThenName(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .toList();
    }

    public static Boolean isSpidersBitesOftenThanDogs(List<Animal> animals) {
        long bitingSpiders = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.SPIDER && animal.bites())
            .count();

        long bitingDogs = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG && animal.bites())
            .count();

        return bitingSpiders > bitingDogs;
    }

    public static Animal heaviestFishMoreThanInTwoLists(List<List<Animal>> animals) {
        return animals.stream()
            .flatMap(List::stream)
            .filter(a -> a.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(null);
    }

    public static Map<String, Set<ValidationError>> validateAnimals(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(
                Animal::name,
                ValidationError::validateAnimal
            ));
    }

    public static Map<String, String> validateAnimalsImproved(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(
                Animal::name,
                ValidationError::validateAnimalImproved
            ));
    }
}
