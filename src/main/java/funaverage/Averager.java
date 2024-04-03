package funaverage;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;

class Average {
  private final double sum;
  private final long count;

  public Average(double sum, long count) {
    this.sum = sum;
    this.count = count;
  }

  public Average merge(Average this, Average other) {
    return new Average(this.sum + other.sum, this.count + other.count);
  }

  public Optional<Double> get() {
    if (count > 0) {
      return Optional.of(sum / count);
    } else {
      return Optional.empty();
    }
  }
}

public class Averager {
  public static void main(String[] args) {
    DoubleStream.generate(() -> ThreadLocalRandom.current().nextDouble(-1, +1))
        .limit(10_000_000)
        .mapToObj(x ->  new Average(x, 1))
        .reduce(
            new Average(0,0),
//            (a1, a2) -> a1.merge(a2))
            Average::merge)
        .get()
        .map(v -> "The average is " + v)
        .ifPresentOrElse(
//            s -> System.out.println(s),
            System.out::println,
            () -> System.out.println("oops, there was no data")
        );

  }
}
