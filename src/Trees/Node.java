package Trees;

/**
 * Created by mugeebhassan on 25/11/16.
 */
public class Node<T extends Comparable<T>> {

    private T item;

    private Node<T> parent;
    private Node<T> leftChild;
    private Node<T> rightChild;

    //  1 if the right subtree is deeper
    //  0 if both subtrees are equal in depth
    // -1 if the left subtree is deeper
    public int balance = 0;


    public Node(T item){
        this.item = item;
    }

    public T getItem(){
        return item;
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

    public int compareTo(Node<T> o) {
        return item.compareTo(o.getItem());
    }
}
