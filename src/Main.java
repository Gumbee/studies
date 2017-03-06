import Graphs.Graph;
import Graphs.Vertex;

import java.util.Random;

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

        graph.addEdge(3,2);
        graph.addEdge(2,0);
        graph.addEdge(3,0);
        graph.addEdge(1,5);
        graph.addEdge(0,1);
        graph.addEdge(0,4);
        graph.addEdge(4,5);
        graph.addEdge(5,6);

        System.out.println(graph.getArticulationPoints());

    }
}
