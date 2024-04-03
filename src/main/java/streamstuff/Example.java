package streamstuff;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Example {
  public static void main(String[] args) {
    List<String> words = List.of("Fred", "Jim", "Shiela");
    Stream<String> ss = words.stream();
    ss
        .map(s -> s.toUpperCase())
        .forEach(s -> System.out.println(s));

//    ss // nope, stream has been exhausted
//        .forEach(s -> System.out.println(s));

    System.out.println("------------------");
    words.stream()
        .map(s -> {
          System.out.println("mapping " + s);
          return s.toUpperCase();
        })
        .filter(s -> s.length() > 3)
//        .forEach(s -> System.out.println(s))
    ;

    System.out.println("------------------");
    boolean allMatch = words.stream()
        .map(s -> {
          System.out.println("mapping2 " + s);
          return s.toUpperCase();
        })
        .allMatch(s -> s.length() > 3);
    ;
    System.out.println("all match? " + allMatch);

    System.out.println("------------------");
    long count = words.stream()
        .map(s -> {
          System.out.println("mapping2 " + s);
          return s.toUpperCase();
        })
        .count();
    ;
    System.out.println("count is " + count);

    System.out.println("------------------");
    int [] ia = {0};
    Stream.iterate(0, x -> x + 1)
        .parallel()
        .limit(1_000_000)
        .peek(x -> ia[0]++)
        .forEach(x -> System.out.println(x));
    System.out.println(ia[0]);

    System.out.println("------------------");
    AtomicInteger ai = new AtomicInteger();
    Stream.iterate(0, x -> x + 1)
        .parallel()
        .limit(1_000_000)
        .peek(x -> ai.incrementAndGet())
        .forEachOrdered(x -> System.out.println(x));
    System.out.println(ai);
  }
}
