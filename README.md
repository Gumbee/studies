Studies
===

A collection of my studies done at the ETH Zurich. The whole repository is a work in progress and will mainly serve to document my progress. For more information about a certain part/topic of this project (e.g graphs, trees), visit the appropriate directory. 

## Getting started

#### Installation

1. Fork the project
2. Use your favourite IDE to extend the project to your liking
3. Use the documentation (each topic has its README.md) to use existing implementations

#### Documentation

- [Trees](https://github.com/Gumbee/studies/blob/master/src/Trees/README.md)
- [Graphs](https://github.com/Gumbee/studies/blob/master/src/Graphs/README.md)
- [Algebra](https://github.com/Gumbee/studies/blob/master/src/Algebra/README.md)
- [Util](https://github.com/Gumbee/studies/blob/master/src/Util/README.md)

## Quick guide

### Trees

#### Binary Tree

```Java
// create a binary tree
BinaryTree<Integer> tree = new BinaryTree<>();
```

#### Binary Heap Tree

```Java
// create a Heap-Tree
HeapTree<Integer> heap = new HeapTree<>();

// create a Heap-Tree with a custom comparator
// this tree sorts the roads by shortest road distance
HeapTree<Road> minDistanceHeap = new HeapTree<>((a,b) -> a.distance-b.distance);
```

#### AVL-Tree

```Java
// create an AVL-Tree
AVLTree<Integer> tree = new AVLTree<>();
```

### Graphs

```Java
// create a graph
Graph<String> graph = new Graph<>();
```

## License
MIT License

Copyright (c) 2017 Mugeeb Al-Rahman Hassan