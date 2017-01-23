package Util;

import Trees.HeapTree;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by mugeebhassan on 20/01/17.
 */
public class Sorter<T> {

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
    public ArrayList<T> heapSort(ArrayList<T> unsorted){
        return heapSort(unsorted, null);
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

}
