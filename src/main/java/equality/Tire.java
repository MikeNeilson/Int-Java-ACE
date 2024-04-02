package equality;

public class Tire {
  private int width;
  private int diameter;

  public Tire(int width, int diameter) {
    this.width = width;
    this.diameter = diameter;
  }

  public final boolean equals(Object other) {
//    if (other.getClass() != this.getClass()) return false;
    return other instanceof Tire t
        && t.width == this.width
        && t.diameter == this.diameter;
  }
}

class TruckTire extends Tire {
  private int load;

  public TruckTire(int width, int diameter, int load) {
    super(width, diameter);
    this.load = load;
  }

//  public boolean equals(Object other) {
//    if (other.getClass() != this.getClass()) return false;
//    return other instanceof TruckTire tt
//      && tt.load == this.load
//      && super.equals(tt);
//  }
}
class UseTire {
  public static void main(String[] args) {
    Tire t1 = new Tire(155, 14);
    Tire t2 = new Tire(155, 14);
    Tire t3 = new Tire(145, 14);
    System.out.println("t1 equals t2 ? " + t1.equals(t2));
    System.out.println("t1 equals t3 ? " + t1.equals(t3));
    TruckTire tt1 = new TruckTire(155, 14, 10);
    TruckTire tt2 = new TruckTire(155, 14, 10);
    System.out.println("tt1 equals tt2 ? " + tt1.equals(tt2));
    System.out.println("tt1 equals t1 ? " + tt1.equals(t1));
    System.out.println("t1 equals tt1 ? " + t1.equals(tt1));
  }
}