package mutaverage;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;

class Average {
  private double sum;
  private long count;

  // default constructor!

  public void include(double d) {
    sum += d;
    count++;
  }

  public void merge(Average other) {
    this.sum += other.sum;
    this.count += other.count;
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

  final long COUNT = 3_000_000_000L;
  long start = System.nanoTime();
  Average res = DoubleStream.generate(() -> ThreadLocalRandom.current().nextDouble(-1, +1))
      .parallel()
      .limit(COUNT)
      .collect(
          () -> new Average(),
          (a, d) -> a.include(d),
          (aFinal, another) -> aFinal.merge(another)
      );
  long time = System.nanoTime() - start;
    res.get()
        .map(v -> "The average is " + v)
        .ifPresentOrElse(
            s -> System.out.println(s),
            () -> System.out.println("oops, there was no data")
        );
    System.out.printf("Time taken was %7.3f, rate is %7.3f per millisecond\n",
        (time / 1_000_000_000.0),
        (COUNT / (time / 1_000_000.0)));
  }
}

