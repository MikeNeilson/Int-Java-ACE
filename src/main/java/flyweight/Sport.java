package flyweight;

import java.util.HashMap;
import java.util.Map;

public class Sport {
  private String name;

  private Sport(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  private static Map<String, Sport> values = new HashMap<>();
  public static Sport of(String s) {
    // do we have a Sport for this String?
    // if so, return it to the caller
    // if not, make one, add it to the map, and then return that

  }

}

class UseSport {
  public static String expressOpionion(Sport s) {
    if (s.getName().equals("Cricket")) return "Kinda";
    if (s.getName().equals("Shinty")) return "You got to be kidding, that hurts!";
    throw new IllegalArgumentException("That can't happen");
  }

  public static void main(String[] args) {
    Sport s1 = new Sport("Cricket");
    Sport s2 = new Sport("Cricket");
    Sport s3 = new Sport("Shinty");

    System.out.println(s1 == s2);

    System.out.println(expressOpionion(s1));
    System.out.println(expressOpionion(s2));
//    System.out.println(expressOpionion("Rugby")); // typesafety
  }
}