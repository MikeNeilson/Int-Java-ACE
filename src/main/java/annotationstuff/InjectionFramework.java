package annotationstuff;

import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Properties;

public class InjectionFramework {
  public static void main(String[] args) throws Throwable {
    Properties props = new Properties();
    props.load(new FileReader("TestConfig.properties"));
    String className = props.getProperty("inject1");
    System.out.println("Classname to be injected is " + className);
    Class cl = Class.forName(className);

    Constructor cons = cl.getConstructor();
    Object obj = cons.newInstance();
    System.out.println(obj); // field should be null here, right?

    Field[] fields = cl.getDeclaredFields();
    for (Field f : fields) {
      System.out.println(f);
      InjectHere annot = f.getDeclaredAnnotation(InjectHere.class);
      if (annot != null) {
        String value = annot.value();
        System.out.println("**** found InjectHere, value is " + value);
        String toInject = props.getProperty(value);
        System.out.println("**** injecting: " + toInject);
        f.setAccessible(true);
        f.set(obj, toInject);
      }
    }
    System.out.println("object is now: " + obj);
  }
}
