package Util;

import Trees.HeapTree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by mugeebhassan on 20/01/17.
 */
public class Sorter<T> {

    /*==========================================
     * Heap sort
     ===========================================*/

    /**
     * sorts an array by utilizing a heap with the default comparator
     */
    public T[] heapSort(T[] unsorted){
        return heapSort(unsorted, null);
    }

    /**
     * sorts an array by utilizing a heap with a custom comparator
     */
    public T[] heapSort(T[] data, Comparator<T> comparator){
        HeapTree<T> heap = new HeapTree<>(comparator);

        for(T item:data){
            heap.add(item);
        }

        for(int i=0;i<data.length;i++) {
            data[i] = heap.popMin();
        }

        return data;
    }

    /**
     * sorts an ArrayList by utilizing a heap with the default comparator
     */
    public ArrayList<T> heapSort(ArrayList<T> data){
        return heapSort(data, null);
    }

    /**
     * sorts an ArrayList by utilizing a heap with a custom comparator
     */
    public ArrayList<T> heapSort(ArrayList<T> data, Comparator<T> comparator){
        HeapTree<T> heap = new HeapTree<>(comparator);

        for(T item:data){
            heap.add(item);
        }

        for(int i=0;i<data.size();i++) {
            data.set(i, heap.popMin());
        }

        return data;
    }

    /*==========================================
     * Merge sort
     ===========================================*/

    public ArrayList<T> mergeSort(ArrayList<T> data) {
        return mergeSort(data, null);
    }

    /**
     * sorts an ArrayList of items with merge sort
     * @param data the unsorted data
     * @param comparator optional comparator to change the way two items are compared
     * @return the initial data in sorted order
     */
    public ArrayList<T> mergeSort(ArrayList<T> data, Comparator<T> comparator){
        if(data.size() == 1){
            // base case
            return data;
        }

        int middle = data.size()/2;
        int size = data.size();

        // split the data array into two parts
        ArrayList<T> left = new ArrayList<T>(data.subList(0, middle));
        ArrayList<T> right = new ArrayList<T>(data.subList(middle, size));

        // sort both subarrays recursively
        left = mergeSort(left, comparator);
        right = mergeSort(right, comparator);

        // will contain the left and right array in sorted order
        ArrayList<T> all = new ArrayList<T>();

        int i = 0;
        int j = 0;

        // merge both sorted arrays into one sorted array
        for(int k=0; k<size; k++){
            if(i >= left.size()){
                // if we are done with the left array we can only add items from the right array
                all.add(right.get(j));
                j++;
            }else if(j >= right.size()){
                // if we are done with the right array we can only add items from the left array
                all.add(left.get(i));
                i++;
            }else{
                T l = left.get(i);
                T r = right.get(j);

                if(compare(l,r,comparator) <= 0){
                    all.add(l);
                    i++;
                }else{
                    all.add(r);
                    j++;
                }
            }
        }

        return all;
    }

    /*==========================================
     * Private Methods
     ===========================================*/

    private int compare(T A, T B, Comparator<T> comparator){
        if (comparator != null)
            return compareUsingComparator(A, B, comparator);
        else{
            return compareComparable(A, B);
        }
    }

    /**
     * if no custom comparator is specified, the items will be treated as comparable elements and their
     * standard comparable-comparison is used
     */
    @SuppressWarnings("unchecked")
    private int compareComparable(T A, T B){
        Comparable<? super T> that = (Comparable<? super T>) A;
        return that.compareTo(B);
    }

    /**
     * compares two elements by using a custom comparator
     */
    private int compareUsingComparator(T A, T B, Comparator<T> comparator){
        return comparator.compare(A, B);
    }
}
