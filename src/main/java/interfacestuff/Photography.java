package interfacestuff;

import java.util.List;

interface Photographer {
  /*public abstract*/ String takePhoto(String subject);
}

class ParentWithCamera implements Photographer {
  @Override
  public String takePhoto(String subject) {
    System.out.println("Click, smile kids! click click, click");
    return "blotchy picture of " + subject + " but hey, the kids are smiling";
  }
}

class SpySatellite implements Photographer {
  public String takePhoto(String subject) {
    System.out.println("bleep, bleep, orientaion thruster blast, bleep");
    return "grainy photo of you " + subject + " and I can read your license plate";
  }
}

public class Photography {
  public static void main(String[] args) {
    List<Photographer> photogs = List.of(
        new ParentWithCamera(),
        new SpySatellite());
    for (var p : photogs) {
      System.out.println("Photographer " + p.getClass().getName()
          + " takes this photo: " + p.takePhoto("Someone else's kids"));
    }


    ParentWithCamera pwc = new ParentWithCamera();
    String myImage = pwc.takePhoto("My Kids");
    System.out.println(myImage);
    SpySatellite ss = new SpySatellite();
    String myImage2 = ss.takePhoto("My Kids");
    System.out.println(myImage2);
  }
}
