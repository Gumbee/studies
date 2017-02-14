package Trees;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by mugeebhassan on 25/11/16.
 */
public class BinaryTree<T> {

    protected Node<T> root;
    // number of items in the three
    protected int size = 0;

    private Comparator<T> comparator;

    public BinaryTree(){
        this(null, null);
    }

    public BinaryTree(Node<T> root){
        this(root, null);
    }

    public BinaryTree(Comparator<T> comparator){
        this(null, comparator);
    }

    public BinaryTree(Node<T> root, Comparator<T> comparator){
        setRoot(root);
        this.comparator = comparator;
    }

    public final boolean isEmpty(){
        return root == null;
    }

    /**
     * creates a new node with an item as its content and then inserts it into the tree
     */
    public void add(T item){
        Node<T> node = new Node<>(item, comparator);
        // insert the new node into the tree and start the insertion process at the root
        insertNode(root, node);
        size++;
    }

    /**
     * removes a node from the tree
     */
    public void remove(T item){
        Node<T> node = find(item);

        if(node == null){
            return;
        }

        // if the node is a leaf, simply remove it
        if(node.getRightChild() == null && node.getLeftChild() == null){
            // if the node is a leaf and the root, simply set root to null
            if(root.equals(node)){
                root = null;
            }else{
                node.remove();
            }
        }else if(node.getRightChild() == null){
            // if the node only has one child (a left child), replace the node's parents connection to the
            // node with a connection to the node's child
            node.replace(node.getLeftChild());
        }else if(node.getLeftChild() == null){
            // if the node only has one child (a right child), replace the node's parents connection to the
            // node with a connection to the node's child
            node.replace(node.getRightChild());
        }else{
            Node<T> replacement = node.getRightChild();

            while (replacement.getLeftChild() != null){
                replacement = replacement.getLeftChild();
            }

            // swap the items
            node.swap(replacement);
            // delete the replacement node by replacing it with its right child
            replacement.replace(replacement.getRightChild());
        }
    }

    /**
     * inserts a node into the tree and automatically places it at the right position
     * @param currentNode the current node to which the new node is compared to
     * @param node a node that is to be inserted into the tree
     */
    public void insertNode(Node<T> currentNode, Node<T> node){
        if(isEmpty()){
            this.root = node;
            return;
        }

        Node<T> current = currentNode;

        boolean canGoLeft = (current.getLeftChild() != null && current.compareTo(node) > 0);
        boolean canGoRight = (current.getRightChild() != null && current.compareTo(node) < 0);

        while(canGoLeft || canGoRight){
            current = current.compareTo(node)>=0?current.getLeftChild():current.getRightChild();

            canGoLeft = current.getLeftChild() != null && current.compareTo(node) > 0;
            canGoRight = current.getRightChild() != null && current.compareTo(node) < 0;
        }

        if(current.compareTo(node) < 0){
            current.setRightChild(node);
        }else if(current.compareTo(node) > 0){
            current.setLeftChild(node);
        }
    }

    // setter methods
    public final void setRoot(Node<T> root){
        if(this.root == null) {
            this.root = root;

            if(root != null && this.root.getParent() != null){
                this.root.setParent(null);
            }
        }
    }

    /**
     * prints out the tree in a formatted manner
     */
    public final void printTree() {
        BinaryTree.printBinaryTree(getLevels());
    }

    /*==========================================
     * Private Methods
     ===========================================*/

    /**
     * finds an item's node and returns it.
     * @return the item's corresponding node
     */
    private Node<T> find(T item){
        Node<T> itemNode = new Node<>(item, comparator);
        Node<T> current = root;

        // compare the item to the current node and move to the right child if the item is
        // bigger and to the left child if it's smaller
        while (current != null && !current.getItem().equals(item)){
            if(current.compareTo(itemNode) < 0){
                current = current.getRightChild();
            }else {
                current = current.getLeftChild();
            }
        }

        return current;
    }

    /**
     * Each level of the tree is stored in a LinkedList and a LinkedList containing all LinkedLists is then returned.
     * @return LinkedList containing a LinkedList for each level of the tree
     */
    private Queue<Queue<Node<?>>> getLevels(){
        // hasNonNullItem checks if there are still other nodes than null left on a certain level (depth)
        boolean hasNonNullItem = true;

        Queue<Queue<Node<?>>> levels = new LinkedList<>();

        Queue<Node<?>> currentLevel = new LinkedList<>();

        currentLevel.add(root);

        while (hasNonNullItem){
            LinkedList<Node<?>> nextLevel = new LinkedList<>();

            hasNonNullItem = false;

            for(Node<?> node: currentLevel){

                if(node != null && node.getLeftChild() != null) {
                    nextLevel.add(node.getLeftChild());
                    hasNonNullItem = true;
                }else{
                    nextLevel.add(null);
                }
                if(node != null && node.getRightChild() != null) {
                    nextLevel.add(node.getRightChild());
                    hasNonNullItem = true;
                }else{
                    nextLevel.add(null);
                }
            }

            levels.add(currentLevel);
            currentLevel = nextLevel;
        }

        return levels;
    }

    static void printBinaryTree(Queue<Queue<Node<?>>> levels){
        // padding can either be 1 or 2 and defines the space between the nodes
        int padding = 1;

        int index = levels.size();

        for(Queue<Node<?>> level: levels){
            for(Node<?> node: level){

                int itemLength = node!=null?node.getItem().toString().length():1;
                String itemString = node!=null?"\u001B[0m"+node.getItem().toString():"\u001B[31mØ";

                String spacingBefore = "";
                String spacingAfter = "";

                for(int i=0;i<padding*Math.pow(2,index)-1-Math.floor((itemLength-1)/2);i++){
                    spacingBefore += " ";
                }
                for(int i=0;i<padding*Math.pow(2,index)-1-(itemLength-1)+Math.ceil((itemLength-1)/2);i++){
                    spacingAfter += " ";
                }

                System.out.print(spacingBefore + itemString + spacingAfter + " ");
            }

            // draw the connections between the nodes
            for(int i=0;i<Math.pow(2,index-(2-padding))-1;i++){
                System.out.println();
                for(Node<?> node: level){
                    for(int j=0;j<(padding*Math.pow(2,index)*2);j++) {
                        if(j == padding * Math.pow(2,index)-(i+2)){
                            System.out.print("\u001B[30m/");
                        }else if(j == padding * Math.pow(2,index)+(i)){
                            System.out.print("\u001B[30m\\");
                        }else{
                            System.out.print(" ");
                        }
                    }
                }
            }

            index--;
            // reset color
            System.out.println("\u001B[0m");
        }
    }

}
