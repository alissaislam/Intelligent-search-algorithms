package Modes;

import Actions.Actions;
import Models.Movement;
import Models.Node;

import java.util.ArrayList;
import java.util.List;

public class ExMinMax {

    private Node alphaBetaMax(Node node, int alpha, int beta, ArrayList<Movement> movements,int depth) {

        if (depth ==0 || Actions.isWin (node.getBoard ().getPlayer1 ()) || Actions.isWin (node.getBoard ().getPlayer2 ()))
            return node;

        Node bestNode = new Node ();
        int value = Integer.MIN_VALUE;
        List<Node> nodesList = genNodes (node,movements);

        for (Node moves : nodesList) {
            int eval = ch(moves,true).calcEva ();
            if (eval > value) {
                bestNode = moves;
                value = eval;
            }
            alpha = Math.max(alpha, value);
            if (alpha >= beta) {
                break; // Beta cut-off
            }
        }
        return bestNode;
    }

    private Node alphaBetaMin(Node  node, int alpha, int beta, ArrayList<Movement> movements,int depth) {

        if (depth ==0 || Actions.isWin (node.getBoard ().getPlayer1 ()) || Actions.isWin (node.getBoard ().getPlayer2 ()))
            return node;

        Node bestNode = new Node ();
        int value = Integer.MAX_VALUE;
        List<Node> nodesList = genNodes (node,movements);

        for (Node moves : nodesList) {
            int eval = ch(moves,false).calcEva ();
            if (eval < value) {
                bestNode = moves;
                value = eval;
            }
            beta = Math.min(beta, value);
            if (beta <= alpha) {
                break; // Alpha cut-off
            }
        }
        return bestNode;
    }

    private Node ch(Node node,boolean comingFromMax){

        


        return new Node ();
    }

    private ArrayList <Node> genNodes(Node node,ArrayList<Movement> movements){
        return new ArrayList<> ();
    }

}
