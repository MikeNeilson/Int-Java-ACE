package closurestuff;

import java.io.Serializable;
import java.util.function.Predicate;
import java.util.stream.Stream;

@FunctionalInterface
interface X {
  void doStuff();
//  void doOtherMethod();
}

interface Y {
  void doOtherMethod();
}

interface Z extends X, Y {

}


public class Example {
  // Create this:
  getInversePredicate...
  // return a predicate that tests the exact opposite of the argument predicate

  public static <T> Predicate<T> getBothPredicate(Predicate<T> ps1, Predicate<T> ps2) {
    return s -> ps1.test(s) && ps2.test(s);
  }

  public static Predicate<String> getShortStringPredicate(final int threshold) {
    return s -> s.length() < threshold;
  }
  public static Predicate<String> getLongStringPredicate(final int threshold) {
//    threshold++;
    return s -> s.length() > threshold;
  }
  public static void main(String[] args) {
//    Comparable & Constable & Serializable o = false ? "Hello" : 99;
    var o = false ? "Hello" : 99;
//    "".cons
//    "".com
//    Integer.valueOf(99).com..

//    X x = () -> {};

    Predicate<String> lsp = getLongStringPredicate(4);
    Stream.of("Fred", "Jim", "Shiela", "Alice", "Bob", "Xi", "I")
        .filter(lsp)
        .forEach(System.out::println);
    System.out.println("-------------------");
    lsp = getLongStringPredicate(3);
    Stream.of("Fred", "Jim", "Shiela", "Alice", "Bob")
        .filter(lsp)
        .forEach(System.out::println);

    System.out.println("-------------------");
    Predicate<String> longer = getLongStringPredicate(3);
    Predicate<String> shorter = getShortStringPredicate(6);
    Predicate<String> both = getBothPredicate(longer, shorter);
    Stream.of("Fred", "Jim", "Shiela", "Alice", "Bob")
        .filter(both)
        .forEach(System.out::println);

    System.out.println("-------------------");
    lsp = getLongStringPredicate(5);
    Predicate<String> notLongString = getInversePredicate(lsp);
    Stream.of("Fred", "Jim", "Shiela", "Alice", "Bob")
        .filter(notLongString)
        .forEach(System.out::println);


  }
}
