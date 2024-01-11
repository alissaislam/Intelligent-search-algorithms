package Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
    String [] board =new String[83];
    List<Pawn> pawnInBoard = new ArrayList<> ();

    int playerNumber;

    public Player() {
    }

    public Player(int playerNumber) {

        this.playerNumber = playerNumber;
    }

    public Player( List<Pawn> pawnInBoard,int playerNumber ) {
       // this.arrayList = arrayList;
        this.pawnInBoard = pawnInBoard;
        this.playerNumber = playerNumber;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public String[] getBoard() {
        return board;
    }

    public void setBoard(String[] board) {
        this.board = board;
    }

    public List<Pawn> getPawnInBoard() {
        return pawnInBoard;
    }

    public void setPawnInBoard(List<Pawn> pawnInBoard) {
        this.pawnInBoard = pawnInBoard;
    }

    public Pawn getPawnByPosition(int position){

        for (Pawn pawn: pawnInBoard){
            if (pawn.getPosition ()==position)
                return pawn;
        }

        return null;
    }

}
