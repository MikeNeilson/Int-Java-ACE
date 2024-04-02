package recordstuff;

public record Airplane(int wingspan, String name) {

  public Airplane {
    if (wingspan < 0) throw new IllegalArgumentException("Negative wingspan");
    if (name.startsWith("C-")) {
      name = "Cessna " + name.substring(2);
    }
  }
//  public Airplane(int wingspan, String name) {
//    if (wingspan < 0) throw new IllegalArgumentException("Negative wingspan");
//    if (name.startsWith("C-")) {
//      name = "Cessna " + name.substring(2);
//    }
//    this.wingspan = wingspan;
//    this.name = name;
//  }

  public Airplane(String name) {
    this(25, name);
  }

  public String getFancyName() {
    return "I'm a cool " + name + " with a wingspan of " + wingspan;
  }

  @Override
  public String name() {
    return "I'm an airplane called " + name;//+ super.name();
  }

  public static void sayHello() {
    System.out.println("Hello Pilot person!");
  }
}

class UseAirplane {
  public static void main(String[] args) {
    Airplane a1 = new Airplane(30, "C-172");
    Airplane a2 = new Airplane(30, "Cessna 172");
    Airplane a3 = new Airplane(28, "Cessna 152");
    System.out.println(a1);
    System.out.println(a1.equals(a2));
    System.out.println(a1.equals(a3));
    System.out.println("name is " + a1.name());
    System.out.println(a1.getFancyName());
    Airplane.sayHello();
    System.out.println(a1.name());
  }
}