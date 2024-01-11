package Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
Player player1 ;
Player player2 ;

String [] board =new String[84];
String [] player2FK = new String[7];
String [] player2LK = new String[8];


    public Board(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public String[] getBoard() {
        return board;
    }

    public void setBoard(String[] board) {
        this.board = board;
    }
    public  Player getPlayerById(int id){
        if (id==1)
            return player1;
        else
            return player2;
    }

    public void print(){
        List<Integer> shera = new ArrayList<> (Arrays.asList (10,21,27,38,44,55,61,72));
        //set base board
        for (int i=0 ;i<board.length;i++){
            board[i]=".";
            if (shera.contains (i))
                board[i]="X";
            if (i==25 || i==59)
                board[i]="\\";
            if (i==41)
                board[i] ="*";
            if (i==6)
                board[i]="*";
            if (i==76)
                board[76]="*";
        }
        for (int i=0 ;i<player2FK.length;i++){
            player2FK[i]=".";
        }
        for (int i=0 ;i<player2LK.length;i++){
            player2LK[i]=".";
        }
        //add player 1 pawns
        for (Pawn pawn : player1.getPawnInBoard ()){
            board[pawn.getPosition ()]="1";
        }
        //add player 2 pawns
        for (Pawn pawn : player2.getPawnInBoard ()){
            if (pawn.getPosition ()<7)
                player2FK[pawn.getPosition ()]="2";
            if ( 7 <= pawn.getPosition () && pawn.getPosition () <= 75) {
                int index = pawn.getPosition () + 34;
                if (41 <= index && index <= 75)
                board[ index ] = "2";
                if (75<index && index < 109)
                board[ index-68 ]="2";
            }
            if ( pawn.getPosition () > 75 ) {
                player2LK[pawn.getPosition () - 76] = "2";
            }
        }
        //print the main board
        for (int i=0 ;i<board.length*2;i++){
            System.out.print ("_");
        }
        System.out.println ();
        for (int i=0 ;i<board.length;i++){
            System.out.print ("|"+board[i]);
        }
        System.out.println ();
        for (int i=0 ;i<board.length*2;i++){
            System.out.print ("-");
        }
        System.out.println ();
        //print the first kitchen for player2
        for (int i=0 ;i<player2FK.length*2;i++){
            System.out.print ("_");
        }
        System.out.println ();
        for (int i=0 ;i<player2FK.length;i++){
            System.out.print ("|"+player2FK[i]);
        }
        System.out.println ();
        for (int i=0 ;i<player2FK.length*2;i++){
            System.out.print ("-");
        }
        System.out.println ();
        //print the last kitchen for player2
        for (int i=0 ;i<(player2LK.length*2)+1;i++){
            System.out.print ("_");
        }
        System.out.println ();
        for (int i=0 ;i<player2FK.length+1;i++){
            System.out.print ("|"+player2LK[i]);
        }
        System.out.println ();
        for (int i=0 ;i<(player2LK.length*2)+1;i++){
            System.out.print ("-");
        }
        System.out.println ();
    }
}
