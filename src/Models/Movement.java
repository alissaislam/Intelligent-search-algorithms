package Models;

import java.util.ArrayList;
import java.util.Arrays;

public class Movement {
    int steps;
    boolean khal;
    boolean playAgain;
  public static Movement dest = new Movement (10,true,true);
  public static Movement beng = new Movement (25,true,true);
  public static Movement shake = new Movement (6,false,true);
  public static Movement bara = new Movement (12,false,true);
  public static Movement doaq = new Movement (2,false,false);
  public static Movement three = new Movement (3,false,false);
  public static Movement four = new Movement (4,false,false);
  public static Movement khall = new Movement (1,false,false);

    public static ArrayList<Movement> movementArrayList = new ArrayList<> (Arrays.asList (dest,beng,shake,bara,doaq,three,four));


    public Movement(int steps, boolean khal, boolean playAgain) {
        this.steps = steps;
        this.khal = khal;
        this.playAgain = playAgain;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public boolean isKhal() {
        return khal;
    }

    public void setKhal(boolean khal) {
        this.khal = khal;
    }

    public boolean isPlayAgain() {
        return playAgain;
    }

    public void setPlayAgain(boolean playAgain) {
        this.playAgain = playAgain;
    }
}
