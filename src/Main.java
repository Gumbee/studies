import Algebra.*;
import Graphs.Edge;
import Graphs.Graph;
import Trees.AVLTree;
import Trees.BinaryTree;
import Util.DisjointSet;
import Util.SetNode;
import Util.Sorter;

import java.util.ArrayList;

/**
 * Created by mugeebhassan on 25/11/16.
 */
public class Main {

    public static void main(String[] args){

        BinaryTree<Integer> tree = new BinaryTree<>();

        tree.add(10);
        tree.add(1);
        tree.add(5);
        tree.add(15);
        tree.add(3);
        tree.add(6);
        tree.add(76);
        tree.add(16);

        tree.printTree();

    }
}
