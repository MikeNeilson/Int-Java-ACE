package schoolstuff;


import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.ArrayList;
//import java.time.*;

@Target({ElementType.TYPE_USE})
@interface LookAtMe{}

public class Student {

  private String name;
//  private java.time.LocalDate enrollDate;
  private LocalDate enrollDate;
  private double gpa;

/*  public Student() {
//    name = "Unknown";
//    enrollDate = LocalDate.now();
    this("Unknown", LocalDate.now());
  }*/

  public /*no return type*/ Student(String name, LocalDate enrollDate, double gpa) {
    if (! isValid(name, enrollDate, gpa)) {
      throw new IllegalArgumentException("Bad Student Data");
    }
    System.out.println("running constructor");
    this.name = name;
    this.enrollDate = enrollDate;
    this.gpa = gpa;
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

  public double getGpa() {
    return gpa;
  }

  public void setGpa(double gpa) {
    this.gpa = gpa;
  }

  @Override
  public String toString() {
    return "Student: name=" + this.name + ", enrolled=" + this.enrollDate
        + ", gpa=" + this.gpa;
  }

  public String asTextNameGpa() {
    return "Student " + name + " has gpa " + gpa;
  }

  public static boolean isValid(String name, LocalDate enrollDate, double gpa) {
    return (name != null && name.length() > 0 && gpa >= 0 && gpa <= 5.0 && enrollDate != null);
  }

}

class UseStudent {
  public static void showAll(ArrayList<Student> als) {
    for (Student s : als) {
      System.out.println(s);
    }
  }

  public static void showAllSmart(ArrayList<Student> als, double smartThreshold) {
    for (Student s : als) {
      if (s.getGpa() > smartThreshold) {
        System.out.println(s);
      }
    }
  }

//  private static double smartThreshold = 3.0;
//
//  public static void showAllSmart(ArrayList<Student> als) {
//    for (Student s : als) {
//      if (s.getGpa() > smartThreshold) {
//        System.out.println(s);
//      }
//    }
//  }

  public static void main(String [] args) {
    System.out.println("Hello");
    Student s = new Student("Albert", LocalDate.now(), 3.5);
    Student s2 = new Student("Freddie", LocalDate.now().minusDays(10), 2.8);
    Student s3 = new Student("Jim", LocalDate.now().minusDays(300), 2.2);
    Student s4 = new Student("Sheila", LocalDate.now().minusDays(75), 4.5);
//    System.out.println(s.getName());
//    System.out.println("Student s is: " + s);
//    System.out.println(getName());

    ArrayList<Student> als = new ArrayList<Student>();
    als.add(s);
    als.add(s2);
    als.add(s3);
    als.add(s4);

    System.out.println(als);
    System.out.println("student count is " + als.size());
    System.out.println("student at position 2 is " + als.get(2));
    System.out.println("--------------------");
    showAll(als);
    System.out.println("--------------------");
    showAllSmart(als);
  }
}