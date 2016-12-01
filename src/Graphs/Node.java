package Graphs;

import java.util.ArrayList;

/**
 * Created by mugeebhassan on 01/12/16.
 */
public class Node<T> {

    private T item;
    private ArrayList<Edge> incomingEdges;
    private ArrayList<Edge> outgoingEdges;

    public Node(){
        this(null);
    }

    public Node(T item){
        incomingEdges = new ArrayList<>();
        outgoingEdges = new ArrayList<>();

        this.item = item;
    }

    public T getItem(){
        return item;
    }

    /**
     * adds an edge to the node. Depending on whether the edge starts or ends (or both) at this edge,
     * the edge is added to the appropriate array
     * @param e
     */
    public void addEdge(Edge e){
        if(e.getStart() == this){
            System.out.println("Added outgoing edge from " + item.toString());
            outgoingEdges.add(e);
        }

        if(e.getEnd() == this){
            System.out.println("Added incoming edge to " + item.toString());
            incomingEdges.add(e);
        }
    }
}
