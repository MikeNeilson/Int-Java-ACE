package collectorstuff;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Examples {
  public static void main(String[] args) {
    Map<Integer, List<String>> map = Stream.of("Fred", "Jim", "Sheila", "Alice", "Bob", "Trent", "Tom", "Dick", "Harry")
        .collect(Collectors.groupingBy(s -> s.length()));

    map.forEach((k, v) -> System.out.println("Names of length " + k + " are " + v));
    System.out.println("-------------------------");
    Stream.of("Fred", "Jim", "Sheila", "Alice", "Bob", "Trent", "Tom", "Dick", "Harry")
        .collect(Collectors.groupingBy(s -> s.length(),
            Collectors.counting()))
        .entrySet().stream() // stream<Map.Entry<Integer, ??? >
        .map(e -> "Key is " + e.getKey() + " value is " + e.getValue())
        .forEach(System.out::println);
    System.out.println("-------------------------");

    Stream.of("Fred", "Jim", "Sheila", "Alice", "Bob", "Trent", "Tom", "Dick", "Harry")
        .collect(Collectors.groupingBy(
            s -> s.length(),
            Collectors.mapping(s -> s.toUpperCase(),
                Collectors.joining(", ", "Strings of this length are: ", " END"))))
        .entrySet().stream() // stream<Map.Entry<Integer, ??? >
        .map(e -> "Key is " + e.getKey() + " value is " + e.getValue())
        .forEach(System.out::println);
  }
}
