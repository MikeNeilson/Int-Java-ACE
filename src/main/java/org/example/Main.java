package org.example;

public class Main {
  public static void main(String[] args) {
    Object obj = "Hello world!";

    System.out.println(switch(obj) {
      case String s -> "Message is " + s;
      default -> "Uh oh!";
    });
  }
}