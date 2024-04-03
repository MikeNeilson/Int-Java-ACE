package streamstuff;

import java.util.stream.IntStream;

public class SumExample {
  public static void main(String[] args) {
//    IntStream.iterate(110, x -> x + 1)
//        .limit(10)
    System.out.println(IntStream.range(1, 11)
        .reduce(0, (x,y) -> x + y));

//        .forEach(x -> System.out.println(x));
  }
}
