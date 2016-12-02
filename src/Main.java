import Graphs.Edge;
import Graphs.Graph;
import Graphs.Node;
import Trees.AVLTree;
import Trees.BinaryTree;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by mugeebhassan on 25/11/16.
 */
public class Main {

    public static void main(String[] args){

        Graph<String> graph = new Graph<>();

        // add some nodes to the graph
        graph.addNode("Berlin");
        graph.addNode("Madrid");
        graph.addNode("Zurich");
        graph.addNode("Rome");

        // create the nodes manually and then add them to the graph
        Node<String> munich = new Node<>("Munich");
        Node<String> geneva = new Node<>("Geneva");

        graph.addNode(munich);
        graph.addNode(geneva);

        graph.addEdge("Berlin", "Munich");
        graph.addEdge(munich, geneva);


        for(Edge e: graph.getEdges()){
            System.out.println(e.getStart().getItem() + " ──> " + e.getEnd().getItem());
        }

        System.out.println();
        System.out.println();

        // create a binary tree
        AVLTree<Integer> tree = new AVLTree<>();

        // add elements to the tree
        tree.add(11);
        tree.add(2);
        tree.add(8);
        tree.add(9);
        tree.add(5);
        tree.add(17);
        tree.add(20);
        tree.add(24);
        tree.add(18);
        tree.add(30);

        // print out the tree (formatted ASCII ART)
        tree.printTree();
    }

}
