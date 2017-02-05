import Algebra.*;
import Graphs.Edge;
import Graphs.Graph;
import Util.DisjointSet;
import Util.SetNode;
import Util.Sorter;

import java.util.ArrayList;

/**
 * Created by mugeebhassan on 25/11/16.
 */
public class Main {

    public static void main(String[] args){

        Graph<String> graph = new Graph<>(false);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");

        graph.addEdge("A", "B", 5);
        graph.addEdge("A", "E", 6);
        graph.addEdge("A", "F", 1);
        graph.addEdge("E", "F", 5);
        graph.addEdge("F", "B", 5);
        graph.addEdge("B", "C", 2);
        graph.addEdge("F", "C", 4);
        graph.addEdge("D", "C", 6);
        graph.addEdge("D", "F", 6);
        graph.addEdge("D", "E", 3);

        ArrayList<Edge<String>> mst = graph.MST();

        for(Edge<String> e : mst){
            System.out.println("Edge ("  + e.getStart().toString() + ", " + e.getEnd().toString() + ") with weight " + e.getWeight());
        }

    }
}
