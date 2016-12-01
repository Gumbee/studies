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

        // item must be inserted in the right subtree, as it is bigger as the current node
        if(currentNode.compareTo(node) < 0){
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
            }
        }else if(currentNode.compareTo(node) > 0){
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
            }
        }

        adjustBalance(node);
        printTree();
        System.out.println();
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
        Node<T> superParent = parent.getParent();

        int balanceChange = node.compareTo(parent) > 0?1:-1;
        int superParentBalance = superParent!=null?parent.getParent().balance:0;

        parent.balance += balanceChange;

        if(parent.balance > 1){
            leftRotation(parent, node);
            return;
        }

        if(parent.balance < -1){
            rightRotation(parent, node);
            return;
        }

        if(superParentBalance > 0 && parent.balance < 0){
            rightRotation(parent, node);
            leftRotation(superParent, node);
            return;
        }

        if(superParentBalance < 0 && parent.balance > 0){
            leftRotation(parent, node);
            rightRotation(superParent, node);
            return;
        }

        if(parent.balance != 0) {
            adjustBalance(parent);
        }

    }

    /**
     * performs a left rotation around the given nodes (parent becomes child's left child)
     * @param parent parent that will become the left child of its child
     * @param child child who will become the new parent of its parent
     */
    private final void leftRotation(Node<T> parent, Node<T> child) {
        Node<T> childLeftSubtree = child.getLeftChild();
        Node<T> parentParent = parent.getParent();

        if(parentParent == null) {
            root = child;
        }else if(parentParent.getRightChild() == parent) {
            parentParent.setRightChild(child);
        }else{
            parentParent.setLeftChild(child);
        }

        parent.setRightChild(childLeftSubtree);
        child.setLeftChild(parent);
        child.setParent(parentParent);

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
        Node<T> parentParent = parent.getParent();

        if(parentParent == null) {
            root = child;
        }else if(parentParent.getRightChild() == parent) {
            parentParent.setRightChild(child);
        }else{
            parentParent.setLeftChild(child);
        }

        parent.setLeftChild(childRightSubtree);
        child.setRightChild(parent);
        child.setParent(parentParent);

        child.balance = 0;
        parent.balance = 0;
    }


}
