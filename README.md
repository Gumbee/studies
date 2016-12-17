Studies
===

A collection of my studies done at the ETH Zurich. The whole repository is a work in progress and will mainly serve to document my progress. For more information about a certain part/topic of this project (e.g graphs, trees), visit the appropriate directory. 

## Trees

### Binary Tree

```Java
// create a binary tree
BinaryTree<Integer> tree = new BinaryTree<>();
```

##### Analysis:
| Best Case | Worst Case | Expected | Operation   | Description                          |
|-----------|------------|----------|-------------|--------------------------------------|
| *O(1)*      | *O(n)*       | *O(log n)* | Insert vertex | Inserts a vertex into the tree         |
| *O(1)*      | *O(n)*       | *O(log n)* | Delete vertex | Deletes a vertex and "repairs" the gap |
| *O(1)*      | *O(n)*       | *O(log n)* | Find a vertex | Searches for a vertex and outputs it   |

### Binary Heap Tree

```Java
// create a Heap-Tree
HeapTree<Integer> heap = new HeapTree<>();

// create a Heap-Tree with a custom comparator
// this tree sorts the roads by shortest road distance
HeapTree<Road> minDistanceHeap = new HeapTree<>((a,b) -> a.distance-b.distance);
```

##### Analysis
| Best Case | Worst Case | Expected | Operation   | Description                                          |
|-----------|------------|----------|-------------|------------------------------------------------------|
| *O(1)*      | *O(log n)*   | *O(log n)* | Insert vertex | Inserts a vertex and restores heap property |
| *O(1)*      | *O(n)*   | *O(n)* | Find a vertex | Searches for a vertex and outputs it                   |

### AVL-Tree

```Java
// create an AVL-Tree
AVLTree<Integer> tree = new AVLTree<>();
```

##### Analysis
| Best Case | Worst Case | Expected | Operation   | Description                                          |
|-----------|------------|----------|-------------|------------------------------------------------------|
| *O(1)*      | *O(log n)*   | *O(log n)* | Insert vertex | Inserts a vertex into the tree and rebalances the tree |
| *O(1)*      | *O(log n)*   | *O(log n)* | Find a vertex | Searches for a vertex and outputs it                   |

## Graphs

```Java
// create a graph
Graph<String> graph = new Graph<>();
```

| Best Case | Worst Case   | Expected     | Operation    | Description                                    |
|-----------|--------------|--------------|--------------|------------------------------------------------|
| O(1)      | O(1)         | O(1)         | Add vertex   | Adds a new vertex to the graph                 |
| O(1)      | O(1)         | O(1)         | Add edge     | Adds a new edge to the graph                   |
| O(1)      | O(\|E\|)     | O(\|E\|)     | isAdjacent   | Checks whether vertex B is reachable from vertex A|
| O(1)      | O(\|E\|)     | O(\|E\|)     | getNeighbors | Finds all neighbors of a certain vertex        |
| O(\|V\|+\|E\|) | O(\|V\|+\|E\|) | O(\|V\|+\|E\|)   | topoSort | Sorts the graph topologically    |
| O(1)      | O(\|V\|+\|E\|) | O(\|V\|+\|E\|) | DFS | Performs a depth-first-search from vertex A |
| O(1)      | O(\|V\|+\|E\|) | O(\|V\|+\|E\|) | BFS | Performs a breadth-first-search from vertex A |
| O(\|V\|*\|V\|) | O(\|V\|*\|V\|) | O(\|V\|*\|V\|) | Dijkstra | Searches for the shortest path starting from A |
| O(\|E\|*\|V\|) | O(\|E\|*\|V\|) | O(\|E\|*\|V\|) | Bellman-Ford | Searches for the shortest path starting from A |
