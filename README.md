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
| Best Case | Worst Case | Expected | Opeartion   | Description                          |
|-----------|------------|----------|-------------|--------------------------------------|
| *O(1)*      | *O(n)*       | *O(log n)* | Insert vertex | Inserts a vertex into the tree         |
| *O(1)*      | *O(n)*       | *O(log n)* | Delete vertex | Deletes a vertex and "repairs" the gap |
| *O(1)*      | *O(n)*       | *O(log n)* | Find a vertex | Searches for a vertex and outputs it   |

### AVL-Tree

```Java
// create an AVL-Tree
AVLTree<Integer> tree = new AVLTree<>();
```

##### Analysis
| Best Case | Worst Case | Expected | Opeartion   | Description                                          |
|-----------|------------|----------|-------------|------------------------------------------------------|
| *O(1)*      | *O(log n)*   | *O(log n)* | Insert vertex | Inserts a vertex into the tree and rebalances the tree |
| *O(1)*      | *O(log n)*   | *O(log n)* | Find a vertex | Searches for a vertex and outputs it                   |

## Graphs

```Java
// create a graph
Graph<String> graph = new Graph<>();
```