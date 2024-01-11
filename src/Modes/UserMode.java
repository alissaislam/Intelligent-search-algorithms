package Modes;

import Actions.Actions;
import Helper.Helper;
import Models.Board;
import Models.Movement;
import Models.Pawn;
import Models.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class UserMode {

    Player player1  = new Player (1);
    Player player2  = new Player (2);
    Board initBoard = new Board (player1,player2);

    Scanner input   = new Scanner(System.in);

    public UserMode() {
        play ();
    }

    void play(){

        initBoard.print ();

        Board newBoard = initBoard;
        boolean player1Turn = true;


        while (true) {


            if (player1Turn) {
                if (Actions.isWin (newBoard.getPlayer2 ())) {
                    System.out.println ("Player 2 you WIN!!!");
                    break;
                }
            }

            else {
                if (Actions.isWin (newBoard.getPlayer1 ())) {
                    System.out.println ("Player 1 you WIN!!!");
                    break;
                }
            }

            ArrayList <Movement> movementArrayList=new ArrayList<> () ;

            if (player1Turn && newBoard.getPlayer1 ().getPawnInBoard ().isEmpty () ) {
                movementArrayList = initialTurn (newBoard, 1);
                if (movementArrayList.isEmpty ()){
                    player1Turn = false;
                    continue;
                }
            }
            else if (!player1Turn && newBoard.getPlayer2 ().getPawnInBoard ().isEmpty ()){
                movementArrayList = initialTurn (newBoard, 2);
                if (movementArrayList.isEmpty ()){
                    player1Turn = true;
                    continue;
                }
            }

            else if (player1Turn && !newBoard.getPlayer1 ().getPawnInBoard ().isEmpty ()){
                System.out.println ("Player 1 turn");
                movementArrayList = Actions.myTern ();
            }

            else if (!player1Turn && !newBoard.getPlayer2 ().getPawnInBoard ().isEmpty ()){
                System.out.println ("Player 2 turn");
                movementArrayList = Actions.myTern ();
            }


            printArray (movementArrayList);

            ArrayList<Integer> steps = getSteps (movementArrayList);

            if (player1Turn)
                newBoard = playTheKhal (newBoard,steps,1);
            else
                newBoard = playTheKhal (newBoard,steps,2);

            if (player1Turn)
                newBoard = playNormalMove (newBoard,steps,1);
            else
                newBoard = playNormalMove (newBoard,steps,2);

            player1Turn =! player1Turn;
        }
    }

    private Board playTheKhal(Board board,ArrayList<Integer> steps,int playerNumber){

        int countOfKhal = Collections.frequency (steps,1 );

        for ( int i =0 ; i < countOfKhal ; i++ ) {
            System.out.println ("What to do with the khal");
            System.out.println ("1. add or 2. play ");
            int x = input.nextInt ();
            if (x == 1) {
                if (board.getPlayerById (playerNumber).getPawnInBoard ().size () < 4) {
                    board = Actions.addPawn (board, board.getPlayerById (playerNumber));
                    Helper.deleteElement (steps, 1);
                }
                else {
                    System.out.println ("you cant add more pawns");
                    i--;
                }
            }
            else {
                ArrayList<Pawn> pawns = Actions.getPossiblePawns (board, 1, board.getPlayerById (playerNumber));
                if (pawns.isEmpty ()) {
                    System.out.println ("You don't have pawns");
                    i--;
                }
                else {
                    Helper.deleteElement (steps,1);
                    board = movePawn (board,1,playerNumber,pawns);
                }
            }
            board.print ();
        }
        return board;
    }
    private Board playNormalMove(Board board ,ArrayList <Integer> steps,int playerNumber){
        for (int step : steps){
            System.out.println ("chose the pawn to move it by " + step);
            ArrayList<Pawn> pawns = Actions.getPossiblePawns ( board ,step ,board.getPlayerById (playerNumber) );
            if (pawns.isEmpty ())
                System.out.println ("You can't play");
            else {
                board = movePawn (board,step,playerNumber,pawns);
                board.print ();
            }
        }
        return board;
    }

    private ArrayList<Movement> initialTurn(Board board ,int playerNumber){
        System.out.println ("Player "+playerNumber+" turn");
        System.out.println ("Throw The Seashells");
        //input.nextLine ();
        Movement movement = Actions.getMovement ();
        ArrayList<Movement> movementArrayList = Actions.firstTurn ( movement, board.getPlayerById (playerNumber), new ArrayList<> () );
        return movementArrayList;
    }
    private void printArray(ArrayList<Movement> movementArrayList){
        for (Movement movement : movementArrayList ){
            System.out.println ( movement.getSteps () );
        }
    }
    private ArrayList<Integer> getSteps(ArrayList<Movement> movementArrayList){
        ArrayList<Integer> steps = new ArrayList<> ();
        for (Movement movement1 : movementArrayList){
            steps.add (movement1.getSteps ());
        }
        return steps;
    }
    private Board movePawn(Board board,int step,int playerNumber,ArrayList<Pawn> pawns){
        System.out.println ("Chose a pawn");
        for (Pawn pawn : pawns) {
            System.out.println ("_"+playerNumber+"_ " + pawn.getPosition ());
        }
        System.out.println ("Enter the position");
        int index = input.nextInt ();
        board = Actions.move (step, board.getPlayerById (playerNumber).getPawnByPosition (index), board);
        return board;
    }
}
