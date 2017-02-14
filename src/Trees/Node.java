package Trees;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by mugeebhassan on 25/11/16.
 */
public class Node<T> {

    private T item;

    private Node<T> parent;
    private Node<T> leftChild;
    private Node<T> rightChild;

    private Comparator<T> comparator;

    //  1 if the right subtree is deeper
    //  0 if both subtrees are equal in depth
    // -1 if the left subtree is deeper
    public int balance = 0;

    public Node(T item){
        this(item, null);
    }

    public Node(T item, Comparator<T> comparator){
        this.item = item;
        this.comparator = comparator;
    }

    public T getItem(){
        return item;
    }

    /**
     * Sets the parent's right child or left child to null (depending on which child this node is)
     * This node's children are therefore also detached from this node's parent since the link between
     * the parent and this node is simply removed.
     */
    public void remove(){
        replace(null);
    }

    /**
     * replaces this node's position in the tree with another node.
     */
    public void replace(Node<T> node){
        if (getParent().getRightChild() != null && getParent().getRightChild().equals(this)) {
            getParent().setRightChild(node);
        } else {
            getParent().setLeftChild(node);
        }
    }

    /**
     * swaps this node's item with a replacement node's item
     * @param node
     */
    public void swap(Node<T> replacement){
        this.item = replacement.item;
    }

    /*==========================================
     * Getter Methods
     ===========================================*/

    public final Node<T> getParent(){
        return parent;
    }

    public final Node<T> getLeftChild(){
        return leftChild;
    }

    public final Node<T> getRightChild(){
        return rightChild;
    }

    /*==========================================
     * Setter Methods
     ===========================================*/

    public final void setParent(Node<T> parent){
        this.parent = parent;
    }

    public final void setLeftChild(Node<T> node){
        this.leftChild = node;
        if(node != null) {
            node.setParent(this);
        }
    }

    public final void setRightChild(Node<T> node){
        this.rightChild = node;
        if(node != null) {
            node.setParent(this);
        }
    }

    /*==========================================
     * Util Methods
     ===========================================*/

    public int compareTo(Node<T> o) {
        if (comparator != null)
            return compareUsingComparator(o.getItem());
        else{
            return compareComparable(o.getItem());
        }
    }

    /**
     * if no custom comparator is specified, the items will be treated as comparable elements and their
     * standard comparable-comparison is used
     * @param o item that this item is compared to
     */
    @SuppressWarnings("unchecked")
    private int compareComparable(T o){
        Comparable<? super T> that = (Comparable<? super T>) item;
        return that.compareTo(o);
    }

    /**
     * compares two elements by using a custom comparator
     * @param o item that this item is compared to
     */
    private int compareUsingComparator(T o){
        return comparator.compare(item, o);
    }

    @Override
    public String toString() {
        return item.toString();
    }
}
