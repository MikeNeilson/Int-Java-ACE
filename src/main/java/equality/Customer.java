package equality;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Customer {
  private String name;
  private String address;
  private long creditLimit;

  public Customer(String name, String address, long creditLimit) {
    this.name = name;
    this.address = address;
    this.creditLimit = creditLimit;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Customer) {
      Customer otherC = (Customer)other;
      return otherC.name.equals(this.name) && otherC.address.equals(this.address);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return name.length(); // functionally correct, but very inefficient!!
  }
}

class UseCustomer {
  public static void main(String[] args) {
    Customer c1 = new Customer("Fred", "Over there", 1_000);
    Customer c2 = new Customer("Jim", "Over here", 2_000);
    Customer c3 = new Customer("Fred", "Over there", 4_000);

    System.out.println("c1.equals(c2) ? " + c1.equals(c2));
    System.out.println("c1.equals(c3) ? " + c1.equals(c3));

    List<Customer> lc = List.of(c1, c2);
    System.out.println(lc.contains(c3));

    Set<Customer> sc = new HashSet<>();
    sc.add(c1);
    sc.add(c2);
    System.out.println(sc.contains(c3));
  }

}
