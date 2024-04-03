package superiterable;

import java.util.List;
import java.util.Objects;

// This one is suitable for Java 11... records don't work until Java 16
public class StudentNonRecord {
  private String name;
  private double gpa;
  private List<String> courses;

  private StudentNonRecord(String name, double gpa, List<String> courses) {
    this.name = name;
    this.gpa = gpa;
    this.courses = courses;
  }

  public static StudentNonRecord of(String name, double gpa, String ... course) {
    return new StudentNonRecord(name, gpa, List.of(course));
  }

  public String getName() {
    return name;
  }

  public double getGpa() {
    return gpa;
  }

  public List<String> getCourses() {
    return courses;
  }

  @Override
  public String toString() {
    return "StudentNonRecord{" +
        "name='" + name + '\'' +
        ", gpa=" + gpa +
        ", courses=" + courses +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    StudentNonRecord that = (StudentNonRecord) o;
    return Double.compare(gpa, that.gpa) == 0 && Objects.equals(name, that.name) && Objects.equals(courses, that.courses);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, gpa, courses);
  }
}
