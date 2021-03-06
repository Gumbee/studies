package Graphs;

import Trees.HeapTree;
import Trees.QuickAccessHeapTree;
import Util.DisjointSet;
import Util.Sorter;

import java.util.*;

/**
 * Created by mugeebhassan on 01/12/16.
 */
public class Graph<T> {

    private ArrayList<Vertex<T>> vertices;
    // hash map for quick look up
    private HashMap<T, Vertex<T>> verticesMap;
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
        verticesMap = new HashMap<T, Vertex<T>>();
        edges = new ArrayList<>();
        this.directed = directed;
    }


    /*==========================================
     * Graph Operations
     ===========================================*/

    public void addVertex(T item){
        Vertex vertex = new Vertex(item);
        vertices.add(vertex);
        // add it to the hash map as well so we can search get the vertex corresponding to
        // that item more quickly
        verticesMap.put(item, vertex);
    }

    public void addVertex(Vertex<T> vertex){
        vertices.add(vertex);

        // add it to the hash map as well so we can search get the vertex corresponding to
        // that item more quickly
        verticesMap.put(vertex.getItem(), vertex);
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

    /**
     * adds a weighted edge to the graph and the appropriate vertices
     * @param start item representing the start vertex (vertex is created with this item)
     * @param end item representing the end vertex (vertex is created with this item)
     * @param weight int representing the edge's weight
     */
    public void addEdge(T start, T end, double weight) {
        Vertex<T> startVertex = getVertex(start);
        Vertex<T> endVertex = getVertex(end);

        addEdge(startVertex, endVertex, weight);
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
    public void addEdge(Vertex<T> start, Vertex<T> end, double weight){
        if(start == null || end == null){
            return;
        }

        Edge<T> e = new Edge<>(start, end, weight);

        edges.add(e);

        start.addEdge(e);
        end.addEdge(e);

        if(!directed){
            e = new Edge<>(end, start, weight);

            edges.add(e);

            start.addEdge(e);
            end.addEdge(e);
        }
    }

    //TODO: add "remove edge"

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

        // assign a number to each vertex
        assignIndexToVertices();

        Queue<Vertex<T>> queue = new LinkedList<>();
        queue.add(entry);

        boolean[] visited = new boolean[vertices.size()];

        while (!queue.isEmpty()){
            Vertex<T> vertex = queue.remove();
            // mark the current vertex as visited
            visited[vertex.index] = true;

            System.out.println("BFS visited " + vertex.getItem().toString());

            for(Edge<T> e: vertex.getOutgoingEdges()){
                int index = e.getEnd().index;
                // only visit the vertex if it hasn't been visited yet
                if(!visited[index]){
                    // mark the added vertex as visited so other vertices don't add the same vertex
                    // to the queue (since that particular vertex may not have been visited yet)
                    visited[index] = true;
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

        // assign a number to each vertex
        assignIndexToVertices();

        Stack<Vertex<T>> stack = new Stack<>();
        stack.add(entry);

        boolean[] visited = new boolean[vertices.size()];

        while (!stack.isEmpty()){
            Vertex<T> vertex = stack.pop();
            // mark the current vertex as visited
            visited[vertex.index] = true;

            System.out.println("DFS visited " + vertex.getItem().toString());

            for(Edge<T> e: vertex.getOutgoingEdges()){
                int index = e.getEnd().index;
                // only visit the vertex if it hasn't been visited yet
                if(!visited[index]){
                    // mark the added vertex as visited so other vertices don't add the same vertex
                    // to the queue (since that particular vertex may not have been visited yet)
                    visited[index] = true;
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

    /**
     * sorts the graph topologically and returns the result. Reminder: A topological sorting of a graph means sorting
     * the graph in such a way that no vertex v is placed in front of another vertex w if there is a edge w -> v (useful
     * for simulating dependencies for example)
     * @return the ArrayList consisting of the topologically sorted vertices
     */
    public final ArrayList<Vertex<T>> topologicalSort(){
        // assign a number to each vertex
        assignIndexToVertices();

        // degree of incoming edges for each vertex. Everytime we pick the next vertex with degree = 0, we decrease
        // the degree of every neighbour by 1 and do the same for every vertex that reaches 0 after decrementing
        int[] incoming = new int[vertices.size()];

        // vertices that reached a degree of 0
        Stack<Vertex<T>> toProcess = new Stack<>();
        // output array
        ArrayList<Vertex<T>> sortedList = new ArrayList<>();


        for(Vertex<T> v: vertices){
            incoming[v.index] = v.degIn();

            if(v.degIn() == 0){
                // mark every vertex with incoming degree = 0 as ready to be processed (since a incoming degree
                // of 0 means that that particular vertex has no dependencies)
                toProcess.push(v);
            }
        }


        while (!toProcess.empty()){
            Vertex<T> v = toProcess.pop();

            sortedList.add(v);

            for(Edge<T> e: v.getOutgoingEdges()){
                int index = e.getEnd().index;
                // decrement the incoming degree of every neighbour of v (to simulate removing v from the graph)
                incoming[index]--;

                // if decrementing the neighbour results in it's incoming degree being set to 0, then that
                // neighbour is ready to be processed (simulate removing that neighbour from the graph)
                if(incoming[index] == 0){
                    toProcess.push(e.getEnd());
                }
            }
        }

        return sortedList;
    }

    /**
     * Returns the graph's articulation points as ArrayList
     */
    public ArrayList<Vertex<T>> getArticulationPoints(){

        // placeholderInitialized represents (in this part of the code) whether the vertex was visited yet or no
        for(Vertex<T> v:vertices){
            v.placeholderInitialized = false;
        }

        ArrayList<Vertex<T>> articulationPoints = new ArrayList<>();

        Vertex<T> root;
        for(Vertex v: vertices) {
            if (!v.placeholderInitialized){
                root = v;
                // start the dfs from the root and let it find all articulation points
                articulationDFS(root, 0, articulationPoints, root);
            }
        }

        // sort the articulation points by index assigned to them by the dfs
        articulationPoints.sort((a,b) -> a.index-b.index);

        return articulationPoints;
    }

    /**
     * finds a minimum spanning tree and returns it. The MST is determined using Kruskal's algorithm.
     * @return ArrayList containing all the Edges that are part of the minimum spanning tree
     */
    public ArrayList<Edge<T>> kruskal(){
        // create a new disjoint set with each vertex as its own partition
        DisjointSet<Vertex<T>> disjointVertices = new DisjointSet<>(vertices);

        //sort the edges in ascending order by their weight
        edges.sort((a, b) -> a.getWeight() > b.getWeight() ? 1 : b.getWeight() > a.getWeight() ? -1 : 0);

        ArrayList<Edge<T>> mst = new ArrayList<>();

        for(Edge<T> e: edges){
            //get the representative of the set to which the starting node belongs to
            Vertex<T> setA = disjointVertices.findSet(e.getStart());
            //get the representative of the set to which the ending node belongs to
            Vertex<T> setB = disjointVertices.findSet(e.getEnd());

            //if this edge connects two still unconnected vertices then we add it to the mst
            if(!setA.equals(setB)){
                disjointVertices.union(e.getStart(), e.getEnd());
                mst.add(e);
            }

            if(disjointVertices.numPartitions() == 1){
                System.out.println("Woah");
                break;
            }
        }

        return mst;
    }

    public ArrayList<Edge<T>> dijsktra(T entry, T goal) {
        Vertex<T> start = getVertex(entry);
        Vertex<T> end = getVertex(goal);

        return dijsktra(start, end);
    }

    /**
     * searches for the shortest path using Dijkstra's algorithm. The shortest paths are relative to the entry point.
     * Definition: vertex.placeholder stores the shortest distance to this vertex
     * @param entry starting point from which all the shortest paths are calculated
     * @param goal desired end
     * @return an ArrayList of edges representing the shortest path from entry to goal
     */
    public ArrayList<Edge<T>> dijsktra(Vertex<T> entry, Vertex<T> goal){
        if(entry == null || goal == null){
            throw new NullPointerException("entry or goal vertex was not found! Error in: graph.dijkstra");
        }

        // assign a number to each vertex
        assignIndexToVertices();

        for(Vertex<T> v:vertices){
            // a false value here is equal to setting the vertexDistances-value to infinity
            v.placeholderInitialized = false;
        }

        boolean[] visited = new boolean[vertices.size()];

        QuickAccessHeapTree<Vertex<T>> toProcess = new QuickAccessHeapTree<>((a, b) -> a.placeholder-b.placeholder<0?-1:a.placeholder-b.placeholder>0?1:0);
        toProcess.add(entry);
        entry.placeholderInitialized = true;

        while (!toProcess.isEmpty()){
            // pops the smallest element
            Vertex<T> vertex = toProcess.popMin();

            // mark the current vertex as visited
            visited[vertex.index] = true;

            if(vertex.equals(goal)){
                return getShortestPath(entry, goal);
            }

            for(Edge<T> e: vertex.getOutgoingEdges()){
                Vertex<T> w = e.getEnd();
                if(!visited[w.index]){
                    toProcess.add(w);
                    visited[w.index] = true;
                }
                if(w.placeholder > vertex.placeholder + e.getWeight() || !w.placeholderInitialized){
                    w.placeholder = vertex.placeholder + e.getWeight();
                    toProcess.updateKey(w);
                    // after the first time that we assign a shortest distance value to a vertex, mark it as initialized
                    // so we don't treat it as if it had a distance value of infinity
                    w.placeholderInitialized = true;
                }
            }

        }

        System.out.println("We reached the goal (" + goal.getItem().toString() + ") with a length of " + goal.placeholder);

        return null;
    }

    public ArrayList<Edge<T>> bellmanFord(T entry, T goal) {
        Vertex<T> start = getVertex(entry);
        Vertex<T> end = getVertex(goal);

        return bellmanFord(start, end);
    }

    /**
     * searches for the shortest path using the Bellman-Ford algorithm. The shortest paths are relative to the entry
     * point. The difference between the Bellman-Ford algorithm and Dijkstra's algorithm is that the
     * Bellman-Ford algorithm can handle negative weight values. However the disadvantage is that it is slower
     * than Dijkstra's algorithm.
     * @param entry starting point from which all the shortest paths are calculated
     * @param goal desired end
     * @return an ArrayList of edges representing the shortest path from entry to goal
     */
    public ArrayList<Edge<T>> bellmanFord(Vertex<T> entry, Vertex<T> goal) {
        if(entry == null || goal == null){
            throw new NullPointerException("entry or goal vertex was not found! Error in: graph.bellmanFord");
        }

        for(Vertex<T> v:vertices){
            v.placeholderInitialized = false;
        }

        entry.placeholder = 0;
        entry.placeholderInitialized = true;

        for(int i=0;i<vertices.size()-1;i++){
            for(Edge<T> e: edges){
                Vertex<T> v = e.getStart();
                Vertex<T> w = e.getEnd();
                // relaxation process
                if((v.placeholderInitialized && !w.placeholderInitialized) || w.placeholder > v.placeholder + e.getWeight()){
                    w.placeholder = v.placeholder + e.getWeight();
                    // mark as initialized so we don't treat w.placeholder as a value equal to infinity
                    w.placeholderInitialized = true;
                }
            }
        }

        for(Edge<T> e:edges){
            // since we have relaxed n-1 times, we should be able to detect a negative cycle by relaxing all
            // edges once more and checking if any of the values change
            Vertex<T> v = e.getStart();
            Vertex<T> w = e.getEnd();
            if(w.placeholder > v.placeholder + e.getWeight()){
                throw new RuntimeException("Negative cycle found! Bellman-Ford algorithm can't deal with negative cycles ");
            }
        }

        System.out.println("We reached the goal (" + goal.getItem().toString() + ") with a length of " + goal.placeholder);

        return getShortestPath(entry, goal);
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
    private Vertex<T> getVertex(T item){
        Vertex<T> vertex = verticesMap.get(item);

        return (vertex!=null)?vertex:null;
    }

    /*==========================================
     * Private Methods
     ===========================================*/

    /**
     * Assigns a number from 1...n to each vertex so that we can store information about nodes in an array A of length n,
     * where entry A[i] = information about vertex with assigned number i. That way we can for example store in an
     * array A of length n if a particular vertex was already visited via DFS/BFS.
     */
    private final void assignIndexToVertices(){
        int index = 0;
        for(Vertex<T> n: vertices){
            // assign a index number to every vertex
            n.index = index++;
        }
    }

    /**
     * gets the shortest path from a start vertex to an end vertex. The path can only be found if the graph
     * was already processed with a shortest path algorithm (e.g dijkstra, bellman-ford)
     * @param start the vertex which was given as entry point for a shortest path algorithm
     * @param end the vertex which was given as end point for a shortest path algorithm
     * @return an ArrayList of Edges which form the shortest path from the entry point to the end point
     */
    private ArrayList<Edge<T>> getShortestPath(Vertex<T> start, Vertex<T> end){
        Vertex<T> current = end;
        ArrayList<Edge<T>> path = new ArrayList<>();

        while(!current.equals(start)){
            // get all incoming edges
            for(Edge<T> edge : current.getIncomingEdges()){
                // check which edge leads to the already calculated minimum for our current edge
                if(edge.getStart().placeholder + edge.getWeight() == current.placeholder){
                    // add the edge to our shortest path
                    path.add(edge);
                    // move to the start of the edge and repeat the process
                    current = edge.getStart();
                    break;
                }
            }

        }

        // reverse the path because we backtracked the path from the end vertex
        Collections.reverse(path);

        return path;
    }

    /**
     * Performs a modified depth-fist-search. Modification: Each vertex is assigned a index, representing its order
     * in the dfs. Furthermore we keep track of all articulation points and we pass the root, so we know when to
     * apply the criteria that only applies to the root of the dfs.
     *
     * @param vertex the vertex that is currently processed by the dfs
     * @param index the index that will be assigned to the vertex
     * @param articulationPoints the list of all articulation points found already
     * @param root the vertex from which the dfs was started
     * @return the low value of this vertex
     */
    private int articulationDFS(Vertex<T> vertex, int index, ArrayList<Vertex<T>> articulationPoints, Vertex<T> root){
        vertex.index = index;
        vertex.low = vertex.index;
        // placeholderInitialized represents whether the vertex was visited or not
        vertex.placeholderInitialized = true;

        int counter = 0;
        for(Edge<T> e: vertex.getOutgoingEdges()){
            if(!e.getEnd().placeholderInitialized) {
                // keep track of how many different dfs we had to initiate from this vertex (needed to determine
                // if the root is an articulation point)
                counter++;

                // perform the dfs on the child and save the child's returned low value
                int low = articulationDFS(e.getEnd(), index+1, articulationPoints, root);
                // update this vertex' low value if necessary
                vertex.low = Math.min(vertex.low, low);

                // if the low value of any child of this vertex is bigger or equal to this vertex' index
                // then this vertex is a articulation point. We exclude the root because the root has a
                // different criteria
                if(low >= vertex.index && !vertex.isArticulationPoint && !vertex.equals(root)){
                    articulationPoints.add(vertex);
                    vertex.isArticulationPoint = true;
                }

                // the root has a different criteria than the other vertices
                if(counter > 1 && vertex.equals(root)){
                    articulationPoints.add(root);
                }
            }else{
                // keep record of the lowest index we can reach from this vertex by only using one  "non-dfs"-Edge
                vertex.low = Math.min(vertex.low, e.getEnd().index);
            }
        }

        return vertex.low;
    }


}
