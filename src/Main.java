import Algebra.*;
import Graphs.Edge;
import Graphs.Graph;
import Trees.AVLTree;
import Util.DisjointSet;
import Util.SetNode;
import Util.Sorter;

import java.util.ArrayList;

/**
 * Created by mugeebhassan on 25/11/16.
 */
public class Main {

    public static void main(String[] args){

        Graph<Integer> graph = new Graph<>(false);

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(1,2,3);
        graph.addEdge(2,3,10);
        graph.addEdge(1,3,5);
        graph.addEdge(4,5,1);
        graph.addEdge(5,3,5);
        graph.addEdge(4,2,3);
        graph.addEdge(4,3,7);

        ArrayList<Edge<Integer>> mst = graph.MST();

        for(Edge<Integer> e : mst){
            System.out.println("Edge (" + e.getStart() + ", " + e.getEnd() + ") with weight " + e.getWeight());
        }

    }
}
