//package Modes;
//
//import Actions.Actions;
//import Models.Movement;
//import Models.Node;
//import Models.prob.ProbabilityNode;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ExMinMax {
//
//    private ArrayList<ProbabilityNode> stepsArray = new ArrayList<>();
//    private Node alphaBetaMax (Node node, double alpha, double beta, ArrayList<Movement> movements,int depth) {
//
//        if (depth == 0 || Actions.isWin (node.getBoard ().getPlayer1 ()) || Actions.isWin (node.getBoard ().getPlayer2 ())) {
//            node.setExpectedValue (node.calcEva ());
//            return node;
//        }
//
//        Node bestNode = new Node ();
//        double value = Double.MIN_VALUE;
//        List<Node> nodesList = genNodes (node,movements);
//
//        for (Node moves : nodesList) {
//            double expectedValue = ch(moves,true,alpha,beta,depth);
//            if (expectedValue > value) {
//                bestNode = moves;
//                value = expectedValue;
//            }
//
//            alpha = Math.max(alpha, value);
//            if (alpha >= beta) {
//                break; // Beta cut-off
//            }
//        }
//        return bestNode;
//    }
//
//    private Node alphaBetaMin(Node  node, double alpha, double beta, ArrayList<Movement> movements,int depth) {
//
//        if (depth == 0 || Actions.isWin (node.getBoard ().getPlayer1 ()) || Actions.isWin (node.getBoard ().getPlayer2 ())) {
//            node.setExpectedValue (node.calcEva ());
//            return node;
//        }
//
//        Node bestNode = new Node ();
//        double value = Double.MAX_VALUE;
//        List<Node> nodesList = genNodes (node,movements);
//
//        for (Node node1 : nodesList) {
//            double expectedValue = ch(node1,false,alpha,beta,depth);
//            if (expectedValue < value) {
//                bestNode = node1;
//                value = expectedValue;
//            }
//            beta = Math.min(beta, value);
//            if (beta <= alpha) {
//                break; // Alpha cut-off
//            }
//        }
//        return bestNode;
//    }
//
//    private double ch(Node node,boolean comingFromMax ,double alpha, double beta,int depth ){
//        double sum=0;
//
//        if (comingFromMax){
//            double multy;
//            for ( ProbabilityNode probabilityNode : stepsArray ) {
//              Node  nodeFromMax = alphaBetaMin (node, alpha, beta, probabilityNode.getPath (), depth);
//                multy = nodeFromMax.getExpectedValue () *  probabilityNode.getProbability ();
//                sum+=multy;
//            }
//        }
//        else {
//            double multy;
//            for ( ProbabilityNode probabilityNode : stepsArray ) {
//                Node  nodeFromMax = alphaBetaMax (node, alpha, beta, probabilityNode.getPath (), depth);
//                multy = nodeFromMax.getExpectedValue () *  probabilityNode.getProbability ();
//                sum+=multy;
//            }
//        }
//
//        return sum;
//    }
//
//    private ArrayList <Node> genNodes(Node node,ArrayList<Movement> movements){
//        return new ArrayList<> ();
//    }
//
//}
