package ec.generic;


import lombok.Data;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonMain {
    public static void main(String[] args) {
        List<Person> personList = List.of(
                new Person("Selam", 45, Person.Gender.FEMALE),
                new Person("Beza", 21, Person.Gender.FEMALE),
                new Person("Jeff", 30, Person.Gender.MALE),
                new Person("Mesi", 19, Person.Gender.FEMALE),
                new Person("Abel", 29, Person.Gender.MALE),
                new Person("Tebebe", 39, Person.Gender.MALE)
        );

        System.out.println(Person.printAge(personList, p1->p1.getAge() > 20));
        System.out.println("Average age = " + Person.printAverageAge(personList, p1->p1.getAge() > 20));
        System.out.println("Comparing by Age");
        Person.sortBy(personList, Comparator.comparing(Person::getAge)).forEach(System.out::println);
        System.out.println("Comparing by name");
        Person.sortBy(personList, Comparator.comparing(Person::getName)).forEach(System.out::println);
        System.out.println("Group By by name");
        Person.groupByName(personList, Person::getName).keySet().stream().forEach(System.out::print);
        System.out.println("Group By by Age");
        Person.groupByAge(personList).keySet().stream().forEach(System.out::print);
    }

}

@Data
final class Person {
    private final String name;
    private final int age;
    private final Gender gender;

    public Person(String name, int age, Person.Gender g) {
        this.name = name;
        this.age = age;
        this.gender = g;
    }

    enum Gender { MALE, FEMALE };

    @Override
    public String toString() {
        return "Person{ " +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }

    /** Generic method to print age by  some selector */
    static int printAge(List<Person> personList, Predicate<Person> selector) {
        return personList.stream()
                .filter(selector)
                .mapToInt(Person::getAge)
                .reduce(0, Integer::sum);
    }
    /** Generic method to print average age by  some selector */
    static double printAverageAge(List<Person> personList, Predicate<Person> selector) {
        return personList.stream()
                .filter(selector)
                .collect(Collectors.averagingDouble(Person::getAge));
    }

    //ToDo -sort persons compare by name, age, gender -> using a single generic method
    /** Sort List of persons by some condition */
    static Stream<Person> sortBy(List<Person> personList, Comparator<Person> comparator) {
        return personList.stream().sorted(comparator);
    }

    //ToDo- create a single generic method to groupBy-> Age, name, gender-  using generic Type T we can do that by passing functional Interface
    static Map<Integer, List<Person>> groupByAge(List<Person> personList) {
        return personList.stream().collect(Collectors.groupingBy(Person::getAge));
    }
    static Map<String, List<Person>> groupByName(List<Person> personList, Function<Person, String> selector) {
        return personList.stream().collect(Collectors.groupingBy(selector));
    }
}
