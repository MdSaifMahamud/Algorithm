import java.util.*;
class Graph{
    private int vertices;
    private List<List<Integer>>adjLists;
    Graph(int vertices){
        this.vertices=vertices;
        adjLists=new ArrayList<>();
        for(int i=0;i<vertices;i++){
            adjLists.add(new ArrayList<>());
        }
    }
    public void addEdge(int i,int j){       //   directed graph         // 0 base index
        adjLists.get(i).add(j);
    }
    public void addEdge(int i,int j,boolean isUndir){
        adjLists.get(i).add(j);
        if(isUndir){
            adjLists.get(j).add(i);
        }
    }
    public void BFS(int Src){
        boolean[] visited=new boolean[this.vertices];
        Queue<Integer> q=new LinkedList<>();
        q.offer(Src);
        while(!q.isEmpty()){
            int cur=q.remove();
            if(visited[cur]==false){
                System.out.print(cur+" ");
                visited[cur]=true;
                for(Integer node:adjLists.get(cur)){
                    q.offer(node);
                }
            }
        }
        System.out.println();
    }
    
    // DFS
    private void DFS_Helper(int node,boolean[] visited){
        visited[node]=true;
        System.out.print(node+" ");
        for(Integer nbr:adjLists.get(node)){
            if(!visited[nbr]){
                DFS_Helper(nbr,visited);
            }
        }
    }
    public void DFS(){
        boolean[] visited=new boolean[this.vertices];
        for(int i=0;i<this.vertices;i++){
            if(!visited[i]){
                DFS_Helper(i, visited);
            }
        }
        System.out.println();
    }
    public void DFS(int src){
        boolean[] visited=new boolean[this.vertices];
        DFS_Helper(src, visited);
        System.out.println();
    }
    public void printGraph(){
        for(int i=0;i<this.vertices;i++){
            System.out.print(i+" : ");
            for(Integer nbr:adjLists.get(i)){
                System.out.print(nbr+" ");
            }
            System.out.println();
        }
    }
}
public class Graph_Traverse{
    public static void main(String[] args){
        Graph grp=new Graph(6);
        grp.addEdge(1, 0,true);
        grp.addEdge(0, 3,true);
        grp.addEdge(1, 3,true);
        grp.addEdge(0, 5,true);
        grp.addEdge(4, 5,true);
        grp.addEdge(4, 3,true);
        // grp.BFS(0);
        // grp.DFS();
        grp.DFS(0);
    }
}