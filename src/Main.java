import Graphs.Graph;

/**
 * Created by mugeebhassan on 25/11/16.
 */
public class Main {

    public static void main(String[] args){

        Graph<Integer> graph = new Graph<>(true);

        long time = System.nanoTime();

        int n = 1000;

        for(int i=0;i<n;i++){
            graph.addVertex(i);
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                graph.addEdge(i, j, Math.random()*100);
            }
        }

        time = System.nanoTime() - time;

        System.out.println(time/1e9);

    }
}
