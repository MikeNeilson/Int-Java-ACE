package schoolv2;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public record Student(String name, double gpa, List<String> courses) {
  public static Student of(String name, double gpa, String ... course) {
    return new Student(name, gpa, List.of(course));
  }
}

//interface CriterionOfStudent {
//  boolean test(Student s);
//}

interface Criterion<T> {
  boolean test(T t);
}

//class SmartCriterion implements CriterionOfStudent {
class SmartCriterion implements Criterion<Student> {
  private double threshold;

  public SmartCriterion(double threshold) {
    this.threshold = threshold;
  }

  @Override
  public boolean test(Student s) {
    return s.gpa() > threshold;
  }
}

//class EnthusiasticCriterion implements CriterionOfStudent {
class EnthusiasticCriterion implements Criterion<Student> {
  @Override
  public boolean test(Student s) {
    return s.courses().size() > 2;
  }
}

class School {
  public static <E> void showAll(List<E> ls) {
    for (E s : ls) {
      System.out.println("> " + s);
    }
  }

//  public static List<Student> getSmart(List<Student> ls, double threshold) {
//    List<Student> res = new ArrayList<>();
//    for (Student s : ls) {
//      if (s.gpa() > threshold) {
//        res.add(s);
//      }
//    }
//    return res;
//  }
//
//  public static List<Student> getEnthusiastic(List<Student> ls, int threshold) {
//    List<Student> res = new ArrayList<>();
//    for (Student s : ls) {
//      if (s.courses().size() > threshold) {
//        res.add(s);
//      }
//    }
//    return res;
//  }

//  public static List<Student> getByCriterion(List<Student> ls, CriterionOfStudent crit) {
//  public static List<Student> getByCriterion(List<Student> ls, Criterion<Student> crit) {
//    List<Student> res = new ArrayList<>();
//    for (Student s : ls) {
//      if (crit.test(s)) {
//        res.add(s);
//      }
//    }
//    return res;
//  }

//  public static <X397QT> List<X397QT> getByCriterion(List<X397QT> ls, Criterion<X397QT> crit) {
//    List<X397QT> res = new ArrayList<>();
//    for (X397QT s : ls) {
//      if (crit.test(s)) {
//        res.add(s);
//      }
//    }
//    return res;
//  }

  public static <E> List<E> filter(Iterable<E> ls, Criterion<E> crit) {
    List<E> res = new ArrayList<>();
    for (E s : ls) {
      if (crit.test(s)) {
        res.add(s);
      }
    }
    return res;
  }

  public static void main(String[] args) {
    List<Student> roster = List.of(
        Student.of("Fred", 3.2, "Math", "Physics"),
        Student.of("Jim", 2.2, "Journalism"),
        Student.of("Mary", 2.4, "Journalism", "English literature", "Pagan history", "Underwater basket-weaving"),
        Student.of("Sheila", 3.8, "Math", "Physics", "Astro-physics", "Quantum Mechanics")
    );

    System.out.println("all students:");
    showAll(roster);
    System.out.println("very smart students:");
//    showAll(getSmart(roster, 3.5));
//    showAll(getByCriterion(roster, new SmartCriterion(3.5)));
    showAll(filter(roster, s -> s.gpa() > 3.5));
    System.out.println("somewhat smart students:");
//    showAll(getSmart(roster, 2.5));
//    showAll(getByCriterion(roster, new SmartCriterion(2.5)));
    showAll(filter(roster, s -> s.gpa() > 2.5));
    System.out.println("enthusiastic students:");
//    showAll(getByCriterion(roster, new EnthusiasticCriterion()));
    showAll(filter(roster, s -> s.courses().size() > 2));

    List<String> words = List.of("the", "rain", "in", "Spain");
    showAll(filter(words, w -> w.length() > 3));

    Criterion<String> test = (String w) -> w.length() > 3;
    Class cl = test.getClass();
    Method [] methods = cl.getMethods();
    for (Method m : methods) {
      System.out.println(m);
    }

  }
}
