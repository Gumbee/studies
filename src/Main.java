import Graphs.Edge;
import Graphs.Graph;
import Graphs.Vertex;

/**
 * Created by mugeebhassan on 25/11/16.
 */
public class Main {

    public static void main(String[] args){

        Graph<String> graph = new Graph<>(true);

        // add some nodes to the graph
        graph.addVertex("Microsoft");
        graph.addVertex("Motorola");
        graph.addVertex("HTC");
        graph.addVertex("Apple");
        graph.addVertex("Kodak");
        graph.addVertex("Samsung");
        graph.addVertex("LG");
        graph.addVertex("Sony");
        graph.addVertex("Nokia");
        graph.addVertex("RIM");
        graph.addVertex("Qualcomm");
        graph.addVertex("Foxconn");
        graph.addVertex("Inventec");
        graph.addVertex("Amazon");
        graph.addVertex("Barnes");


        graph.addEdge("Microsoft", "Motorola");
        graph.addEdge("Motorola", "Apple");
        graph.addEdge("Microsoft", "HTC");
        graph.addEdge("HTC", "Apple");
        graph.addEdge("Apple", "Samsung");
        graph.addEdge("Samsung", "Kodak");
        graph.addEdge("Apple", "Kodak");
        graph.addEdge("RIM", "Kodak");
        graph.addEdge("LG", "Kodak");
        graph.addEdge("Sony", "LG");
        graph.addEdge("Microsoft", "Inventec");
        graph.addEdge("Microsoft", "Amazon");
        graph.addEdge("Microsoft", "Barnes");
        graph.addEdge("Microsoft", "Foxconn");
        graph.addEdge("Apple", "Nokia");
        graph.addEdge("Nokia", "Qualcomm");

        for(Edge e: graph.getEdges()){
            System.out.println(e.getStart().getItem() + " ──> " + e.getEnd().getItem());
        }

        System.out.println();
        System.out.println();


        System.out.println(graph.isAdjacent("Samsung", "Apple"));

        System.out.println();
        System.out.println();

        for(Vertex<String> n: graph.getNeighbors("Apple")){
            System.out.println(n.getItem().toString() + " is a neighbor of Apple");
        }

    }

}
