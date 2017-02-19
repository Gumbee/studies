import Algebra.*;
import Graphs.Edge;
import Graphs.Graph;
import Trees.AVLTree;
import Trees.BinaryTree;
import Util.DisjointSet;
import Util.SetNode;
import Util.Sorter;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by mugeebhassan on 25/11/16.
 */
public class Main {

    public static void main(String[] args){

        Graph<Integer> graph = new Graph<>(false);

        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);
        graph.addVertex(7);
        graph.addVertex(8);

        graph.addEdge(0,7,8);
        graph.addEdge(0,1,4);
        graph.addEdge(1,2,8);
        graph.addEdge(1,7,11);
        graph.addEdge(7,8,7);
        graph.addEdge(7,6,1);
        graph.addEdge(6,8,6);
        graph.addEdge(8,2,2);
        graph.addEdge(2,3,7);
        graph.addEdge(2,5,4);
        graph.addEdge(6,5,2);
        graph.addEdge(3,5,14);
        graph.addEdge(3,4,9);
        graph.addEdge(5,4,10);

        ArrayList<Edge<Integer>> path = graph.dijsktra(0, 4);

        for(Edge<Integer> e: path){
            System.out.print(e.getStart() + " -> " + e.getEnd() + " | ");
        }

    }
}
