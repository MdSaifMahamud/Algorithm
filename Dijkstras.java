import java.util.*;
class Edge{
    int to;
    int weight;
    Edge(int to,int weight){
        this.to=to;
        this.weight=weight;
    }
}
class Graph{
    private int vertices;
    private List<List<Edge>>adjLists;
    Graph(int vertices){
        this.vertices=vertices;
        adjLists=new ArrayList<>();
        for(int i=0;i<this.vertices;i++){
            adjLists.add(new ArrayList<>());
        }
    }  
    public void addEdge(int i,int j,int weight){
        adjLists.get(i-1).add(new Edge(j-1, weight));
    }

    public void dijkstras(int src){
        src--;
        int[] dist=new int[this.vertices];
        boolean[] visited=new boolean[this.vertices];
        for(int i=0;i<this.vertices;i++){
            dist[i]=Integer.MAX_VALUE;
        }
        dist[src]=0;
        visited[src]=true;
        PriorityQueue<Edge> pq=new PriorityQueue<>(Comparator.comparingInt(e->e.weight));
        pq.add(new Edge(src, 0));
        while (!pq.isEmpty()) {
            Edge ed=pq.poll();
            int u=ed.to;
            for(Edge nbr:adjLists.get(ed.to)){
                int v=nbr.to;
                int weight=nbr.weight;
                if(dist[u]+weight<dist[v]){
                    dist[v]=dist[u]+weight;
                    pq.add(new Edge(v, dist[v]));
                }
            }
        }
        for(int i=0;i<this.vertices;i++){
            System.out.print(dist[i]+" ");
        }
        System.out.println();
    }
}
public class Dijkstras{
    public static void main(String[] args) {
        Graph graph=new Graph(6);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 4);
        graph.addEdge(2, 3, 1);
        graph.addEdge(2, 4, 7);
        graph.addEdge(3, 5, 3);
        graph.addEdge(4, 6, 1);
        graph.addEdge(5, 4, 2);
        graph.addEdge(5, 6, 5);
        graph.dijkstras(1);
    }
}