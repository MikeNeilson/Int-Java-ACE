package equality;

public class Boxing {
  public static void main(String[] args) {
    Integer i1 = 99; // autoboxing
    Integer i = Integer.valueOf(99); // explicit
    Integer i2 = new Integer(99); // DEPRECATED
    System.out.println(i == i1);
    System.out.println(i == i2);
  }
}
