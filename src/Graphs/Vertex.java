package Graphs;

import java.util.ArrayList;

/**
 * Created by mugeebhassan on 01/12/16.
 */
public class Vertex<T> implements Comparable<Vertex<T>> {

    // when assigning a number to each vertex, that number is stored in here
    public int index;
    // placeholder for whatever value needs to be stored in this vertex temporarily
    public double placeholder;
    // whether or not the placeholder was initialized already (false could for example represent an infinity value
    // for the placeholder-value)
    public boolean placeholderInitialized;

    private T item;
    private ArrayList<Edge<T>> incomingEdges;
    private ArrayList<Edge<T>> outgoingEdges;

    public Vertex(){
        this(null, 0);
        this.placeholderInitialized = false;
    }

    public Vertex(T item){
        this(item, 0);
        this.placeholderInitialized = false;
    }

    public Vertex(T item,double placeholder){
        incomingEdges = new ArrayList<>();
        outgoingEdges = new ArrayList<>();

        this.item = item;
        this.placeholder = placeholder;
        this.placeholderInitialized = true;
    }

    /**
     * adds an edge to the node. Depending on whether the edge starts or ends (or both) at this edge,
     * the edge is added to the appropriate array
     * @param e
     */
    public void addEdge(Edge<T> e){
        if(e.getStart() == this){
            outgoingEdges.add(e);
        }

        if(e.getEnd() == this){
            incomingEdges.add(e);
        }
    }

    /**
     * returns this node's incoming degree
     */
    public final int degIn(){
        return incomingEdges.size();
    }

    /**
     * returns this node's outgoing degree
     */
    public final int degOut(){
        return outgoingEdges.size();
    }

    /*==========================================
     * Getter Methods
     ===========================================*/

    public final T getItem(){
        return item;
    }

    public final ArrayList<Edge<T>> getOutgoingEdges(){
        return outgoingEdges;
    }

    public final ArrayList<Edge<T>> getIncomingEdges(){
        return incomingEdges;
    }

    /*==========================================
     * Util Methods
     ===========================================*/

    @Override
    public String toString() {
        return item.toString();
    }

    @Override
    public int compareTo(Vertex<T> o) {
        return o.placeholder>placeholder?-1:o.placeholder<placeholder?1:0;
    }
}
