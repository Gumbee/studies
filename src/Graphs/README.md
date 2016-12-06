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