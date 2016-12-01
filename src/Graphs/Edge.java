package Graphs;

/**
 * Created by mugeebhassan on 01/12/16.
 */
public class Edge {

    private Node start;
    private Node end;

    private int weight;

    public Edge(Node start, Node end) {
        this(start, end, 1);
    }

    public Edge(Node start, Node end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public Node getStart(){
        return start;
    }

    public Node getEnd(){
        return end;
    }

}