package superiterable;

import java.util.List;

public class UseSuperIterable {
  public static void main(String[] args) {
    SuperIterable<String> sis = new SuperIterable<>(List.of("Fred", "Jim", "Sheila"));
    sis
        .forEvery(s -> System.out.println(s));
//    for (String s : sis) {
//      System.out.println("> " + s);
//    }
//
    System.out.println("---------------------");
//    for (String s : sis.filter(s -> s.length() > 3)) {
//      System.out.println(">> " + s);
//    }
    sis
        .filter(s -> s.length() > 3)
        .map(s -> s.length())
        .forEvery(s -> System.out.println(s));
    System.out.println("-----------------------");
    sis
        .filter(s -> s.length() > 3)
        .map(s -> "The name " + s + " has " + s.length() + " characters")
        .forEach(s -> System.out.println(s));
  }
}
