import Algebra.*;
import Graphs.Graph;
import Graphs.Vertex;

import java.util.ArrayList;

/**
 * Created by mugeebhassan on 25/11/16.
 */
public class Main {

    public static void main(String[] args){

        Graph<Integer> graph = new Graph<>();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(1,2,1);
        graph.addEdge(1,4,2);
        graph.addEdge(1,5,1);
        graph.addEdge(2,3,1);
        graph.addEdge(3,5,1);
        graph.addEdge(4,5,1);

        System.out.println(graph.getEdges().get(0).compareTo(graph.getEdges().get(1)));
        System.out.println(graph.BFS(4, 5));

    }
}
