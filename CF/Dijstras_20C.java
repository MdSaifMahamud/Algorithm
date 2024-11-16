// package CF;
import java.util.*;
public class Dijstras_20C{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        sc.nextLine();
        Graph grp=new Graph(n+1);
        for(int i=0;i<m;i++){
            int x=sc.nextInt(),y=sc.nextInt(),z=sc.nextInt();
            sc.nextLine();
            grp.addEdge(x, y, z);
        }
        LinkedList<Integer>ans=grp.dijkstras(1, n);
        if(ans.size()==0){
            System.out.println(-1);
        }else{
            for(Integer x:ans){
                System.out.print(x+" ");
            }
            System.out.println();
        }
    }
}
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
        for(int i=0;i<=this.vertices;i++){
            adjLists.add(new ArrayList<>());
        }
    }  
    public void addEdge(int i,int j,int weight){
        adjLists.get(i).add(new Edge(j, weight));
        adjLists.get(j).add(new Edge(i, weight));
    }
    public LinkedList<Integer> dijkstras(int src,int dest){
         int[] dist=new int[this.vertices];
         int[] parent=new int[this.vertices];
         boolean[] visited=new boolean[this.vertices];
         Arrays.fill(dist,Integer.MAX_VALUE);
         Arrays.fill(parent,-1);
         PriorityQueue<Edge>pq=new PriorityQueue<>(Comparator.comparingInt(e->e.weight));
         dist[src]=0;
         pq.offer(new Edge(src, 0));
         while(!pq.isEmpty()){
            Edge curr=pq.poll();
            int u=curr.to;
            if(visited[u]){continue;}
            visited[u]=true;
            if(u==dest){break;}
            for(Edge ed:adjLists.get(u)){
                int nbr=ed.to;
                int wt=ed.weight;
                if(!visited[nbr] && dist[u]+wt<dist[nbr]){
                    parent[nbr]=u;
                    dist[nbr]=dist[u]+wt;
                    pq.add(new Edge(nbr, dist[nbr]));
                }
            }

         }
        LinkedList<Integer> ans=new LinkedList<>();
        if(dist[dest]==Integer.MAX_VALUE){ 
            return ans;
        }
        for(int i=dest ;i!=-1;i=parent[i]){
            ans.addFirst(i);
        }
        return ans;
         
    }
}
