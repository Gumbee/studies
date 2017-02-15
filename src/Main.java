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

        ArrayList<Integer> unsorted = new ArrayList<>();

        unsorted.add(3);
        unsorted.add(5);
        unsorted.add(1);
        unsorted.add(7);
        unsorted.add(2);
        unsorted.add(9);
        unsorted.add(10);
        unsorted.add(0);

        System.out.println(unsorted);

        Sorter<Integer> sorter = new Sorter<>();

        System.out.println(sorter.mergeSort(unsorted));

    }
}
