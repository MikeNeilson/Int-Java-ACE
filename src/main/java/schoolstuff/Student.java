package schoolstuff;


import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//import java.time.*;

@Target({ElementType.TYPE_USE})
@interface LookAtMe{}

enum COURSE {
  MATH, PHYSICS, CHEMISTRY;
}

class VIPStudent extends Student {
  public VIPStudent(String name, LocalDate enrollDate, double gpa) {
    super(name, enrollDate, gpa);
  }

  @Override
  public void setGpa(double gpa) {
    super.setGpa(gpa + 0.5);
  }
}

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

//  public Student(String studenName, String majorName) {}
//  public Student(String studentName, String parentName) {}

  public static Student ofStudentNameMajorName(String studenName, String majorName) {return null;}
  public static Student ofStudentNameParentName(String studenName, String parentName) {return null;}

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

  public static Student of(String name, LocalDate enrollDate, double gpa, int contrib) {
    if (contrib > 10_000) {
      return new VIPStudent(name, enrollDate, gpa + 0.5);
    } else {
      return new Student(name, enrollDate, gpa);
    }
  }
}

class UseStudent {
//  public static void showAll(ArrayList<Student> als) {
  public static void showAll(List<Student> als) {
    for (Student s : als) {
      System.out.println(s);
    }
  }

//  public static void showAllSmart(ArrayList<Student> als, double smartThreshold) {
  public static void showAllSmart(List<Student> als, double smartThreshold) {
    for (Student s : als) {
      if (s.getGpa() > smartThreshold) {
        System.out.println(s);
      }
    }
  }

  public static List<Student> getAllSmart(List<Student> als, double smartThreshold) {
    ArrayList<Student> results = new ArrayList<>();
    for (Student s : als) {
      if (s.getGpa() > smartThreshold) {
        results.add(s);
      }
    }
    return results;
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

//    ArrayList<Student> als = new ArrayList<Student>();
//    List<Student> als = new ArrayList<Student>();
    List<Student> als = new LinkedList<Student>();
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
    showAllSmart(als, 3.0);
    System.out.println("--------------------");
    showAll(getAllSmart(als, 3.0));

    Student special = new VIPStudent("Algernon", LocalDate.now(), 2.2);
    System.out.println(special);
    special.setGpa(2.4);
    System.out.println(special);
  }
}