package Graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by mugeebhassan on 01/12/16.
 */
public class Graph<T> {

    private ArrayList<Vertex<T>> vertices;
    private ArrayList<Edge<T>> edges;

    private boolean directed = true;

    public Graph(){
        this(true);
    }

    /**
     * @param directed true if the graph is directed and false otherwise (in undirected graphs, every edge is added twice,
     *                 once in both directions)
     */
    public Graph(boolean directed) {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        this.directed = directed;
    }


    /*==========================================
     * Graph Operations
     ===========================================*/

    public void addVertex(T item){
        Vertex n = new Vertex(item);
        vertices.add(n);
    }

    public void addVertex(Vertex<T> vertex){
        vertices.add(vertex);
    }

    /**
     * adds an edge to the graph and the appropriate vertices
     * @param start item representing the start vertex (vertex is created with this item)
     * @param end item representing the end vertex (vertex is created with this item)
     */
    public void addEdge(T start, T end) {
        Vertex<T> startVertex = getVertex(start);
        Vertex<T> endVertex = getVertex(end);

        addEdge(startVertex, endVertex, 1);

    }

    public void addEdge(Vertex<T> start, Vertex<T> end) {
        addEdge(start, end, 1);
    }

    /**
     * adds an edge to the graph and the appropriate vertices
     * @param start the vertex where the edge starts
     * @param end the vertex where the edge ends
     * @param weight the weight of the edge
     */
    public void addEdge(Vertex<T> start, Vertex<T> end, int weight){
        if(start == null || end == null){
            return;
        }

        Edge<T> e = new Edge<>(start, end, weight);

        edges.add(e);

        start.addEdge(e);
        end.addEdge(e);

        if(!directed){
            e = new Edge<>(end, start, weight);

            start.addEdge(e);
            end.addEdge(e);
        }
    }

    /**
     * A function that checks whether the vertex B is connected to the vertex A with an edge
     * @param A starting point
     * @param B desired ending point
     * @return whether B is reachable from A via one edge
     */
    public final boolean isAdjacent(T B, T A) {
        Vertex<T> a = getVertex(A);
        Vertex<T> b = getVertex(B);

        return isAdjacent(b, a);
    }

    /**
     * A function that checks whether the vertex B is connected to the vertex A with an edge
     * @param A starting point
     * @param B desired ending point
     * @return whether B is reachable from A via one edge
     */
    public final boolean isAdjacent(Vertex<T> B, Vertex<T> A){
        for(Edge<T> edge: A.getOutgoingEdges()){
            if(edge.getEnd() == B){
                return true;
            }
        }

        return false;
    }

    /**
     * finds all neighbors of a particular vertex and returns them
     * @param vertex a vertex whose neighbors should be found
     * @return list of the vertex's neighbors
     */
    public final ArrayList<Vertex<T>> getNeighbors(T vertex) {
        Vertex<T> n = getVertex(vertex);

        return getNeighbors(n);
    }

    /**
     * finds all neighbors of a particular vertex and returns them
     * @param vertex a vertex whose neighbors should be found
     * @return list of the vertex's neighbors
     */
    public final ArrayList<Vertex<T>> getNeighbors(Vertex<T> vertex){
        ArrayList<Vertex<T>> neighbors = new ArrayList<>();

        for(Edge<T> edge: vertex.getOutgoingEdges()){
            neighbors.add(edge.getEnd());
        }

        return neighbors;
    }


    /*==========================================
     * "Algorithmic" Methods
     ===========================================*/

    public final boolean BFS(T vertex) {
        return BFS(vertex, null);
    }

    public final boolean BFS(Vertex<T> entry) {
        return BFS(entry, null);
    }

    /**
     * searches for a vertex with a matching item and uses it as entry vertex for a BFS
     * @param vertex desired vertex's item (value)
     * @param V item of the vertex that BFS should watch out for
     */
    public final boolean BFS(T vertex, T V) {
        Vertex<T> entry = getVertex(vertex);
        Vertex<T> v = getVertex(V);

        return BFS(entry, v);
    }

    /**
     * Performs a Breadth-First-Search on the graph, starting from the provided vertex
     * @param entry vertex where the BFS should start
     * @param entry vertex that BFS should watch out for
     */
    public final boolean BFS(Vertex<T> entry, Vertex<T> V){
        if(entry == null){
            throw new NullPointerException("No entry vertex found! Error in: graph.BFS");
        }

        Queue<Vertex<T>> queue = new LinkedList<>();
        queue.add(entry);

        int index = 0;
        for(Vertex<T> n: vertices){
            // assign a index number to every vertex
            n.order = index++;
        }

        boolean[] visited = new boolean[vertices.size()];

        while (!queue.isEmpty()){
            Vertex<T> vertex = queue.remove();
            // mark the current vertex as visited
            visited[vertex.order] = true;

            System.out.println("BFS visited " + vertex.getItem().toString());

            for(Edge<T> e: vertex.getOutgoingEdges()){
                // only visit the vertex if it hasn't been visited yet
                if(!visited[e.getEnd().order]){
                    // mark the added vertex as visited so other vertices don't add the same vertex
                    // to the queue (since that particular vertex may not have been visited yet)
                    visited[e.getEnd().order] = true;
                    queue.add(e.getEnd());
                    if(e.getEnd() == V){
                        System.out.println("BFS visited " + e.getEnd().getItem().toString());
                        return true;
                    }
                }
            }

        }

        return false;

    }


    public final boolean DFS(T vertex) {
        return DFS(vertex, null);
    }

    public final boolean DFS(Vertex<T> entry) {
        return DFS(entry, null);
    }

    /**
     * searches for a vertex with a matching item and uses it as entry vertex for a DFS
     * @param vertex desired vertex's item (value)
     * @param V item of the vertex that DFS should watch out for
     */
    public final boolean DFS(T vertex, T V) {
        Vertex<T> entry = getVertex(vertex);
        Vertex<T> v = getVertex(V);

        return DFS(entry, v);
    }

    /**
     * Performs a Depth-First-Search on the graph, starting from the provided vertex
     * @param entry vertex where the BFS should start
     */
    public final boolean DFS(Vertex<T> entry, Vertex<T> V){
        if(entry == null){
            throw new NullPointerException("No entry vertex found! Error in: graph.DFS");
        }

        Stack<Vertex<T>> stack = new Stack<>();
        stack.add(entry);

        int index = 0;
        for(Vertex<T> n: vertices){
            // assign a index number to every vertex
            n.order = index++;
        }

        boolean[] visited = new boolean[vertices.size()];

        while (!stack.isEmpty()){
            Vertex<T> vertex = stack.pop();
            // mark the current vertex as visited
            visited[vertex.order] = true;

            System.out.println("DFS visited " + vertex.getItem().toString());

            for(Edge<T> e: vertex.getOutgoingEdges()){
                // only visit the vertex if it hasn't been visited yet
                if(!visited[e.getEnd().order]){
                    // mark the added vertex as visited so other vertices don't add the same vertex
                    // to the queue (since that particular vertex may not have been visited yet)
                    visited[e.getEnd().order] = true;
                    stack.add(e.getEnd());
                    if(e.getEnd() == V){
                        System.out.println("DFS visited " + e.getEnd().getItem().toString());
                        return true;
                    }
                }
            }

        }

        return false;

    }

    /*==========================================
     * Getter Methods
     ===========================================*/

    /**
     * returns all the vertices of the graph
     */
    public final ArrayList<Vertex<T>> getVertices(){
        return vertices;
    }

    /**
     * returns all the edges of the graph
     */
    public final ArrayList<Edge<T>> getEdges() {
        return edges;
    }

    /**
     * given an item it returns the vertex that has that particular item as item
     */
    private final Vertex<T> getVertex(T item){
        for(Vertex<T> vertex : vertices){
            if(vertex.getItem() == item){
                return vertex;
            }
        }

        return null;
    }


}
