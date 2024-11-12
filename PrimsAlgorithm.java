import java.util.*;
class Edge{
    int to;
    int weigth;
    Edge(int to,int weigth){
        this.to=to;
        this.weigth=weigth;
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
    public void addEdge(int i,int j,int weigth){
        adjLists.get(i).add(new Edge(j, weigth));
        adjLists.get(j).add(new Edge(i, weigth));
    } 
    public int primsAlgorithm(){
        // implement prim's algorithm
        PriorityQueue<Edge> pq=new PriorityQueue<>(Comparator.comparingInt(e->e.weigth));
        boolean[] visited=new boolean[this.vertices];
        // visited[0]=true;
        int ans=0;
        pq.offer(new Edge(0, 0));
        while (!pq.isEmpty()) {
            Edge ed=pq.poll();
            int to1=ed.to;
            int wt1=ed.weigth;
            if(visited[to1]){continue;}
            ans+=wt1;
           visited[to1]=true;
                for(Edge nbr:adjLists.get(to1)){
                    int nbrTo=nbr.to;
                    int weigt=nbr.weigth;
                    if(!visited[nbrTo])
                        pq.offer(new Edge(nbrTo, weigt));
                    
                }
        }
        return ans;
    }
   
}

public class PrimsAlgorithm {
    public static void main(String[] args) {
        Graph grp=new Graph(7);
        grp.addEdge(0, 5, 10);
        grp.addEdge(0, 1, 28);
        grp.addEdge(5, 4, 25);
        grp.addEdge(4, 6, 24);
        grp.addEdge(6, 1, 14);
        grp.addEdge(4, 3, 22);
        grp.addEdge(3, 6, 18);
        grp.addEdge(3, 2, 12);
        grp.addEdge(1, 2, 16);
        System.out.println(grp.primsAlgorithm());
    }
}
