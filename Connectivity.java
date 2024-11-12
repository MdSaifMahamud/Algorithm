import java.util.*;
class Graph{
    int vertices;
    List<List<Integer>>adjLists;
    Graph(int n){
        this.vertices=n;
        adjLists=new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            adjLists.add(new ArrayList<>());
        }
    }
    public void addEdge(int i,int j){
        adjLists.get(i-1).add(j-1);
    }
    public void addEdge(int i,int j, boolean isUndir){
        adjLists.get(i-1).add(j-1);
        if(isUndir){
            adjLists.get(j-1).add(i-1);
        }
    }
    public void addVertices(int n){
        for(int i=0;i<n;i++){
            adjLists.add(new ArrayList<>());
        }
    }
    private void dfs(int node,boolean[] visited){
        visited[node]=true;
        for(Integer nbr:adjLists.get(node)){
            if(!visited[nbr]){
                dfs(nbr,visited);
            }
        }
    }
    public boolean isConnectedGraph(){
        boolean[] visited=new boolean[this.vertices];
        int cnt=0;
        for(int i=0;i<this.vertices;i++){
            if(!visited[i]){
                cnt++;
                dfs(i,visited);
            }
            if(cnt>1){
                return false;
            }
        }
        return true;
    }
    private void dfs(int node,String[] color,int[] low,int[] d,int[] f,int[] prev,Set<Integer>artPoint,int time){
        color[node]="gray";
        time++;
        d[node]=time;
        low[node]=time;
        for(Integer nbr:adjLists.get(node)){
            if(color[nbr]=="white"){
                prev[nbr]=node;
                dfs(nbr, color, low, d, f,prev, artPoint, time);
                
                if(low[nbr]<low[node]){
                    low[node]=low[nbr];
                }
                if(prev[node]==-1){ // handle kora lagbe
                    // if(adjLists.get(node).size()>1){
                    //     // System.out.println((node+1)+"jk");
                    //     System.out.println(adjLists.get(node));
                    //     artPoint.add(node);
                    // }
                }else if(low[nbr]>=d[node]){
                    // System.out.println((node+1)+"pk");
                    artPoint.add(node);
                }
            }else if(prev[nbr]!=node){
                if(low[node]>d[nbr]){
                    low[node]=d[nbr];
                }
            }
        }
        color[node]="black";
        time++;
        f[node]=time;
    }
    public int ArticulationPoint(){
        int cnt=0;
        String[] color=new String[this.vertices];
        int[] low=new int[this.vertices];
        int[] d=new int[this.vertices];
        int[] f=new int[this.vertices];
        int[] prev=new int[this.vertices];
        for(int i=0;i<this.vertices;i++){
            color[i]="white";
            d[i]=0;
            f[i]=0;
            low[i]=0;
            prev[i]=-1;
        }
        Set<Integer>artPoints=new HashSet<>();
        for(int i=0;i<this.vertices;i++){
            if(color[i]=="white"){
                dfs(i, color, low, d, f, prev, artPoints, 0);
            }
        }
        for(Integer i:artPoints){
            System.out.print((i+1)+" ");
        }
        System.out.println();
        return cnt=artPoints.size();
    }
}
public class Connectivity {
    public static void main(String[] args) {
        Graph gp=new Graph(10);
        gp.addEdge(1, 2, true);
        gp.addEdge(2, 3, true);
        gp.addEdge(4, 3, true);
        gp.addEdge(2, 4, true);
        gp.addEdge(1, 5, true);
        gp.addEdge(1, 6, true);
        gp.addEdge(5, 6, true);
        gp.addEdge(7, 6, true);
        gp.addEdge(5, 7, true);
        gp.addEdge(8, 7, true);
        gp.addEdge(8, 9, true);
        gp.addEdge(10, 7, true);
        gp.addEdge(10, 9, true);
        // System.out.println(gp.isConnectedGraph());
        System.out.println(gp.ArticulationPoint());
    }
}
