package myserviceimplpackage;

import myservice.MyServiceIf;

public class MyServiceImpl implements MyServiceIf {
  @Override
  public String getMessage() {
    return "I'm a message from a service";
  }
}
