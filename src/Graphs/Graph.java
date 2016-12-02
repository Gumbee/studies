package Graphs;

import java.util.ArrayList;

/**
 * Created by mugeebhassan on 01/12/16.
 */
public class Graph<T> {

    private ArrayList<Node<T>> nodes;
    private ArrayList<Edge> edges;

    public Graph() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void addNode(T item){
        Node n = new Node(item);
        nodes.add(n);
    }

    public void addNode(Node<T> node){
        nodes.add(node);
    }

    /**
     * adds an edge to the graph and the appropriate nodes
     * @param start item representing the start node (node is created with this item)
     * @param end item representing the end node (node is created with this item)
     */
    public void addEdge(T start, T end) {
        Node<T> startNode = null;
        Node<T> endNode = null;

        for(Node n: nodes){
            if(n.getItem() == start){
                startNode = n;
            }

            if(n.getItem() == end){
                endNode = n;
            }
        }

        addEdge(startNode, endNode, 1);
    }

    public void addEdge(Node<T> start, Node<T> end) {
        addEdge(start, end, 1);
    }

    /**
     * adds an edge to the graph and the appropriate nodes
     * @param start the node where the edge starts
     * @param end the node where the edge ends
     * @param weight the weight of the edge
     */
    public void addEdge(Node<T> start, Node<T> end, int weight){
        if(start == null || end == null){
            return;
        }

        Edge e = new Edge(start, end, weight);

        edges.add(e);

        start.addEdge(e);
        end.addEdge(e);
    }

    /**
     * returns all the nodes of the graph
     */
    public final ArrayList<Node<T>> getNodes(){
        return nodes;
    }

    /**
     * returns all the edges of the graph
     */
    public final ArrayList<Edge> getEdges(){
        return edges;
    }


}
