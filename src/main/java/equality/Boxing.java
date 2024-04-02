package equality;

import java.util.ArrayList;
import java.util.List;

public class Boxing {
  public static void main(String[] args) {
    Integer i1 = 99; // autoboxing
    Integer i = Integer.valueOf(99); // explicit
    Integer i2 = new Integer(99); // DEPRECATED
    System.out.println(i == i1);
    System.out.println(i == i2);

    String s1 = "Hello";
    s1.toUpperCase();
    System.out.println(s1);
    s1 = s1.toUpperCase();
    System.out.println(s1);

    List<Object> stuff = new ArrayList<>();
    stuff.add(99);
    Object obj = stuff.get(0);
    System.out.println(obj);
    System.out.println(obj.getClass().getName());

    Integer x = 99;
//    x = x + 10;
    x = Integer.valueOf(x.intValue() + 10);
    System.out.println(x);
  }
}
