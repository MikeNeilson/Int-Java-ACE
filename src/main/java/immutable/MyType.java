package immutable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class MyType {
  private final String name;
  private final List<String> words;

  MyType(String name, List<String> words) {
    this.name = name;
    this.words = words;
  }

//  public static MyType of(String name, List<String> words) {
//  public static MyType of(String name, String [] words) {
  public static MyType of(String name, String ... words) {
    System.out.println(words.getClass().getName());
//    return new MyType(name, new ArrayList<>(words));
//    return new MyType(name, List.copyOf(words));
//    return new MyType(name, Collections.unmodifiableList(words));
    return new MyType(name, List.of(words));
//    return new MyType(name, Arrays.asList(words)); // This is a VIEW!!! NOT "immutable" can still set items
  }

  public String getName() {
    return name;
  }

//  public void setName(String name) {
//    this.name = name;
//  }

  public List<String> getWords() {
//    return Collections.unmodifiableList(words);
    return words; // as a "List.of" this is safe
  }

//  public void setWords(List<String> words) {
//    this.words = words;
//  }

//  public void addSomething() {
//    words.add("Aha! I have the power");
//  }

  @Override
  public String toString() {
    return "MyType: name=" + name + ", words=" + words;
  }
}

//class MyBadType extends MyType {
//  private List<String> myWords = new ArrayList<>();
//  public MyBadType() {
//    super("Dodgy", null);
//  }
//  public void breakIt() {
//    myWords.add("Oh no!");
//  }
//
//  @Override
//  public List<String> getWords() {
//    return myWords;
//  }
//
//  @Override
//  public String toString() {
//    return super.toString() + " and " + myWords;
//  }
//}

class UseMyType {
  public static void main(String[] args) {
//    List<String> words = new ArrayList<>();
//    words.add("the");
//    words.add("rain");
//    words.add("in");
//    words.add("Spain");
//
//    MyType mt = MyType.of("Stuff", words);
//    MyType mt = MyType.of("Stuff", List.of("the", "rain", "in", "Spain"));
//    MyType mt = MyType.of("Stuff", new String[]{"the", "rain", "in", "Spain"});
//    MyType mt = MyType.of("Stuff", "the", "rain", "in", "Spain");
    String [] myWords = new String[]{"the", "rain", "in", "Spain"};
    MyType mt = MyType.of("Stuff", myWords);

    System.out.println(mt);
    myWords[0] = "Uh oh";
//    words.add("oops");
//    mt.getWords().add("Bang!!!");
//    System.out.println(mt);
//    mt.addSomething();
    System.out.println(mt);

//    mt = new MyBadType();
//    System.out.println(mt);
//    mt.getWords().add("Really!!!");
//    ((MyBadType)mt).breakIt();
//    System.out.println(mt);

  }
}
