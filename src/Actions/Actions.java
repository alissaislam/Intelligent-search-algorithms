package Actions;

import Helper.Helper;
import Models.*;

import java.util.*;

public class Actions {
  static   List<Integer> shera = new ArrayList<> (Arrays.asList (10,21,27,38,44,55,61,72));
    //return false if you can't but pawn on shera because the enemy is on it
    public static boolean setOnShera(Pawn pawn,Movement movement,Board board){
        Player playerEnemy ;
        //next index
        int index =pawn.getPosition ()+movement.getSteps();
        //if the current player is the 1 player
        if (pawn.getPlayer ().getPlayerNumber () == board.getPlayer1 ().getPlayerNumber ())
            playerEnemy = board.getPlayer2 ();
            //if the current player is the 2 player
        else
            playerEnemy = board.getPlayer1 ();

        for (Pawn pawn1 : playerEnemy.getPawnInBoard ()){
            if (pawn1.getPosition ()==index)
                return false;
        }
        return true;
    }

    //can move (movement steps more than possible step, if can stand on shera?, )
    public static boolean checkMove(Board board,Movement movement, Pawn pawn){
        //الحركات الباقية للوصول للنهاية لضمان عدم الخرج من الرقعة
        if (83 - pawn.getPosition ()< movement.getSteps ())
            return false;
        //موقع الحجر الناتج عن الحركة
        int index = pawn.getPosition ()+movement.getSteps ();
        if (shera.contains (index)) {
            if (!setOnShera (pawn, movement, board))
                return false;
        }
        return true;
    }

    //return list of the  pawn that can move
    public static ArrayList<Pawn> getPossiblePawns (Board board,Movement movement,Player player){
        ArrayList<Pawn> possiblePawns = new ArrayList<> ();
        for (Pawn pawn : player.getPawnInBoard ()){
            if (checkMove (board,movement,pawn))
                possiblePawns.add (pawn);
        }
        return possiblePawns;
    }

    //random move
    public static Movement getMovement(){
        Random random =new Random ();
        int index = random.nextInt (7);
        return Movement.movementArrayList.get (index);
    }

    //allow the player to  play 10 time and store it in list
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

   //  move  the pawn
    public static Board move(Movement movement ,Pawn pawn ,Board board){
        Board newBoard = boardDeepCopy (board);
        for (Pawn newPawn : newBoard.getPlayerById (pawn.getPlayer ().getPlayerNumber ()).getPawnInBoard ()){
            if (newPawn.getPosition ()==pawn.getPosition ()) {
                newPawn.setPosition (newPawn.getPosition () + movement.getSteps ());
                killing(board,pawn);
            }
        }
        return newBoard;
    }

    //generate next Node fot the algo
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

    // all pawn on board + all pawn in the end
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
    //check if can play in the start turn : there is no pawn on the board , the movement include khal
    public static boolean start(Movement movement,Player player){
        if(player.getPawnInBoard ().size ()==0){
            if(movement.isKhal ())
                return true;
        }
        return false;
    }
    //search for khal,
    public static ArrayList<Movement> firstTurn(Movement movement, Player player,ArrayList<Movement> movementArrayList){
        if (start (movement,player)){
            //الرميات الجديدةا
             movementArrayList = Actions.myTern ();
             // الرمية الحالية من تابع  play
             movementArrayList.add (movement);
             //خال لان الرمية الاصلية تحتوي على خال
             movementArrayList.add (Movement.khall);
             return movementArrayList;
        }
        //no khal, but can playQAgain
        else if (movement.isPlayAgain ()){
            //اعادة الرمية مرة واحدة
            Movement movement1 = getMovement ();
            //اضافة الرمية الجديدة لمصفوات الرميات
            movementArrayList.add (movement1);
            //اعادة استدعاء للبحث عن خال
            return firstTurn (movement1,player,movementArrayList);
        }
        // لا يمكن للتابع ان يلعب اذا لم يظهر له خال وبالتالي يعيد مصفوفة حركات فارغة
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


    public static  void killing(Board board,Pawn pawn){
        Player enemy ;

        //if the current player is the 1 player
        if (pawn.getPlayer ().getPlayerNumber () == board.getPlayer1 ().getPlayerNumber ())
            enemy = board.getPlayer2 ();
            //if the current player is the 2 player
        else
            enemy = board.getPlayer1 ();

        if(!shera.contains(pawn.getPosition()) && !shera.contains(pawn.getPosition())){
             for (Pawn pawn1 : enemy.getPawnInBoard()) {
                 if(pawn.getPosition() == pawn1.getPosition()){
                     enemy.getPawnInBoard().remove(pawn1);
                     System.out.println("You Killed a pawn to your enemy");
                 }
             }
        }
    }

}
