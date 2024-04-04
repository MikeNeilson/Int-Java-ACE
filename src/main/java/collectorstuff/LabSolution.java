package collectorstuff;

import superiterable.StudentNonRecord;
import superiterable.SuperIterable;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class LabSolution {

  public static String getGpaAsLetter(double gpa) {
    if (gpa > 3.5) return "A";
    if (gpa > 3)  return "B";
    if (gpa > 2.5) return "C";
    if (gpa > 2) return "D";
    return "F";
  }

  public static void main(String[] args) {
    List<StudentNonRecord> roster = List.of(
        StudentNonRecord.of("Fred", 3.2, "Math", "Physics"),
        StudentNonRecord.of("Jim", 2.2, "Journalism"),
        StudentNonRecord.of("Jimmy", 3.1, "Journalism"),
        StudentNonRecord.of("Jim1", 3.3, "Journalism"),
        StudentNonRecord.of("Jim2", 2.7, "Journalism"),
        StudentNonRecord.of("Sheila", 3.8, "Math", "Physics", "Astrophysics", "Quantum Mechanics"),
        StudentNonRecord.of("Mary", 2.8, "Math", "Art", "Journalism", "English Literature")
    );

    roster.stream()
        .collect(Collectors.groupingBy(s -> getGpaAsLetter(s.getGpa())))
        .entrySet().stream()
        .map(e -> "Students with letter grade " + e.getKey() + " are " + e.getValue())
        .forEach(System.out::println);

    System.out.println("--------------------------------");

    roster.stream()
        .collect(Collectors.groupingBy(
            s -> getGpaAsLetter(s.getGpa()),
            Collectors.counting()))
        .entrySet().stream()
        .map(e -> "There are " + e.getValue() + " students with grade " + e.getKey())
        .forEach(System.out::println);

    System.out.println("--------------------------------");
    roster.stream()
        .collect(Collectors.groupingBy(
            s -> getGpaAsLetter(s.getGpa()),
            Collectors.mapping(s -> s.getName(),
                Collectors.toList())))
        .entrySet().stream()
        .map(e -> "Students " + e.getValue() + " have grade " + e.getKey())
        .forEach(System.out::println);

    System.out.println("--------------------------------");
    Collector<StudentNonRecord, ?, String> collectStudentToNamesInAMessage = Collectors.mapping(s -> s.getName(),
        Collectors.joining(", ", "Students ", " have grade "));
    roster.stream()
        .collect(Collectors.groupingBy(
            s -> getGpaAsLetter(s.getGpa()),
            collectStudentToNamesInAMessage))
        .entrySet().stream()
        .map(e -> e.getValue() + e.getKey())
        .forEach(System.out::println);

  }
}
