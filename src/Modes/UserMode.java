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
import java.util.Stack;

public class UserMode {

    Player player1 = new Player (1);
    Player player2 = new Player (2);
    Board initBoard = new Board (player1,player2);
   // Stack<Board> stack = new Stack<> ();


    public UserMode() {
        play ();
    }

    void play(){
        initBoard.print ();
        Scanner input = new Scanner(System.in);
        Board newBoard = initBoard;

        while (true) {
            if (Actions.isWin (newBoard.getPlayer1 ())){
                System.out.println ("Player 1 you WIN!!!");
                break;
            }
            if (Actions.isWin (newBoard.getPlayer2 ())){
                System.out.println ("Player 2 you WIN!!!");
                break;
            }
            System.out.println ("Player 1 turn");
            System.out.println ("Throw The Seashells");
           // القاء الودعات -الرمية
            Movement movement = Actions.getMovement ();
            //return list of movements that include at least one khal
            ArrayList<Movement> movementArrayList = Actions.firstTurn (movement,newBoard.getPlayer1 (),new ArrayList<> ());
            if (movementArrayList.isEmpty ()) {
                System.out.println ("you can't play");
                break;
            }
            for (Movement movement1 :movementArrayList ){
                System.out.println (movement1.getSteps ());
            }
            int countOfKhal=0;
            ArrayList<Integer> steps = new ArrayList<> ();
                for (Movement movement1 : movementArrayList){
                    steps.add (movement1.getSteps ());
                }
                countOfKhal = Collections.frequency (steps,1 );
                //for each khal  make decision : add pawn, move one step
                for ( int i =0 ; i < countOfKhal ; i++ ){
                    System.out.println ("What to do with the khal");
                    System.out.println ("1 add or 2 play ");
                    int x = input.nextInt ();
                    if ( x == 1 ){
                        if (newBoard.getPlayer1 ().getPawnInBoard ().size ()<4) {
                            newBoard = Actions.addPawn (newBoard,newBoard.getPlayer1 ());
                            Helper.deleteElement (steps,1);
                        }
                        else
                            System.out.println ("you cant add more pawns");
                    }
                    else {
                        ArrayList<Pawn> pawns = Actions.getPossiblePawns (newBoard,Movement.khall,newBoard.getPlayer1 ());
                        if (pawns.isEmpty ())
                            System.out.println ("You don't have pawns");
                        else {
                            System.out.println ("Chose a pawn");
                            for (Pawn pawn : pawns){
                                System.out.println ("_1_ "+pawn.getPosition ());
                            }
                            System.out.println ("Enter the position");
                            int index = input.nextInt ();
                            newBoard =Actions.move (Movement.khall,newBoard.getPlayer1 ().getPawnByPosition (index),newBoard);
                        }
                    }
                    newBoard.print ();
                }




            break;
//            char key = input.next ().charAt (0);
//
//            if(key == 'e')
//            {
//                System.out.println("\n ============ EXIT =============\n");
//                break;
//            }
//            if(key == 'w')
//            {
//                if(MoveCheck.checkUp (previousBoard)) {
//                    stack.add (previousBoard);
//                    Board newBoard = MoveEgg.up (previousBoard);
//                    newBoard = MoveResult.move (newBoard,Actions.UP);
//                    previousBoard=newBoard;
//                    newBoard.drawBoard ();
//                }else {
//                    System.out.println ("can not move this way!" );
//                }
//            }
//
//            if(key == 's')
//            {
//                if(MoveCheck.checkDown (previousBoard)) {
//                    stack.add (previousBoard);
//                    Board newBoard = MoveEgg.down(previousBoard);
//                    newBoard = MoveResult.move (newBoard,Actions.DOWN);
//                    previousBoard=newBoard;
//                    newBoard.drawBoard ();
//                }else {
//                    System.out.println ("can not move this way!" );
//                }
//            }
//
//            if(key == 'd')
//            {
//                if(MoveCheck.checkRight (previousBoard)) {
//                    stack.add (previousBoard);
//
//                    Board newBoard = MoveEgg.right (previousBoard);
//                    newBoard = MoveResult.move (newBoard,Actions.RIGHT);
//                    previousBoard=newBoard;
//                    newBoard.drawBoard ();
//                }else {
//                    System.out.println ("can not move this way!" );
//                }
//            }
//
//            if(key == 'a')
//            {
//                if(MoveCheck.checkLeft (previousBoard)) {
//                    stack.add (previousBoard);
//                    Board newBoard = MoveEgg.left (previousBoard);
//                    newBoard = MoveResult.move (newBoard,Actions.LEFT);
//                    previousBoard=newBoard;
//                    newBoard.drawBoard ();
//                }else {
//                    System.out.println ("can not move this way!" );
//                }
//            }
//            if(key == 'u'){
//                previousBoard = stack.pop ();
//                previousBoard.drawBoard ();
//
//            }

        }
    }
}
