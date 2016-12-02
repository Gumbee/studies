package Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by mugeebhassan on 25/11/16.
 */
public class BinaryTree<T extends Comparable<T>> {

    protected Node<T> root;
    protected int count = 0;

    ArrayList<Character>[] tree;

    public BinaryTree(){
        this(null);
    }

    public BinaryTree(Node<T> root){
        setRoot(root);
    }

    public final boolean isEmpty(){
        return root == null;
    }

    /**
     * creates a new node with an item as its content and then inserts it into the tree
     */
    public void add(T item){
        Node<T> node = new Node<>(item);
        // insert the new node into the tree and start the insertion process at the root
        insertNode(root, node);
        count++;
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

        // padding can either be 1 or 2 and defines the space between the nodes
        int padding = 1;

        Queue<Queue<Node<T>>> levels = getLevels();

        int index = levels.size();

        for(Queue<Node<T>> level: levels){
            for(Node<T> node: level){

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
                for(Node<T> node: level){
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
            System.out.println();
        }

    }

    /**
     * Each level of the tree is stored in a LinkedList and a LinkedList containing all LinkedLists is then returned.
     * @return LinkedList containing a LinkedList for each level of the tree
     */
    private final Queue<Queue<Node<T>>> getLevels(){
        // hasNonNullItem checks if there are still other nodes than null left on a certain level (depth)
        boolean hasNonNullItem = true;

        Queue<Queue<Node<T>>> levels = new LinkedList<>();

        Queue<Node<T>> currentLevel = new LinkedList<>();

        currentLevel.add(root);

        while (hasNonNullItem){
            LinkedList<Node<T>> nextLevel = new LinkedList<>();

            hasNonNullItem = false;

            for(Node<T> node: currentLevel){

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


}
