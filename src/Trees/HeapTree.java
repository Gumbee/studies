package Trees;

import Graphs.Vertex;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by mugeebhassan on 16/12/16.
 */
public class HeapTree<T> {

    // we store the tree as ArrayList
    ArrayList<HeapNode<T>> heap;
    // comparator that is used to compare the HeapNodes
    Comparator<HeapNode<T>> comparator;

    /**
     * creates a heap-tree with a default comparator (only works for comparable objects).
     */
    @SuppressWarnings("unchecked")
    public HeapTree(){
        this.heap = new ArrayList<>();
        this.comparator = (a, b) -> {
            if(Comparable.class.isAssignableFrom(a.getItem().getClass()) && Comparable.class.isAssignableFrom(b.getItem().getClass())) {
                return ((Comparable<T>) (a.getItem())).compareTo(b.getItem());
            }else {
                throw new RuntimeException("Can't compare objects if they are not comparable or you don't provide a comparator to compare them. Error occurred in: HeapTree.Constructor");
            }
        };
    }

    /**
     * creates a heap-tree with a custom comparator
     * @param comparator
     */
    public HeapTree(Comparator<HeapNode<T>> comparator){
        this.heap = new ArrayList<>();
        this.comparator = comparator;
    }

    /**
     * creates a new Node with an item as its content and then inserts it into the tree
     */
    public void add(T item){
        heap.add(new HeapNode<>(item, comparator));
        bubbleUp(heap.size()-1);
    }

    /*==========================================
     * Getter Methods
     ===========================================*/

    /**
     * returns the heap-array
     */
    public ArrayList<HeapNode<T>> getHeap() {
        return heap;
    }

    /*==========================================
     * Private Methods
     ===========================================*/

    /**
     * takes the node at the specified index and lets it rise to the top for as long as the node is smaller than
     * the parent (or greater, depends on your specified comparator)
     * @param index index of the node whose position must be corrected
     */
    private void bubbleUp(int index){
        int childIndex = index;

        // while there are still parents
        while ((childIndex-1)/2 >= 0 && childIndex-1 >= 0){
            // get parent's index
            int parentIndex = (childIndex-1)/2;

            HeapNode<T> parent = heap.get(parentIndex);
            HeapNode<T> child = heap.get(childIndex);

            // if the parent is bigger than the child swap them
            if(parent.compareTo(child) > 0){
                swap(parentIndex, childIndex);
                // update our new position
                childIndex = parentIndex;
            }else {
                return;
            }
        }
    }

    /**
     * swaps the nodes at index i and j
     */
    private final void swap(int i, int j){
        HeapNode<T> tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }


}
