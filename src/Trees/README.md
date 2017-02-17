# Trees

## Binary Tree

### Usage

```Java
// create a binary tree
BinaryTree<Integer> tree = new BinaryTree<>();

// add elements to the tree
tree.add(15);
tree.add(31);

// print out the tree (formatted ASCII ART)
tree.printTree();
```

#### Analysis:
| Best Case | Worst Case | Operation   | Description                          |
|-----------|------------|-------------|--------------------------------------|
| *O(1)*      | *O(n)* | Insert vertex | Inserts a vertex into the tree         |
| *O(1)*      | *O(n)* | Delete vertex | Deletes a vertex and "repairs" the gap |
| *O(1)*      | *O(n)* | Find a vertex | Searches for a vertex and outputs it   |

## Binary Heap Tree

### Usage

There are two ways to create a heap tree. Either you create a heap tree with comparable elements and without a custom comparator or you create a heap tree with a comparator and any type of elements.

```Java
// create a heap tree with comparable objects
HeapTree<Integer> heap = new HeapTree<>();

// add some values to the heap
heap.add(12);
heap.add(1);
heap.add(7);
heap.add(3);
heap.add(4);
heap.add(2);
heap.add(6);
```

#### Analysis
| Best Case | Worst Case | Operation   | Description                                          |
|-----------|------------|-------------|------------------------------------------------------|
| *O(1)*      | *O(log n)* | Insert vertex | Inserts a vertex and restores heap property |
| *O(1)*      | *O(n)*   | Find a vertex | Searches for a vertex and outputs it                   |


Or with a comparator:

```Java
// create a heap tree with a comparator (player with the higher score is nearer to the root)
HeapTree<Player> heap = new HeapTree<>((a,b) -> b.getItem().highscore-a.getItem().highscore);

// add some players to the heap
heap.add(barry);
heap.add(tim);
heap.add(thomas);
```

## AVL-Tree

### Usage

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

##### Analysis
| Best Case | Worst Case | Operation   | Description                                          |
|-----------|------------|-------------|------------------------------------------------------|
| *O(1)*      | *O(log n)* | Insert vertex | Inserts a vertex into the tree and rebalances the tree |
| *O(1)*      | *O(log n)* | Find a vertex | Searches for a vertex and outputs it                   |

