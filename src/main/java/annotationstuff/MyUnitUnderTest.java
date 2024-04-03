package annotationstuff;

//@RunMe
public class MyUnitUnderTest {
//  @RunMe
  String value;

  @RunMe(value=10, name="Albert")
  public void doesThisWork() {
    System.out.println("Running test!!!");
  }

  @RunMe(10)
  private void tryThisToo() {
    System.out.println("running another test");
  }

  private void doNotRunThisOne() {
    System.out.println("oops, should not run this ...");
  }

  public String toString() {
    return "I'm a MyUnitUnderTest!!!";
  }
}
