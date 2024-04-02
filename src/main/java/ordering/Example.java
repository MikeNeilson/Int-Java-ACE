package ordering;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//interface Compartor<T> {
//  int compare(T a, T b);
//}

record Student(double gpa, int height) {}

class StudentHeightComparator implements Comparator<Student> {
  @Override
  public int compare(Student o1, Student o2) {
    return Integer.compare(o1.height(), o2.height());
  }
}

public class Example {
  public static void main(String[] args) {
    List<Student> students = new ArrayList<>(List.of(
        new Student(3.2, 70),
        new Student(3.1, 72),
        new Student(3.4, 68),
        new Student(2.2, 74)
    ));
    System.out.println(students);
    students.sort(new StudentHeightComparator());
    System.out.println(students);
  }
}
