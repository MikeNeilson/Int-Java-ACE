package mylistinner;

import java.util.Iterator;

public class MyList<E> {
  private E[] data = (E[])new Object[10];
  private int count = 0;

  public void add(E e) {
    data[count++] = e;  // no overflow check!!
  }

  public String toString() {
    StringBuilder sb = new StringBuilder("MyList[");
    for (int i = 0; i < count; i++) {
      sb.append(data[i]).append(", ");
    }
    sb.setLength(sb.length() - 2);
    sb.append("]");
    return sb.toString();
  }

  private class MyListIterator implements Iterator<E> {
    private int progress = 0;
//    private mylist.MyList<E> target; // MyList.this

//    public MyListIterator(/*mylist.MyList<E> target*/) { becomes:
//    public MyListIterator(MyList<E> MyList.this) {
    public MyListIterator() { // this has the above argument implicitly
//      this.target = target;
    }

    @Override
    public boolean hasNext() {
//      return progress < /*target.*/count;
      return progress < /*target.*/MyList.this.count;
    }

    @Override
    public E next() {
      return /*target.*/data[progress++]; // failed to check for overflow
    }
  }

  public Iterator<E> iterator() {
//    return this.new MyListIterator();
    return new MyListIterator();
  }
}

class UseMyList {
  public static void main(String[] args) {
    MyList<String> mls = new MyList<>();
    mls.add("Fred");
    mls.add("Jim");
    mls.add("Sheila");

    System.out.println(mls);

    Iterator<String> mli = mls.iterator();
    while (mli.hasNext()) {
      System.out.println(mli.next());
    }

    Iterator<String> mli2 = mls.iterator();
    Iterator<String> mli3 = mls.iterator();

    System.out.println("> " + mli2.next());
    System.out.println(">                                      " + mli3.next());
    System.out.println("> " + mli2.next());
    System.out.println(">                                      " + mli3.next());
    System.out.println(">                                      " + mli3.next());
    System.out.println("> " + mli2.next());
  }
}