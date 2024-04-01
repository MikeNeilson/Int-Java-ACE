package schoolstuff;


import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.time.DayOfWeek;
//import java.time.*;

@Target({ElementType.TYPE_USE})
@interface LookAtMe{}

public class Student {

  private String name;
//  private java.time.LocalDate enrollDate;
  private LocalDate enrollDate;

  public Student() {
//    name = "Unknown";
//    enrollDate = LocalDate.now();
    this("Unknown", LocalDate.now());
  }

  public /*no return type*/ Student(String name, LocalDate enrollDate) {
    System.out.println("running constructor");
    this.name = name;
    this.enrollDate = enrollDate;

  }

  public String getName(@LookAtMe Student this) {
//    int name = 99;
//    System.out.println("name is " + name);
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getEnrollDate() {
    return enrollDate;
  }

  public void setEnrollDate(LocalDate enrollDate) {
    this.enrollDate = enrollDate;
  }
}

class UseStudent {
  public static void main(String [] args) {
    System.out.println("Hello");
    Student s = new Student("Albert", LocalDate.now());
    Student s2 = new Student();
    System.out.println(s.getName());
//    System.out.println(getName());
  }
}