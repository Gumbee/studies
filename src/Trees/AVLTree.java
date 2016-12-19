package Trees;

import java.util.Comparator;

/**
 * Created by mugeebhassan on 30/11/16.
 */
public class AVLTree<T> extends BinaryTree<T> {

    public AVLTree(){
        this(null, null);
    }

    public AVLTree(Node<T> root){
        this(root, null);
    }

    public AVLTree(Comparator<T> comparator){
        this(null, comparator);
    }

    public AVLTree(Node<T> root, Comparator<T> comparator){
        super(root, comparator);
    }

    /**
     * inserts a node into the tree and balances out the tree if necessary
     * @param currentNode the current node to which the new node is compared to
     * @param node a node that is to be inserted into the tree
     */
    @Override
    public final void insertNode(Node<T> currentNode, Node<T> node) {
        super.insertNode(currentNode, node);

        adjustBalance(node);

        /* For debugging purposes uncomment this block to see the tree's elements being added
        printTree();
        System.out.println();
        System.out.println();
        */
    }


    /**
     * rebalances the tree
     * @param node the node whose AVL-Balance must be adjusted
     */
    private final void adjustBalance(Node<T> node){
        // as long as there is a parent check if the AVL-condition was broken
        while(node.getParent() != null) {

            Node<T> parent = node.getParent();
            Node<T> superParent = parent.getParent();

            int balanceChange = node.compareTo(parent) > 0 ? 1 : -1;
            int superParentBalance = superParent != null ? parent.getParent().balance : 0;

            // we only update the parent immediately because the superParent will be updated when
            // the while loop moves on to the next iteration and superParent becomes
            // parent and superSuperParent becomes superParent
            parent.balance += balanceChange;

            // if the parent's balance got even worse than it was, correct the
            // balance immediately
            if (parent.balance > 1) {
                leftRotation(parent, node);
                return;
            }

            // if the parent's balance got even worse than it was, correct the
            // balance immediately
            if (parent.balance < -1) {
                rightRotation(parent, node);
                return;
            }

            // if the superParent's balance is shifted to the right and the parent's balance is shifted to the left
            // it will only get worse (for the superParent) if we are on the superParent's right branch and
            // therefore we perform a right-left-rotation
            if (superParentBalance > 0 && parent.balance < 0 && node.compareTo(superParent) > 0) {
                rightRotation(parent, node);
                leftRotation(superParent, node);
                return;
            }

            // if the superParent's balance is shifted to the left and the parent's balance is shifted to the right
            // it will only get worse (for the superParent) if we are on the superParent's left branch and
            // therefore we perform a left-right-rotation
            if (superParentBalance < 0 && parent.balance > 0 && node.compareTo(superParent) < 0) {
                leftRotation(parent, node);
                rightRotation(superParent, node);
                return;
            }

            if(parent.balance != 0) {
                node = parent;
            }else{
                return;
            }

        }

    }

    /**
     * performs a left rotation around the given nodes (parent becomes child's left child)
     * @param parent parent that will become the left child of its child
     * @param child child who will become the new parent of its parent
     */
    private final void leftRotation(Node<T> parent, Node<T> child) {
        Node<T> childLeftSubtree = child.getLeftChild();
        Node<T> superParent = parent.getParent();

        if(superParent == null) {
            root = child;
        }else if(superParent.getRightChild() == parent) {
            superParent.setRightChild(child);
        }else{
            superParent.setLeftChild(child);
        }

        parent.setRightChild(childLeftSubtree);
        child.setLeftChild(parent);
        child.setParent(superParent);

        child.balance = 0;
        parent.balance = 0;
    }

    /**
     * performs a right rotation around the given nodes (parent becomes child's right child)
     * @param parent parent that will become the right child of its child
     * @param child child who will become the new parent of its parent
     */
    private final void rightRotation(Node<T> parent, Node<T> child) {
        Node<T> childRightSubtree = child.getRightChild();
        Node<T> superParent = parent.getParent();

        if(superParent == null) {
            root = child;
        }else if(superParent.getRightChild() == parent) {
            superParent.setRightChild(child);
        }else{
            superParent.setLeftChild(child);
        }

        parent.setLeftChild(childRightSubtree);
        child.setRightChild(parent);
        child.setParent(superParent);

        child.balance = 0;
        parent.balance = 0;
    }


}
