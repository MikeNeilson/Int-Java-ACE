package mylist;

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

  public static class MyListIterator<E> implements Iterator<E> {
    private int progress = 0;
    private MyList<E> target;

    public MyListIterator(MyList<E> target) {
      this.target = target;
    }

    @Override
    public boolean hasNext() {
      return progress < target.count;
    }

    @Override
    public E next() {
      return target.data[progress++]; // failed to check for overflow
    }
  }
}

class UseMyList {
  public static void main(String[] args) {
    MyList<String> mls = new MyList<>();
    mls.add("Fred");
    mls.add("Jim");
    mls.add("Sheila");

    System.out.println(mls);

    MyList.MyListIterator<String> mli = new MyList.MyListIterator<>(mls);
    while (mli.hasNext()) {
      System.out.println(mli.next());
    }

    MyList.MyListIterator<String> mli2 = new MyList.MyListIterator<>(mls);
    MyList.MyListIterator<String> mli3 = new MyList.MyListIterator<>(mls);

    System.out.println("> " + mli2.next());
    System.out.println(">                                      " + mli3.next());
    System.out.println("> " + mli2.next());
    System.out.println(">                                      " + mli3.next());
    System.out.println(">                                      " + mli3.next());
    System.out.println("> " + mli2.next());
  }
}