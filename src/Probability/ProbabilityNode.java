package Probability;

import Models.Board;
import Models.Movement;

import java.util.ArrayList;
import java.util.List;

public class ProbabilityNode {
   private ProbabilityNode parent;
    private List<ProbabilityNode> children;
    private List<Movement> path;

    Board board;
    int depth;
    Movement movement;

    private double probability;


    public ProbabilityNode(ProbabilityNode parent, double probability, Movement movement) {
        this.parent = parent;
        this.children = new ArrayList<>();
        this.movement = movement;
        this.probability = movement.getProbability();
        this.path = new ArrayList<>();
    }



    public List<ProbabilityNode> getChildren() {
        return children;
    }

    public void setChildren(List<ProbabilityNode> children) {
        this.children = children;
    }


    public List<Movement> getPath() {
        return path;
    }

    public void setPath(List<Movement> path) {
        this.path = path;
    }

    public ProbabilityNode(ProbabilityNode parent, Board board, int depth, Movement movement) {
        this.parent = parent;
        this.board = board;
        this.depth = depth;
        this.movement = movement;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public ProbabilityNode getParent() {
        return parent;
    }

    public void setParent(ProbabilityNode parent) {
        this.parent = parent;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }
}
