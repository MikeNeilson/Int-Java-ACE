package annotationstuff;

import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Properties;

public class TestHarness {
  public static void main(String[] args) throws Throwable {
    Properties props = new Properties();
    props.load(new FileReader("TestConfig.properties"));
    String className = props.getProperty("test1");
    System.out.println("Classname to be tested is " + className);
    Class cl = Class.forName(className);

    Constructor cons = cl.getConstructor();
    Object obj = cons.newInstance();
    System.out.println(obj);

    Method[] methods = cl.getDeclaredMethods();
//    Method[] methods = cl.getMethods();
    for (Method m : methods) {
      System.out.println("> " + m);
      RunMe annot = m.getAnnotation(RunMe.class);
      if (annot != null) {
        System.out.println("****** RunMe found!!!");
        System.out.println("****** name is " + annot.name());
        System.out.println("****** value is " + annot.value());
        m.setAccessible(true);
        /*var res = */m.invoke(obj);
      }
    }
  }
}
