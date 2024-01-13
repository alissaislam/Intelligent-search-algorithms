package Probability;

import Models.Movement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public  class TreeSimulation {

    public static ArrayList<Movement> MOVEMENTS = Movement.movementArrayList;






    public static void generateTree(ProbabilityNode parent, int depth) {
        if (depth > 10) {
            return;
        }

        for (Movement movement : MOVEMENTS) {
            double probability = parent.getProbability() * movement.getProbability();
            ProbabilityNode child = new ProbabilityNode(parent, probability, movement);
            parent.getChildren().add(child);

            if (movement.isPlayAgain()) {
                generateTree(child, depth + 1);
            }
        }
    }

    public static List<PathWithProbability> iteratePaths(ProbabilityNode root) {
        List<PathWithProbability> paths = new ArrayList<>();
        Stack<ProbabilityNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            ProbabilityNode node = stack.pop();
            List<Movement> currentPath = new ArrayList<>(node.getPath());
            double currentProb = node.getProbability();

            currentPath.add(node.getMovement());

            if (currentPath.size() > 10) {
                paths.add(new PathWithProbability(new ArrayList<>(currentPath), currentProb));
            } else {
                for (ProbabilityNode child : node.getChildren()) {
                    stack.push(child);
                    child.setPath(new ArrayList<>(currentPath));
                    child.setProbability(currentProb * child.getProbability()); // Multiply by parent's probability
                }
            }
        }
        return paths;
    }



    public static class PathWithProbability {
        List<Movement> path;
        double probability;

        public PathWithProbability(List<Movement> path, double probability) {
            this.path = path;
            this.probability = probability;
        }

        public void printPath(){
            for (Movement movement : path){
                System.out.print( movement.getSteps() + ", ");
            }
            System.out.print(" probability:"+probability);
            System.out.println();
        }
    }
}