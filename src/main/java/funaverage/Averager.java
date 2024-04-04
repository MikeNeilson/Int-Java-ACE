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
    final long COUNT = 1_000_000_000;
    long start = System.nanoTime();
    Average res = DoubleStream.generate(() -> ThreadLocalRandom.current().nextDouble(-1, +1))
        .parallel()
        .limit(COUNT)
        .mapToObj(x -> new Average(x, 1))
        .reduce(
            new Average(0, 0),
//            (a1, a2) -> a1.merge(a2))
            Average::merge);
    long time = System.nanoTime() - start;
    res.get()
        .map(v -> "The average is " + v)
        .ifPresentOrElse(
//            s -> System.out.println(s),
            System.out::println,
            () -> System.out.println("oops, there was no data")
        );
    System.out.printf("Time taken was %7.3f, rate is %7.3f per millisecond\n",
        (time / 1_000_000_000.0),
        (COUNT / (time / 1_000_000.0)));
  }
}
