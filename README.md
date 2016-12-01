# Studies

## Graphs

### Create a graph
```Java
// initialize graph
Graph<String> graph = new Graph<>();
```

### Add nodes
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

### Add edges
To add an edge to the graph you can either pass the node's value to addEdge(), or you can directly pass the nodes themselves

```Java
graph.addEdge("Berlin", "Munich");
graph.addEdge(start, end);
```

## Trees

### Binary Tree

```Java
// create a binary tree
BinaryTree<Integer> tree = new BinaryTree<>();

// add elements to the tree
tree.add(15);
tree.add(31);

// print out the tree (formatted ASCII ART)
tree.printTree();
```

### AVL-Tree

```Java
// create an AVL-Tree
AVLTree<Integer> tree = new AVLTree<>();

// add elements to the tree
tree.add(5);
tree.add(61);
tree.add(32);

// print out the tree (formatted ASCII ART)
tree.printTree();
```