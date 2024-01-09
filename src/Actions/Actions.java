package Actions;

import Models.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Actions {
  static   List<Integer> shera = new ArrayList<> (Arrays.asList (10,21,27,38,44,55,61,72));

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

    public static boolean checkMove(Board board,Movement movement, Pawn pawn){

        if (83 - pawn.getPosition ()< movement.getSteps ())
                return false;
        int index = pawn.getPosition ()+movement.getSteps ();
        if (shera.contains (index)) {
            if (!setOnShera (pawn, movement.getSteps (), board))
                return false;
        }
       return true;
        }

    public static ArrayList<Pawn> getPossiblePawns (Board board,Movement movement,Player player){
        ArrayList<Pawn> possiblePawns = new ArrayList<> ();
        for (Pawn pawn : player.getPawnInBoard ()){
            if (checkMove (board,movement,pawn))
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


    public static Board move(Movement movement ,Pawn pawn ,Board board){

        Board newBoard = boardDeepCopy (board);
        for (Pawn newPawn : newBoard.getPlayerById (pawn.getPlayer ().getPlayerNumber ()).getPawnInBoard ()){
            if (newPawn.getPosition ()==pawn.getPosition ()) {
                newPawn.setPosition (newPawn.getPosition () + movement.getSteps ());
            }
        }
        return newBoard;
    }


    public static ArrayList<Node> getNextStates(Node node, Player player){
        ArrayList<Node> nextStates = new ArrayList<> ();

        for (Movement movement : Movement.movementArrayList) {
            ArrayList<Pawn> possiblePawns = getPossiblePawns (node.getBoard (),movement, player);
            for (Pawn pawn : possiblePawns){
                Board newBoard = move (movement,pawn,node.getBoard ());
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
            if(!movement.isKhal ())
                return false;
        }
        return true;
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

}
