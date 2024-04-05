package exceptionsinstream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

class Either<L, R> {
  private L left;
  private R right;

  private Either(L l, R r) {
    this.left = l;
    this.right = r;
  }

  public static <L, R> Either<L,R> success(R r) {
    return new Either<>(null, r);
  }

  public static <L, R> Either<L,R> failure(L l) {
    return new Either<>(l, null);
  }

  public boolean isSuccess() {
    return left == null;
  }

  public boolean isFailure() {
    return left != null;
  }

  public L getLeft() {
    if (left == null) {
      throw new IllegalStateException("Bad");
    }
    return left;
  }

  public R getRight() {
    if (left != null) {
      throw new IllegalStateException("Bad");
    }
    return right;
  }

  public void handleError(Consumer<L> handler) {
    if (left != null) {
      handler.accept(left);
    }
  }

  // recover is a "map" that only happens if we are a failure representation
//  public Either<L, R> recover(UnaryOperator<Either<L, R>> op) {
  public Either<L, R> recover(UnaryOperator<Either<L, R>> op) {
    if (left != null) {
      return op.apply(this);
    } else {
      return this;
    }
  }
}

interface ExUnaryOperator<A> {
  A apply(A a) throws Throwable;

  public static <A> UnaryOperator<Either<Throwable, A>> wrap(ExUnaryOperator<A> op) {
    return e -> {
      try {
        return Either.success(op.apply(e.getRight()));
      } catch (Throwable t) {
        return Either.failure(t);
      }
    };
  }

}

interface ExFunction<A, R> {
  R apply(A a) throws Throwable;

  public static <A, R> Function<A, Either<Throwable, R>> wrap(ExFunction<A, R> op) {
    return a -> {
      try {
        return Either.success(op.apply(a));
      } catch (Throwable t) {
        return Either.failure(t);
      }
    };
  }
}

public class Example {
//  public static Stream<String> getLines(String fn) {
//    try {
//      return Files.lines(Path.of(fn));
//    } catch (Throwable t) {
//      throw new RuntimeException(t);
//    }
//  }

//  public static Optional<Stream<String>> getLines(String fn) {
//    try {
//      return Optional.of(Files.lines(Path.of(fn)));
//    } catch (Throwable t) {
//      return Optional.empty();
//    }
//  }

//  public static Either<Throwable, Stream<String>> getLines(String fn) {
//    try {
//      return Either.success(Files.lines(Path.of(fn)));
//    } catch (Throwable t) {
//      return Either.failure(t);
//    }
//  }

  public static void main(String[] args) {
    ExFunction<String, Stream<String>> getLines = fn -> Files.lines(Path.of(fn));

//    UnaryOperator<Either<Throwable, Stream<String>>> recovery = e -> {
    UnaryOperator<Either<Throwable, Stream<String>>> recovery = e -> {
      String fn = e.getLeft().getMessage();
      System.out.println("attempting to recover from missing file " + fn);
      return ExFunction.wrap(getLines).apply("d.txt");
    };

    Stream.of("a.txt", "b.txt", "c.txt")
//        .flatMap(Example::getLines)
        .map(ExFunction.wrap(getLines))
//        .peek(opt -> {
//          if (opt.isEmpty()) {
//            System.out.println("oops, that didn't work");
//          }
//        })
//        .filter(Optional::isPresent)
//        .flatMap(Optional::get)

        .peek(e -> e.handleError(t -> System.out.println("Problem with " + t.getMessage())))
        .map(e -> e.recover(recovery))
        .peek(e -> e.handleError(t -> System.out.println("Problem with " + t.getMessage())))
        // wrap call to recovery in the e.recover, so recovery is only executed in case of failure
        .map(e -> e.recover(recovery))
        .filter(Either::isSuccess)
        .flatMap(Either::getRight)
        .forEach(System.out::println);
  }
}
