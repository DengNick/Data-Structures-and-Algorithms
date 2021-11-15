import java.util.LinkedList;
import java.util.*;
public class main {
    public static void main(String[] args){
        bookGraph();
    }

    private static void bookGraph(){
        Graph graph = new Graph(5);
        Edge[] edges = new Edge[10];
        edges[0] = new Edge(0,1,10);
		edges[1] = new Edge(0,3,5);
		edges[2] = new Edge(1,2,1);
		edges[3] = new Edge(1,3,2);
		edges[4] = new Edge(2,4,4);
		edges[5] = new Edge(3,1,3);
		edges[6] = new Edge(3,2,9);
		edges[7] = new Edge(3,4,2);
		edges[8] = new Edge(4,0,7);
		edges[9] = new Edge(4,2,6);

        for(int i = 0; i < 10; i++){
            graph.insertEdge(edges[i]);
        }

        graph.bianli();
        graph.DIJKSTRA(0);
    }
}
