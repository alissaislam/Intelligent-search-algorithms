package Actions;

import Models.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Actions {
   static   List<Integer> shera = new ArrayList<> (Arrays.asList (10,21,27,38,44,55,61,72));
   static List<Integer> safe = new ArrayList<> (Arrays.asList (0,1,2,3,4,5,6,82,81,80,79,78,77,76,75,10,21,27,38,44,55,61,72));

    public static boolean setOnShera(Pawn pawn,int movement,Board board){
        Player playerEnemy ;
        int index =pawn.getPosition ()+movement;

            if (pawn.getPlayer ().getPlayerNumber () == board.getPlayer1 ().getPlayerNumber ())
                playerEnemy = board.getPlayer2 ();
            else
                playerEnemy = board.getPlayer1 ();

            for (Pawn pawn1 : playerEnemy.getPawnInBoard ()){
                if (pawn1.getPosition ()==index)
                    return false;
            }

            return true;
    }

    public static boolean checkMove(Board board,int steps, Pawn pawn){

        if (83 - pawn.getPosition ()< steps)
                return false;
        int index = pawn.getPosition ()+steps;
        if (shera.contains (index)) {
            if (!setOnShera (pawn, steps, board))
                return false;
        }
       return true;
        }

    public static ArrayList<Pawn> getPossiblePawns (Board board,int steps,Player player){
        ArrayList<Pawn> possiblePawns = new ArrayList<> ();
        for (Pawn pawn : player.getPawnInBoard ()){
            if (checkMove (board,steps,pawn))
                possiblePawns.add (pawn);
        }
        return possiblePawns;
    }

    public static Movement getMovement(){
        Random random =new Random ();
        int index = random.nextInt (7);
        return Movement.movementArrayList.get (index);
    }

    private static ArrayList<Movement> myTernHelper(int count,ArrayList<Movement> ternList){
        if (count==10)
            return ternList;

        Movement movement = getMovement ();
        ternList.add (movement);
        if (movement.isKhal ())
            ternList.add (Movement.khall);

        if (movement.isPlayAgain ())
            myTernHelper (count+1,ternList);

        return ternList;

    }

    public static ArrayList<Movement> myTern(){
        return myTernHelper (1,new ArrayList<> ());
    }


    public static Board move(int steps ,Pawn pawn ,Board board){

        Board newBoard = boardDeepCopy (board);
        ArrayList <Pawn> pawns = new ArrayList<> ();
        for (Pawn newPawn : newBoard.getPlayerById (pawn.getPlayer ().getPlayerNumber ()).getPawnInBoard ()){
            if (newPawn.getPosition ()==pawn.getPosition ()) {
                pawns.add (newPawn);
                killing (board,newPawn,steps);
            }
        }
        pawns.get (0).setPosition (pawns.get (0).getPosition ()+steps);
        return newBoard;
    }


    public static ArrayList<Node> getNextStates(Node node, Player player){

        ArrayList<Node> nextStates = new ArrayList<> ();

        for (Movement movement : Movement.movementArrayList) {
            ArrayList<Pawn> possiblePawns = getPossiblePawns (node.getBoard (),movement.getSteps (), player);
            for (Pawn pawn : possiblePawns){
                Board newBoard = move (movement.getSteps (),pawn,node.getBoard ());
                Node newNode = new Node (node,newBoard,node.getDepth ()+1,movement);
                nextStates.add (newNode);
            }
        }
        return nextStates;
    }

    public static boolean isWin(Player player){
        if (player.getPawnInBoard ().size ()==4) {
            for (Pawn pawn : player.getPawnInBoard ()) {
                if (pawn.getPosition () != 83)
                    return false;
            }
            return true;
        }
            return false;
    }

    public static boolean start(Movement movement,Player player){
        if(player.getPawnInBoard ().size ()==0){
            if(movement.isKhal ())
                return true;
        }
        return false;
    }
    public static ArrayList<Movement> firstTurn(Movement movement, Player player,ArrayList<Movement> movementArrayList){
        if (start (movement,player)){
             movementArrayList = Actions.myTern ();
             movementArrayList.add (movement);
             movementArrayList.add (Movement.khall);
             return movementArrayList;
        }
        else if (movement.isPlayAgain ()){
            Movement movement1 = getMovement ();
            movementArrayList.add (movement1);
            return firstTurn (movement1,player,movementArrayList);
        }

            return new ArrayList<> ();
    }

    public static Pawn pawnDeepCopy(Pawn pawn){
        Pawn newPawn= new Pawn (pawn.getPosition (),pawn.getPlayer ());
        return newPawn;
    }
    public static Player playerDeepCopy(Player player){
        ArrayList<Pawn> pawnArrayList = new ArrayList<> ();
        for (Pawn pawn :player.getPawnInBoard ()){
            Pawn newPawn = pawnDeepCopy (pawn);
            pawnArrayList.add (newPawn);
        }
        Player newPlayer = new Player (pawnArrayList, player.getPlayerNumber ());
        return newPlayer;
    }
    public static Board boardDeepCopy(Board board){
        Player newPlayer1 = playerDeepCopy (board.getPlayer1 ());
        Player newPlayer2 = playerDeepCopy (board.getPlayer2 ());
        Board newBoard = new Board (newPlayer1,newPlayer2);
        return newBoard;
    }

    public static Board addPawn(Board board,Player player){
        Pawn newPawn = new Pawn (0,player);
        Board newBoard = boardDeepCopy (board);
        newBoard.getPlayerById (player.getPlayerNumber ()).getPawnInBoard ().add (newPawn);
        return newBoard;
    }

    public static  void killing(Board board,Pawn pawn,int steps){
        Player enemy ;
        int newPostion;
        Pawn deadPawn = new Pawn (-1,new Player ());
        //if the current player is the 1 player
        if (pawn.getPlayer ().getPlayerNumber () == board.getPlayer1 ().getPlayerNumber ())
            enemy = board.getPlayer2 ();
            //if the current player is the 2 player
        else
            enemy = board.getPlayer1 ();

        if(!safe.contains(pawn.getPosition())){
            for (Pawn pawn1 : enemy.getPawnInBoard()) {
                newPostion = pawn.getPosition () + steps;
                if(Math.abs( newPostion - pawn1.getPosition() ) == 34){
                    deadPawn = pawn1;
                    System.out.println("You Killed a pawn to your enemy");
                }
            }
            if (deadPawn.getPosition ()!=-1)
            enemy.getPawnInBoard ().remove (deadPawn);
        }
    }

}
