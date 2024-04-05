package cocontravar;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TestingStuff {
//  public static <E extends F, F> List<E> filter(List<E> lt, Predicate<F> pred) {
  public static <E> List<E> filter(List<E> lt, Predicate<? super E> pred) {
    List<E> res = new ArrayList<>();
    for (E t : lt) {
      if (pred.test(t)) {
        res.add(t);
      }
    }
    return res;
  }

  public static void main(String[] args) {
    List<String> names = List.of("Fred", "Jim", "Sheila");

    Predicate<CharSequence> longish = s -> s.length() > 3;
    System.out.println(filter(names, longish));

    List<StringBuilder> names2 = List.of(new StringBuilder("Fred"),
        new StringBuilder("Jim"),
        new StringBuilder("Sheila"));
    System.out.println(filter(names2, longish));
  }
}
