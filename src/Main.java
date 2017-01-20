import Algebra.*;
import Util.Sorter;

/**
 * Created by mugeebhassan on 25/11/16.
 */
public class Main {

    public static void main(String[] args){

//        HeapTree<Integer> heap = new HeapTree<>();
//
//        heap.add(20);
//        heap.add(4);
//        heap.add(26);
//        heap.add(3);
//        heap.add(9);
//        heap.add(21);
//        heap.add(30);
//        heap.add(11);
//        heap.add(7);
//        heap.add(2);
//
//        heap.printTree();
//        heap.checkHeap();

//        Graph<Integer> graph = new Graph<>(false);
//
//        graph.addVertex(0);
//        graph.addVertex(1);
//        graph.addVertex(2);
//        graph.addVertex(3);
//        graph.addVertex(4);
//        graph.addVertex(5);
//        graph.addVertex(6);
//        graph.addVertex(7);
//        graph.addVertex(8);
//
//        graph.addEdge(0,7,8);
//        graph.addEdge(0,1,4);
//        graph.addEdge(1,2,8);
//        graph.addEdge(1,7,11);
//        graph.addEdge(7,8,7);
//        graph.addEdge(7,6,1);
//        graph.addEdge(6,8,6);
//        graph.addEdge(8,2,2);
//        graph.addEdge(2,3,7);
//        graph.addEdge(2,5,4);
//        graph.addEdge(6,5,2);
//        graph.addEdge(3,5,14);
//        graph.addEdge(3,4,9);
//        graph.addEdge(5,4,10);
//
//        graph.dijsktra(0,4);

//        AVLTree<Integer> tree = new AVLTree<>();
//
//        tree.add(20);
//        tree.add(4);
//        tree.add(26);
//        tree.add(3);
//        tree.add(9);
//        tree.add(21);
//        tree.add(30);
//        tree.add(11);
//        tree.add(7);
//        tree.add(2);
//  
//        tree.printTree();
//
//        ZModGroup modAdd = new ZModGroup(2, ZModType.additive);
//        ZModGroup modMult = new ZModGroup(2, ZModType.multiplicative);
//
//        GaloisField<Integer> GF = new GaloisField<>(modAdd, modMult);
//
//        PolynomialSuggester<Integer> ps = new PolynomialSuggester<>(GF);
//
//        System.out.println("Irreducible polynomial: " + ps.getRandomIrreduciblePolynomial(8).toString());
//
//        PolynomialField<Integer> polynomialField = new PolynomialField<>(GF, new Polynomial<Integer>(GF, 1,0,1,0,1));

//        polynomialField.mult()

        Sorter<Integer> sorter = new Sorter<>();

        Integer[] sorted = sorter.heapSort(new Integer[]{1,1,3,1,10,4,5,1,6,3,1,7,5});

        for(int i:sorted){
            System.out.print(i + " ");
        }

    }
}
