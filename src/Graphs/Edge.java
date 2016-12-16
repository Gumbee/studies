package Graphs;

/**
 * Created by mugeebhassan on 01/12/16.
 */
public class Edge<T> implements Comparable<Edge<T>> {

    private Vertex<T> start;
    private Vertex<T> end;

    private double weight;

    public Edge(Vertex<T> start, Vertex<T> end) {
        this(start, end, 1);
    }

    public Edge(Vertex<T> start, Vertex<T> end, double weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    /*==========================================
     * Getter Methods
     ===========================================*/

    public Vertex<T> getStart(){
        return start;
    }

    public Vertex<T> getEnd(){
        return end;
    }

    public double getWeight(){
        return weight;
    }

    /*==========================================
     * Util Methods
     ===========================================*/

    @Override
    public int compareTo(Edge<T> o) {
        return weight<o.weight?-1:weight>o.weight?1:0;
    }
}
