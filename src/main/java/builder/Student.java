package builder;

import java.util.ArrayList;
import java.util.List;

public class Student {
  private String name;
  private double gpa;
  private List<String> courses;

  private Student() { }

  public static class Builder {
    private String name;
    private double gpa;
    private List<String> courses = new ArrayList<>();

    private Builder() {}

    public Builder name(String s) {
      this.name = s;
      return this;
    }

    public Builder gpa(double gpa) {
      this.gpa = gpa;
      return this;
    }

    public Builder course(String c) {
      courses.add(c);
      return this;
    }

    public Student build() {
      // validate!!!!
      if (!isValid(name, gpa, courses)) {
        throw new IllegalStateException("Builder is not in a good place");
      }
      Student rv = new Student();
      rv.name = name;
      rv.gpa = gpa;
      rv.courses = List.copyOf(courses);
      return rv;
    }
  }

  public static boolean isValid(String name, double gpa, List<String> courses) {
    return name != null && name.length() > 0 && gpa >= 0 && gpa <= 5.0 && courses != null;
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", gpa=" + gpa +
        ", courses=" + courses +
        '}';
  }
}

class UseTheBuilder {
  public static void main(String[] args) {
    Student.Builder builder = Student.builder()
        .name("Fred")
        .gpa(3.2);
    Student s1 = builder.course("Math").course("Physics").build();
    System.out.println(s1);
    builder.name("Alan");
    Student s2 = builder.build();
    System.out.println(s2);
  }
}