package org.example;

import myservice.DataProvider;
import myservice.MyServiceIf;
//import myservice.HackIt;

import java.lang.reflect.Field;
import java.util.ServiceLoader;

public class Main {
  public static void main(String[] args) throws Throwable {
    System.out.println(DataProvider.dataProviderMessage);
    Class cl = DataProvider.class;
    Field secret = cl.getDeclaredField("secretMessage");
    System.out.println("field is " + secret);
    secret.setAccessible(true);
    String msg = (String)secret.get(null);
    System.out.println("secret message is " + msg);
//    System.out.println(DataProvider.morePersonalMessage);
//    System.out.println(HackIt.getTheMessage());

    ServiceLoader<MyServiceIf> loc = ServiceLoader.load(MyServiceIf.class);
    for (MyServiceIf srv : loc) {
      System.out.println("Found a service: " + srv.getClass().getName());
      System.out.println("message is " + srv.getMessage());
    }
  }
}


/*
  205  tree  my*
  206  javac -d serv-classes my.service.module/src/main/java/myservice/DataProvider.java
  207  tree serv-classes/
  208  javac -d cli-classes my.client.module/src/main/java/org/example/Main.java
  209  javac -d cli-classes -cp serv-classes/ my.client.module/src/main/java/org/example/Main.java
  210  java org.example.Main
  211  java -cp cli-classes/ org.example.Main
  212  java -cp cli-classes/:serv-classes/ org.example.Main

    */
//  Modular forms:
//    218  javac -d serv-mods --module-source-path "./*/src/main/java" --module my.service.module
//    219  ls
//  220  tree serv-mods/
//    221  javac -d cli-mods --module-source-path "./*/src/main/java" --module-path serv-mods --module my.client.module
//    222  tree cli-mods/
//    223  rm -rf cli-mods/
//    224  ls
//    225  javac -d cli-mods --module-source-path "./*/src/main/java" --module-path serv-mods/ --module my.client.module
//    226  tree cli-mods/
//    227  rm -rf cli-mods/my.service.module
//    228  tree cli-mods/
//    229  ls
//    230  tree serv-mods/
//    231  tree cli-mods/
//    232  java --module-path serv-mods:cli-mods --module my.client.module/org.example.Main

