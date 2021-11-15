import java.util.LinkedList;
import java.util.*;
public class Graph {
    private LinkedList<Edge>[] edgeLinks;
        private int vNum;
        private int edgeNum;
        private int[] distance;
        private int[] prenode;
        private LinkedList<Integer> S;
        private LinkedList<Integer> Q;
        public static final int INF = 10000;
        public static final int NIL = -1;

        public Graph(int vnum){
            this.vNum = vnum;
            edgeLinks = new LinkedList[vnum];
            edgeNum = 0; //从这一点与其他点相连的个数
            distance = new int[vnum];
            prenode = new int[vnum];
            for(int i = 0; i < vnum; i++){
                edgeLinks[i] = new LinkedList<>(); //分配空间
            }

        }

        public void insertEdge(Edge edge){
            int v1 = edge.getV1();
            edgeLinks[v1].add(edge);
            edgeNum ++;
        }

        public void bianli(){
            System.out.println("共有" + vNum +  "个顶点" + edgeNum + "条边");
            for(int i = 0; i < vNum; i++){
                LinkedList<Edge> list = (LinkedList<Edge>) edgeLinks[i].clone();
                while(!list.isEmpty()){
                    Edge edge = list.pop();
                    System.out.println(edge.toString());
                }
            }
        }

        public void INITIALIZE_SINGLE_SOURCE(int start){
            for(int i = 0; i < vNum; i++){
                distance[i] = INF;
                prenode[i] = NIL;
            }

            distance[start] = 0;//开始节点

        }

        public void RELAX(Edge edge){
            int v1 = edge.getV1();
            int v2 = edge.getV2();
            int w = edge.getWeight();
            if(distance[v2] > distance[v1] + w){
                distance[v2] = distance[v1] + w;
                prenode[v2] = v1;
            }
        }

        public int EXTRACT_MIN(LinkedList<Integer> q){
            if(q.isEmpty()){
                return -1;
            }
            int min = q.getFirst();
            for(int i = 0; i < q.size(); i ++){
                int v = q.get(i);
                if(distance[min] > distance[v]){
                    min = v;
                }
            }
            q.remove(q.indexOf(min));
            return min;//寻找下一个距离最小的点
        }

        public void DIJKSTRA(int start){
            INITIALIZE_SINGLE_SOURCE(start);
            S = new LinkedList<>();
            Q = new LinkedList<>(); //未被循环到的点

            for(int i = 0; i < vNum; i++){
                Q.add(i);
            }

            while(!Q.isEmpty()){
                int u = EXTRACT_MIN(Q);
                S.add(u);
                //更新根据节点u已知的距离
                LinkedList<Edge> list = (LinkedList<Edge>) edgeLinks[u].clone();
                while(!list.isEmpty()){
                    Edge edge = list.pop();
                    RELAX(edge);
                }
            }

            ShowResult();


        }

        public void ShowResult(){
            Stack<Integer>[] routes = new Stack[vNum];
            for(int i = 0; i < vNum; i++){
                routes[i] = new Stack<>();
                int j = i;
                while(j != NIL){
                    routes[i].push(j);
                    //找到的最小路径的
                    j = prenode[j];
                }

                System.out.print(i + "(" + distance[i] + "):");

                while(!routes[i].isEmpty()){
                    int k = routes[i].pop();//获取最短路径
                    System.out.print("--->" + k);
                }

                System.out.println();
            }
        }
}
