# Studies

## Datastructures

### Trees
---

#### Binary Tree

```Java
// create a binary tree
BinaryTree<Integer> tree = new BinaryTree<>();
```

##### Analysis:
| Best Case | Worst Case | Expected | Opeartion   | Description                          |
|-----------|------------|----------|-------------|--------------------------------------|
| *O(1)*      | *O(n)*       | *O(log n)* | Insert node | Inserts a node into the tree         |
| *O(1)*      | *O(n)*       | *O(log n)* | Delete node | Deletes a node and "repairs" the gap |
| *O(1)*      | *O(n)*       | *O(log n)* | Find a node | Searches for a node and outputs it   |

#### AVL-Tree

```Java
// create an AVL-Tree
AVLTree<Integer> tree = new AVLTree<>();
```

##### Analysis
| Best Case | Worst Case | Expected | Opeartion   | Description                                          |
|-----------|------------|----------|-------------|------------------------------------------------------|
| *O(1)*      | *O(log n)*   | *O(log n)* | Insert node | Inserts a node into the tree and rebalances the tree |
| *O(1)*      | *O(log n)*   | *O(log n)* | Find a node | Searches for a node and outputs it                   |

### Graphs
---

```Java
// create a graph
Graph<String> graph = new Graph<>();
```