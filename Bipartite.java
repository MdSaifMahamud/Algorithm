package Algorithm;
import java.util.*;
class Graph{
    private int vertices;
    private List<List<Integer>>adjList;
    Graph(int v){
        this.vertices=v;
        adjList=new ArrayList<>();
        for(int i=0;i<v;i++){
            adjList.add(new ArrayList<>());
       }
    }
    public void addEdge(int i,int j){
        adjList.get(i).add(j);
    }
    public int[] inDegree(){
        int[] indegree=new int[this.vertices];
        Arrays.fill(indegree,0);
        for(List<Integer> nbr:adjList){
            for(int v:nbr){
                indegree[v]++;
            }
        }
       return indegree;
    }
    public int[] outDegree(){
        int[] outDegree=new int[this.vertices];
        Arrays.fill( outDegree,0);
        for(int i=0;i<this.vertices;i++){
            outDegree[i]=adjList.get(i).size();
        }
        return outDegree;
    }
    public boolean isBipartite(){
        int n=adjList.size();
        int[] color=new int[n];
        for(int i=0;i<n;i++){
            if(color[i]==0){
                if(!bfscheck(i, color)){
                    return false;
                }
            }
        }
        return true;
    }
    private boolean bfscheck(int str,int[] color){
        Queue<Integer> queue=new LinkedList<>();
        queue.add(str);
        color[str]=1;
        while(!queue.isEmpty()){
            int node=queue.poll();
            for(int nbr:adjList.get(node)){
                if(color[nbr]==0){
                    color[nbr]=-color[node];
                    queue.add(nbr);
                }else if(color[nbr]==color[node]){
                    return false;
                }
            }
        }
        return true;
    }
}
public class Bipartite {
    
}
