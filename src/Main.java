import Algebra.*;
import Util.DisjointSet;
import Util.SetNode;
import Util.Sorter;

import java.util.ArrayList;

/**
 * Created by mugeebhassan on 25/11/16.
 */
public class Main {

    public static void main(String[] args){

        DisjointSet<Integer> disjointSet = new DisjointSet<>();

        disjointSet.add(1);
        disjointSet.add(2);
        disjointSet.add(3);
        disjointSet.add(4);
        disjointSet.add(5);
        disjointSet.add(6);
        disjointSet.add(7);
        disjointSet.add(8);
        disjointSet.add(9);

        disjointSet.union(1,2);
        disjointSet.union(2,3);
        disjointSet.union(3,4);
        disjointSet.union(4,5);
        disjointSet.union(6,7);
        disjointSet.union(8,9);
        disjointSet.union(7,9);


        for(SetNode<Integer> e:disjointSet){
            System.out.println("Item " + e.item + " belongs to the set " + e.findSet());
        }



    }
}
