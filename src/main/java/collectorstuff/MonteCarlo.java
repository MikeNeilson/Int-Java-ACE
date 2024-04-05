package collectorstuff;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MonteCarlo {
  public static void main(String[] args) {
    Map<Integer, Long> map = Stream.generate(
        () -> IntStream.generate(() -> ThreadLocalRandom.current().nextInt(1, 7))
            .limit(20)
            .sum()
    )
        .limit(1_000_000_000)
        .collect(Collectors.groupingBy(x -> x,
            Collectors.counting()));

    // find the largest count and work out the scaling to fit to screen width
    long maxCount = map.entrySet().stream()
        .mapToLong(e -> e.getValue())
        .max()
        .getAsLong();
    long width = 110;
    long divisor = maxCount / width;

    Comparator<Map.Entry<Integer, Long>> intComp = Comparator.comparingInt(Map.Entry::getKey);
    Comparator<Map.Entry<Integer, Long>> revIntComp =
        Comparator.<Map.Entry<Integer, Long>>comparingInt(Map.Entry::getKey).reversed();
//    Comparator<Map.Entry<Integer, Long>> revIntComp = intComp.reversed();
    map.entrySet().stream()
//        .sorted((e1, e2) -> Integer.compare(e1.getKey(), e2.getKey()))
//        .sorted(Comparator.comparingInt(Map.Entry::getKey))
        .sorted()
        .map(e -> String.format("%3d: %s",
            e.getKey(),
            Stream.generate(() -> "*")
                .limit(e.getValue() / divisor)
                .collect(Collectors.joining())))
        .forEachOrdered(System.out::println);
  }
}
