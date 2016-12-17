package Trees;

import Graphs.Vertex;

import java.util.*;

/**
 * Created by mugeebhassan on 16/12/16.
 */
public class HeapTree<T> {

    // we store the tree as ArrayList
    ArrayList<Node<T>> heap;
    // comparator that is used to compare the Nodes
    Comparator<T> comparator;

    /**
     * creates a heap-tree with a default comparator (only works for comparable objects).
     */
    public HeapTree(){
        this(null);
    }

    /**
     * creates a heap-tree with a custom comparator
     * @param comparator a custom comparator
     */
    public HeapTree(Comparator<T> comparator){
        this.heap = new ArrayList<>();
        this.comparator = comparator;
    }

    /**
     * creates a new Node with an item as its content and then inserts it into the tree
     */
    public void add(T item){
        heap.add(new Node<>(item, comparator));
        bubbleUp(heap.size()-1);
    }

    /*==========================================
     * Getter Methods
     ===========================================*/

    /**
     * returns the heap-array
     */
    public ArrayList<Node<T>> getHeap() {
        return heap;
    }

    /*==========================================
     * Util Methods
    ===========================================*/

    /**
     * prints out the tree in a formatted manner
     */
    public final void printTree() {
        BinaryTree.printBinaryTree(getLevels());
    }

    /**
     * Each level of the tree is stored in a LinkedList and a LinkedList containing all LinkedLists is then returned.
     * @return LinkedList containing a LinkedList for each level of the tree
     */
    private final Queue<Queue<Node<?>>> getLevels(){
        Queue<Queue<Node<?>>> levels = new LinkedList<>();

        int pow = 0;
        int offset = 0;

        while (Math.pow(2, pow) <= heap.size()){
            LinkedList<Node<?>> nextLevel = new LinkedList<>();

            // loop through the elements of the next level and add them to the array
            for (int i = 0; i < Math.pow(2, pow); i++){
                if (offset + i < heap.size()) {
                    nextLevel.add(heap.get(offset + i));
                }else {
                    // if there are no more elements, fill the rest of the tree with null
                    nextLevel.add(null);
                }
            }

            // add an offset so we don't add the same elements multiple times
            offset += Math.pow(2, pow);
            pow++;

            levels.add(nextLevel);
        }

        return levels;
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
        while (childIndex > 0){
            // get parent's index
            int parentIndex = (childIndex-1) >>> 1;

            Node<T> parent = heap.get(parentIndex);
            Node<T> child = heap.get(childIndex);

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
     * takes the node at the specified index and lets it rise to the top for as long as the node is smaller than
     * the parent (or greater, depends on your specified comparator)
     * @param index index of the node whose position must be corrected
     */
    private void bubbleDown(int index){
        int parentIndex = index;

        // while there are still children
        while (((parentIndex+1) << 1)-1 < heap.size()){
            // get parent's index
            int firstChildIndex = ((parentIndex+1) << 1)-1;
            int secondChildIndex = firstChildIndex+1;

            Node<T> parent = heap.get(parentIndex);
            Node<T> leftChild = heap.get(firstChildIndex);
            // only get second child if it exists
            Node<T> rightChild = secondChildIndex<heap.size()?heap.get(secondChildIndex):null;

            // check which one of the children is smaller
            // if the right child is smaller than the left one, we only have to
            // compare the parent to the right child and vice versa
            if(rightChild != null && rightChild.compareTo(leftChild) < 0){
                // check if the right child is smaller than the parent and swap them if so
                if(rightChild.compareTo(parent) < 0){
                    swap(parentIndex, secondChildIndex);
                    parentIndex = secondChildIndex;
                }else {
                    return;
                }
            }else {
                // check if the left child is smaller than the parent and swap if so
                if(leftChild.compareTo(parent) < 0){
                    swap(parentIndex, firstChildIndex);
                    parentIndex = firstChildIndex;
                }else {
                    return;
                }
            }
        }

    }

    public void replaceItem(int index, T value){
        heap.set(index, new Node<>(value, comparator));
        bubbleDown(index);
        bubbleUp(index);
    }

    /**
     * swaps the nodes at index i and j
     */
    private void swap(int i, int j){
        Node<T> tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }

    /**
     * checks if the tree satisfies heap condition. Mainly for debugging purposes
     * @return
     */
    public int checkHeap(){
        for(int i=0;i<=heap.size();i++){
            if((i+1)*2 < heap.size()){
                if(heap.get((i+1)*2-1).compareTo(heap.get(i)) < 0 || heap.get((i+1)*2).compareTo(heap.get(i)) < 0){
                    System.out.println("Error:");
                    System.out.println(heap.get(i));
                    System.out.println(heap.get((i + 1) * 2 - 1));
                    System.out.println(heap.get((i + 1) * 2));
                    return -1;
                }
            }
        }
        return 1;
    }

}
