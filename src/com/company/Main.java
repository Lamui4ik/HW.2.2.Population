package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long streamUnderAge = persons.stream()
                .filter((p) -> p.getAge() < 18)
                .count();
        System.out.println("Number of minors: " + streamUnderAge);

        List<String> streamMilitaryService = persons.stream()
                .filter((p) -> p.getAge() >= 18 && p.getAge() < 27 && p.getSex() == Sex.MAN)
                .map(p -> String.valueOf(p.getFamily()))
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Names of conscripts: " + streamMilitaryService);

        List<String> streamPeopleWhoCanWork = persons.stream()
                .filter((p) -> p.getEducation() == Education.HIGHER)
                .filter((p) -> p.getAge() >= 18)
                .filter((p) -> (p.getSex() == Sex.WOMEN && p.getAge() < 60) || (p.getSex() == Sex.MAN && p.getAge() < 65))
                .sorted((o1, o2) -> -o1.getFamily().compareTo(o2.getFamily()))
                .map(p -> String.valueOf(p.getFamily()))
                .distinct()
                .collect(Collectors.toList());
        System.out.println("People who can work: " + streamPeopleWhoCanWork);
    }
}
