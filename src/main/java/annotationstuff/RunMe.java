package annotationstuff;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RunMe {
  // types can be:
  // primitive
  // String
  // Class
  // Enum
  // Annotation
  // Arrays of the ABOVE
  int value();
  String name() default "Unknown";
}
