package Trees;

import Graphs.Vertex;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by mugeebhassan on 19/12/16.
 */
public class QuickAccessHeapTree<T> extends HeapTree<T> {

    private HashMap<T, Integer> indexTracker;

    /**
     * creates a heap-tree with a default comparator (only works for comparable objects).
     */
    public QuickAccessHeapTree(){
        this(null);
    }

    /**
     * creates a heap-tree with a custom comparator
     * @param comparator a custom comparator
     */
    public QuickAccessHeapTree(Comparator<T> comparator){
        super(comparator);
        this.indexTracker = new HashMap<>();
    }

    /**
     * restores heap condition after updating a certain key
     * @param item the key whose value was updated and needs realignment in the tree
     */
    public void updateKey(T item){
        int index = indexTracker.get(item);
        restoreHeapAtIndex(index);
    }

    @Override
    public void add(T item) {
        indexTracker.put(item, heap.size());
        super.add(item);
    }

    /**
     * swaps the nodes at index i and j and updates their index reference in the hash-map
     */
    @Override
    protected void swap(int i, int j) {
        T a = heap.get(i).getItem();
        T b = heap.get(j).getItem();
        super.swap(i, j);
        indexTracker.replace(a, j);
        indexTracker.replace(b, i);
    }
}
