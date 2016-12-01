# Graphs

## Usage

#### Create a graph
```Java
// initialize graph
Graph<String> graph = new Graph<>();
```

#### Add nodes
There are two ways to create new nodes in the graph.

```Java
// add some nodes to the graph
graph.addNode("Madrid");
graph.addNode("Zurich");
graph.addNode("Rome");
```

```Java
// create the nodes manually and then add them to the graph
Node<String> munich = new Node<>("Munich");
Node<String> geneva = new Node<>("Geneva");

graph.addNode(munich);
graph.addNode(geneva);
```

#### Add edges
To add an edge to the graph you can either pass the node's value to addEdge(), or you can directly pass the nodes themselves

```Java
graph.addEdge("Berlin", "Munich");
graph.addEdge(start, end);
```````