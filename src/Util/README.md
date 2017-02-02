# Utilites

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