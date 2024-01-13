package Models;

import java.util.ArrayList;
import java.util.Arrays;

public class Movement {
    int steps;
    boolean khal;
    boolean playAgain;

    private double probability;
  public static Movement dest = new Movement (10,true,true,0.2);
  public static Movement beng = new Movement (25,true,true,0.15);
  public static Movement shake = new Movement (6,false,true,0.1);
  public static Movement bara = new Movement (12,false,true,0.2);
  public static Movement doaq = new Movement (2,false,false,0.1);
  public static Movement three = new Movement (3,false,false,0.15);
  public static Movement four = new Movement (4,false,false,0.1);
  public static Movement khall = new Movement (1,false,false);

    public static ArrayList<Movement> movementArrayList = new ArrayList<> ( Arrays.asList (dest,beng,shake,bara,doaq,three,four) );


    public Movement(int steps, boolean khal, boolean playAgain ,double probability ) {
        this.steps = steps;
        this.khal = khal;
        this.probability = probability;
        this.playAgain = playAgain;
    }
    public Movement(int steps, boolean khal, boolean playAgain ) {
        this.steps = steps;
        this.khal = khal;
        this.playAgain = playAgain;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
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
