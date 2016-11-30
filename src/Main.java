import Trees.AVLTree;
import Trees.BinaryTree;

/**
 * Created by mugeebhassan on 25/11/16.
 */
public class Main {

    public static void main(String[] args){
        AVLTree<Integer> tree = new AVLTree<>();

        tree.addItem(11);
        tree.addItem(5);
        tree.addItem(15);
        tree.addItem(18);
        tree.addItem(19);
        //tree.addItem(22);




        tree.printTree();

    }

}
