package annotationstuff;

public class MyInjectionTarget {
  @InjectHere("key1")
  private String myValue;

  private String dontInjectMe;

  @InjectHere("key2")
  private String anotherValue;

  @Override
  public String toString() {
    return "I'm a MyInjectionTarget with myValue=" + myValue
        + " anothervalue=" + anotherValue
        + " dontInjectMe=" + dontInjectMe;
  }
}
