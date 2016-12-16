import Algebra.*;
import Graphs.Graph;
import Graphs.Vertex;

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

        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "C", 1);
        graph.addEdge("A", "D", 99);


        graph.dijsktra("A", "C");

    }
}
