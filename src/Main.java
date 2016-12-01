import Graphs.Edge;
import Graphs.Graph;
import Graphs.Node;
import Trees.AVLTree;
import Trees.BinaryTree;

/**
 * Created by mugeebhassan on 25/11/16.
 */
public class Main {

    public static void main(String[] args){

        Graph<String> graph = new Graph<>();

        Node<String> start = new Node<>("Munich");
        Node<String> end = new Node<>("Geneva");

        graph.addNode("Madrid");
        graph.addNode("Barcelona");
        graph.addNode("Berlin");
        graph.addNode("Bern");
        graph.addNode("Zurich");
        graph.addNode("Rome");
        graph.addNode(start);
        graph.addNode(end);

        graph.addEdge("Berlin", "Munich");
        graph.addEdge(start, end);

        System.out.println();
        System.out.println();

        for(Node n: graph.getNodes()){
            System.out.println("Node " + n.getItem().toString());
        }

        System.out.println();
        System.out.println();

        for(Edge e: graph.getEdges()){
            System.out.println("Edge from " + e.getStart().getItem().toString() + " to " + e.getEnd().getItem().toString());
        }

    }

}
