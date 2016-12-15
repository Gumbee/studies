package Graphs;

/**
 * Created by mugeebhassan on 01/12/16.
 */
public class Edge<T> implements Comparable<Edge<T>> {

    private Vertex<T> start;
    private Vertex<T> end;

    private int weight;

    public Edge(Vertex<T> start, Vertex<T> end) {
        this(start, end, 1);
    }

    public Edge(Vertex<T> start, Vertex<T> end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public Vertex<T> getStart(){
        return start;
    }

    public Vertex<T> getEnd(){
        return end;
    }

    /*==========================================
         * Util Methods
     ===========================================*/

    @Override
    public int compareTo(Edge<T> o) {
        return weight-o.weight;
    }
}
