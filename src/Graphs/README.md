# Graphs

## Usage

#### Create a graph
```Java
// initialize a directed graph
Graph<String> graph = new Graph<>();
// or
Graph<String> graph = new Graph<>(true);

// initialize an undirected graph
Graph<String> graph = new Graph<>(false);
```

#### Add vertices
There are two ways to create new vertices:

```Java
// add some vertices to the graph
graph.addVertex("Madrid");
graph.addVertex("Zurich");
graph.addVertex("Rome");
```

```Java
// create the vertices manually and then add them to the graph
Vertex<String> munich = new Vertex<>("Munich");
Vertex<String> geneva = new Vertex<>("Geneva");

graph.addVertex(munich);
graph.addVertex(geneva);
```

#### Add edges
To add an edge to the graph you can either pass the vertex's value to addEdge(), or you can directly pass the vertices themselves

```Java
graph.addEdge("Berlin", "Munich");
graph.addEdge(start, end);
```

#### Check if a vertex is connected to another vertex with an edge

```Java
// returns true if Munich can be reached from Berlin via one edge
boolean isReachable = graph.isAdjacent("Munich", "Berlin");
```

#### Get all neighbors of a vertex

```Java
// get neighbors
Arraylist<Vertex<String>> neighbors = graph.getNeighbors("Zurich");

for(Vertex<String> neighbor: neighbors){
    //...do stuff with the neighbors
}
```

Keep in mind that a "neighbor" means two different things in an undirected and a directed graph.
In an undirected graph it means a vertex that is connected to "v" via a edge while in a directed graph a neighbor "n" is a vertex that has a edge that starts at "v" and ends at "n".

#### Perform a BFS/DFS

```Java
// performs a breadth-first-search with startVertex as starting vertex and desiredVertex
// as vertex that is to be reached. BFS returns true if desiredVertex was reached and false otherwise.
boolean bfsFoundVertex = graph.BFS(startVertex, desiredVertex);

// performs a depth-first-search with startVertex as starting vertex and desiredVertex
// as vertex that is to be reached. DFS returns true if desiredVertex was reached and false otherwise.
boolean dfsFoundVertex = graph.DFS(startVertex, desiredVertex);
```

#### Sort topologically

```Java
// sorts the graph topologically and returns the result
ArrayList<Vertex<Task>> sorted = graph.topologicalSort();

for(Vertex<Task> task: sorted){
    // execute the tasks in order
    task.getItem().execute();
}
```

#### Find shortest paths

For shortest paths in a graph with only non-negative numbers, use the Dijkstra-method:

```Java
ArrayList<Vertex<Station>> path = graph.dijkstra(myStation, myHome);

for(Vertex<Station> station: path){
    // do something with the shortest path...
}
```

If there are negative values, use Bellman-Ford's algorithm:

```Java
ArrayList<Vertex<Reaction>> minEntropy = graph.bellmanFord(myState, goalState);

for(Vertex<Reaction> reaction: minEntropy){
    // do something with the shortest path...
}
```

# Analysis

This analysis is based on the provided implementation.

| Best Case | Worst Case   | Operation    | Description                                    |
|-----------|--------------|--------------|------------------------------------------------|
| O(1)      | O(1)         | Add vertex   | Adds a new vertex to the graph                 |
| O(1)      | O(1)         | Add edge     | Adds a new edge to the graph                   |
| O(1)      | O(\|E\|)     | isAdjacent   | Checks whether vertex B is reachable from vertex A|
| O(1)      | O(\|E\|)     | getNeighbors | Finds all neighbors of a certain vertex        |
| O(\|V\|+\|E\|) | O(\|V\|+\|E\|) | topoSort | Sorts the graph topologically    |
| O(1)      | O(\|V\|+\|E\|) | DFS | Performs a depth-first-search from vertex A |
| O(1)      | O(\|V\|+\|E\|) | BFS | Performs a breadth-first-search from vertex A |
| O(\|E\|*log\|V\|) | O(\|E\|*log\|V\|) | MST | Returns a minimum spanning tree |
| O(1) | O((\|E\|+\|V\|)*log\|V\|) | Dijkstra | Searches for the shortest path starting from A |
| O(\|E\|*\|V\|) | O(\|E\|*\|V\|) | Bellman-Ford | Searches for the shortest path starting from A |
