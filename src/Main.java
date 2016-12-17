import Trees.AVLTree;
import Trees.HeapTree;

/**
 * Created by mugeebhassan on 25/11/16.
 */
public class Main {

    public static void main(String[] args){

        HeapTree<Integer> heap = new HeapTree<>((a,b) -> b-a);

        heap.add(5);
        heap.add(2);
        heap.add(8);
        heap.add(12);
        heap.add(6);
        heap.add(9);
        heap.add(13);
        heap.add(1);
        heap.add(3);
        heap.add(14);
        heap.add(19);
        heap.add(7);

        heap.printTree();
        heap.checkHeap();

    }
}
