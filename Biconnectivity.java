import java.util.*;
class Graph{
    private int vertices;
    private List<List<Integer>>adj;
    public Graph(int vertices){
        this.vertices=vertices;
        adj=new ArrayList<>();
        for(int i=0;i<vertices;i++){
            adj.add(new ArrayList<>());
        }
    }
    public void addEdge(int u,int v){
        u--;
        v--;
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
    private void dfsArticulation(int u,boolean[] visited,int[] disc,int[] low,int[] parent,boolean[] isArticulation ,int[] time){
        disc[u]=low[u]= ++time[0];
        visited[u]=true;
        int children=0;
        for(int v:adj.get(u)){
            if(!visited[v]){
                children++;
                parent[v]=u;
                dfsArticulation(v, visited, disc, low, parent, isArticulation, time);
                low[u]=Math.min(low[u],low[v]);
                if(parent[u]==-1 && children>1){
                    isArticulation[u]=true;
                }
                if(parent[u]!=-1 && low[v]>=disc[u]){
                    isArticulation[u]=true;
                }
            }else if(v!=parent[u]){
                low[u]=Math.min(low[u],disc[v]);
            }
        }
    }
    public List<Integer> findArticulationPoint(){
        boolean[] visited=new boolean[this.vertices];
        int[] disc=new int[vertices];
        int[] low=new int[vertices];
        int[] parent=new int[vertices];
        Arrays.fill(parent,-1);
        int[] time={0};
        boolean[] isArticulation=new boolean[vertices];
        for(int i=0;i<vertices;i++){
            if(!visited[i]){
                
                dfsArticulation(i, visited, disc, low, parent, isArticulation, time);
            }

        }
        List<Integer> artiPoints=new ArrayList<>();
        for(int i=0;i<vertices;i++){
            if(isArticulation[i]){
                artiPoints.add(i+1);
            }
        }
        return artiPoints;
    }
}
public class Biconnectivity {
    public static void main(String[] args) {
        // Graph A=new Graph(6);
        // A.addEdge(1, 2);
        // A.addEdge(1, 4);
        // A.addEdge(3, 2);
        // A.addEdge(4, 3);
        // A.addEdge(3, 5);
        // A.addEdge(6, 3);
        // A.addEdge(5, 6);
        // List<Integer>ans=A.findArticulationPoint();
        // for(int a:ans){
        //     System.out.println(a+"  ");
        // }
        Graph B=new Graph(6);
        B.addEdge(1,2);
        B.addEdge(2,3);
        B.addEdge(2,4);
        B.addEdge(3,6);
        B.addEdge(3,5);
        B.addEdge(4,5);
        List<Integer>ans=B.findArticulationPoint();
        for(int a:ans){
            System.out.println(a+"  ");
        }
    }

    
}
// Backend Develpoment 
// web implementation and fuck fuck fuck 
