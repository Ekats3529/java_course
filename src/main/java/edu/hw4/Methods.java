package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import static edu.hw4.Animal.Sex;
import static edu.hw4.Animal.Type;

@SuppressWarnings("MagicNumber")
public class Methods {

    //task 1 Отсортировать животных по росту от самого маленького
    // к самому большому -> List<Animal>
    public List<Animal> sortByHeight(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    //task 2 Отсортировать животных по весу от самого тяжелого к самому легкому,
    // выбрать k первых -> List<Animal>
    public List<Animal> sortByWeight(List<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k)
            .toList();
    }

    //task 3 Сколько животных каждого вида -> Map<Animal.Type, Integer>
    public Map<Type, Long> frequencyDict(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(
                Animal::type,
                Collectors.counting()
            ));
    }

    //task 4 У какого животного самое длинное имя -> Animal
    public Animal getLongestNameAnimal(List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparing(animal -> animal.name().length()))
            .orElseThrow();
    }

    //task 5 Каких животных больше: самцов или самок -> Sex
    public Sex mostCommonSex(List<Animal> animals) {
        int maleCount = (int) animals.stream()
            .filter(animal -> animal.sex() == Sex.M)
            .count();

        if (2 * maleCount > animals.size()) {
            return Sex.M;
        }
        return Sex.F;
    }

    //task 6 Самое тяжелое животное каждого вида -> Map<Animal.Type, Animal>
    public Map<Type, Animal> heaviestAnimals(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(
                Animal::type,
                Function.identity(),
                BinaryOperator.maxBy(Comparator.comparing(Animal::weight))
            ));
    }

    //task 7 K-е самое старое животное -> Animal
    public Animal kthOldest(List<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::age).reversed())
            .skip(k - 1)
            .findFirst()
            .orElse(null);
    }

    //task 8 Самое тяжелое животное среди животных ниже k см -> Optional<Animal>
    public Optional<Animal> heaviestBelowK(List<Animal> animals, int k) {
        return animals.stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparing(Animal::weight));
    }

    //task 9 Сколько в сумме лап у животных в списке -> Integer
    public Integer sumOfPaws(List<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    //task 10 Список животных, возраст у которых не совпадает с количеством лап -> List<Animal>
    public List<Animal> ageNotEqualPaws(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.paws() != animal.age())
            .toList();
    }

    //task 11 Список животных, которые могут укусить (bites == true)
    // и рост которых превышает 100 см -> List<Animal>
    public List<Animal> canBiteMoreThanHundred(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.bites() && animal.height() > 100)
            .toList();
    }

    //task 12 Сколько в списке животных, вес которых превышает рост -> Integer
    public long countWeightGreaterHeight(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count();
    }

    //task 13 Список животных, имена которых состоят из более чем двух слов -> List<Animal>
    public List<Animal> nameMoreTwoWords(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.name().split(" ").length > 1)
            .toList();
    }

    //task 14 Есть ли в списке собака ростом более k см -> Boolean
    public Boolean isInListDogMoreThanK(List<Animal> animals, int k) {
        return animals.stream()
            .anyMatch(animal -> animal.type() == Type.DOG && animal.height() > k);
    }

    //task 15 Найти суммарный вес животных каждого вида, которым от k до l лет
    // -> Map<Animal.Type, Integer>
    public Map<Type, Integer> sumWeightEachType(List<Animal> animals, int k, int l) {
        return animals.stream()
            .filter(animal -> animal.age() <= l && animal.age() >= k)
            .collect(Collectors.groupingBy(
                Animal::type,
                Collectors.summingInt(Animal::weight)
            ));
    }

    //task 16 Список животных, отсортированный по виду, затем по полу,
    // затем по имени -> List<Animal>
    public List<Animal> sortByTypeSexName(List<Animal> animals) {
        Comparator<Animal> animalComparator = Comparator
            .comparing(Animal::type)
            .thenComparing(Animal::sex)
            .thenComparing(Animal::name);

        return animals.stream()
            .sorted(animalComparator)
            .toList();
    }

    //task 17 Правда ли, что пауки кусаются чаще, чем собаки ->
    // Boolean (если данных для ответа недостаточно, вернуть false)
    public boolean isSpidersBitesMoreThanDogs(List<Animal> animals) {
        int cntDogs = (int) animals.stream().filter(animal -> animal.type() == Type.DOG).count();
        int cntSpiders = (int) animals.stream().filter(animal -> animal.type() == Type.SPIDER).count();

        int cntByteDogs = (int) animals.stream()
            .filter(animal -> animal.type() == Type.DOG && animal.bites())
            .count();

        int cntByteSpiders = (int) animals.stream()
            .filter(animal -> animal.type() == Type.SPIDER && animal.bites())
            .count();

        if (cntDogs * cntSpiders == 0) {
            return false;
        }
        return (double) cntByteSpiders / cntSpiders > (double) cntByteDogs / cntDogs;
    }

    //task 18 Найти самую тяжелую рыбку в 2-х или более списках -> Animal

    //task 19 Животные, в записях о которых есть ошибки:
    // вернуть имя и список ошибок -> Map<String, Set<ValidationError>>.

    //task 20 Сделать результат предыдущего задания более читабельным:
    // вернуть имя и названия полей с ошибками,
    // объединенные в строку -> Map<String, String>
}
