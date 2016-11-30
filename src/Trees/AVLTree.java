package Trees;

/**
 * Created by mugeebhassan on 30/11/16.
 */
public class AVLTree<T extends Comparable<T>> extends BinaryTree<T> {

    @Override
    public final void insertNode(Node<T> currentNode, Node<T> node) {
        if(isEmpty()){
            this.root = node;
            return;
        }

        int balance = 0;

        // item must be inserted in the right subtree, as it is bigger as the current node
        if(currentNode.getItem().compareTo(node.getItem()) < 0){
            Node<T> newCurrentNode = currentNode.getRightChild();

            if(newCurrentNode != null){
                // current node has a right child, therefore we must compare the node
                // that is to be inserted with the right child
                insertNode(newCurrentNode, node);
                return;
            }else{
                // current node has no right child, therefore we can set the node that is to
                // be inserted as its new right child
                currentNode.setRightChild(node);
                balance = 1;
            }
        }else if(currentNode.getItem().compareTo(node.getItem()) > 0){
            Node<T> newCurrentNode = currentNode.getLeftChild();

            if(newCurrentNode != null){
                // current node has a left child, therefore we must compare the node
                // that is to be inserted with the left child
                insertNode(newCurrentNode, node);
                return;
            }else{
                // current node has no left child, therefore we can set the node that is to
                // be inserted as its new left child
                currentNode.setLeftChild(node);
                balance = -1;
            }
        }

        System.out.println("Will adjust for " + node.getItem().toString());
        adjustBalance(node);

        System.out.println();
    }

    /**
     * corrects the AVL-Balance
     * @param node the node whose AVL-Balance must be adjusted
     */
    private final void adjustBalance(Node<T> node){
        if(node.getParent() == null){
            return;
        }

        Node<T> parent = node.getParent();

        int balanceChange = node.getItem().compareTo(parent.getItem()) > 0?1:-1;
        parent.balance += balanceChange;

        System.out.println(parent.getItem().toString() + " with balance " + parent.balance);

        if(parent.balance > 1){
            System.out.println("Brace for left-rotation!");
            System.out.println("Node: " + node.getItem() + " with parent:" + parent.getItem());
            leftRotation(parent, node);
            return;
        }

        if(parent.balance < -1){
            System.out.println("Brace for right-rotation!");
            System.out.println("Node: " + node.getItem() + " with parent:" + parent.getItem());
            rightRotation(parent, node);
        }

        if(parent.balance != 0) {
            adjustBalance(parent);
        }
        System.out.println(parent.getItem().toString() + " with balance " + parent.balance);
    }

    private final void leftRotation(Node<T> parent, Node<T> child) {
        Node<T> childLeftSubtree = child.getLeftChild();
        Node<T> parentParent = parent.getParent();

        if(parentParent == null) {
            root = parent;
        }else if(parentParent.getRightChild() == parent) {
            parentParent.setRightChild(child);
        }else{
            parentParent.setLeftChild(child);
        }

        parent.setRightChild(childLeftSubtree);
        child.setLeftChild(parent);

        child.balance = 0;
        parent.balance = 0;
    }

    private final void rightRotation(Node<T> parent, Node<T> child) {
        Node<T> childRightSubtree = child.getRightChild();
        Node<T> parentParent = parent.getParent();

        if(parentParent == null) {
            root = parent;
        }else if(parentParent.getRightChild() == parent) {
            parentParent.setRightChild(child);
        }else{
            parentParent.setLeftChild(child);
        }

        parent.setLeftChild(childRightSubtree);
        child.setRightChild(parent);

        child.balance = 0;
        parent.balance = 0;
    }


}
