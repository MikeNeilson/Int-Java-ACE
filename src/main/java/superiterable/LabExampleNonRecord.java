package superiterable;

import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class LabExampleNonRecord {
  public static void main(String[] args) {
    SuperIterable<StudentNonRecord> sis = new SuperIterable<>(List.of(
        StudentNonRecord.of("Fred", 3.2, "Math", "Physics"),
        StudentNonRecord.of("Jim", 2.2, "Journalism"),
        StudentNonRecord.of("Sheila", 3.8, "Math", "Physics", "Astrophysics", "Quantum Mechanics"),
        StudentNonRecord.of("Mary", 2.8, "Math", "Art", "Journalism", "English Literature")
    ));

    sis
        .forEach(s -> System.out.println(s));
    System.out.println("-----------------------");

    sis
        .filter(s -> s.getGpa() > 3.0)
        .map(s -> "Student " + s.getName() + " has grade " + s.getGpa())
        .forEach(s -> System.out.println(s));
    System.out.println("-----------------------");

    Predicate<StudentNonRecord> smartStudent = s -> s.getGpa() > 3.0;
    Predicate<StudentNonRecord> nonEnthusiatic = s -> s.getCourses().size() < 3;
    sis
        .filter(smartStudent)
        .filter(nonEnthusiatic)
        .map(s -> "Student " + s.getName() + " has grade " + s.getGpa())
        .forEach(s -> System.out.println(s));
    System.out.println("-----------------------");

    sis
        .map(s -> s.getCourses())
        .forEach(s -> System.out.println(s));
    System.out.println("-----------------------");

    sis
        .flatMap(s -> new SuperIterable<>(s.getCourses()))
        .forEach(s -> System.out.println(s));
    System.out.println("-----------------------");

    sis
        .filter(s -> s.getGpa() > 3.0)
        .flatMap(s -> new SuperIterable<>(s.getCourses()))
        .map(s -> "a smart student takes " + s)
        .forEach(s -> System.out.println(s));
    System.out.println("-----------------------");

    sis
        .filter(s -> s.getGpa() > 3.0)
//        .flatMap(
//            (StudentNonRecord s) -> {
//              return new SuperIterable<>(s.getCourses()).map(c -> "Student " + s.getName() + " takes " + c);
//            }
//        )
        .flatMap(s -> new SuperIterable<>(s.getCourses()).map(c -> "Student " + s.getName() + " takes " + c))
        .forEach(s -> System.out.println(s));
    System.out.println("-----------------------");


/*
    Function<StudentNonRecord, List<String>> fsi = s -> s.getCourses();
//    Object fsi = s -> s.getCourses();
    Class cl = fsi.getClass();
    System.out.println(cl.getName());
    Method [] methods = cl.getMethods();
    for (Method m : methods) {
      System.out.println(m);
    }
*/
  }
}
