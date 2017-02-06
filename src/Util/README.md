# Utilites

## Disjoint set

### Usage

#### Create a empty disjoint set

```Java
// create a empty disjoint set
DisjointSet<String> territories = new DisjointSet<>();
```
#### Add sets to the disjoint set

```Java
// create a new set with one item ("Rome")
territories.add("Rome");
// create another set with also just one item ("Athens")
territories.add("Athens");
// add another set
territories.add("Sparta");
```
#### Unite sets and find set representatives

```Java
// merge the set of Rome and the set of Athens into one set
territories.union("Rome", "Athens");

// get the set representative of Athens (it is Rome)
String representativeAthens = territories.findSet("Athens");

// merge the set of Rome and the set of Sparta into one set (all are in one set now)
territories.union("Rome", "Sparta");
```


## Sorter

### Usage

```Java
// create a sorter
Sorter<Integer> sorter = new Sorter<>();
ArrayList<Integer> unsorted = getUnsortedList();

// sort the list using heap sort without a custom comparator
ArrayList<Integer> ascending = sorter.heapSort(unsorted);
// sort the list using heap sort with a custom comparator
ArrayList<Integer> descending = sorter.heapSort(unsorted, (a,b)->b-a);
```