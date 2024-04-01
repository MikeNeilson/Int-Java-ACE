package equality;

public class Thing {
  public static void main(String[] args) {
    int x1 = 99;
    int x2 = 99;
    int x3 = 100;
    System.out.println(x1 == x2);
    System.out.println(x1 == x3);

    String s1 = new String("Hello");
    String s2 = new String("Hello");
    String s3 = new String("Goodbye");
    System.out.println(s1 == s2);
    System.out.println(s1 == s3);
  }
}
