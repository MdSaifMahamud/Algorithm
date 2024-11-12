import java.util.*;
class Graph{
    private int vertices;
    private List<List<Integer>>adjLists;
    Graph(int n){
        this.vertices=n;
        adjLists=new ArrayList<>();
        for(int i=0;i<n;i++){
            adjLists.add(new ArrayList<>());
        }
    }
    public void addList(int i,int j){
        adjLists.get(i).add(j);
    }
    public void addList(int i,int j, boolean isUndir){
        adjLists.get(i).add(j);
        if(isUndir){
            adjLists.get(j).add(i);
        }
    }
    public void addEdge(int n){
        for(int i=0;i<n;i++){
            adjLists.add(new ArrayList<>());
        }
    }
    public List<List<Integer>> reverseGraph(){
        List<List<Integer>>revGraph=new ArrayList<>();
        for(int i=0;i<this.vertices;i++){
            revGraph.add(new ArrayList<>());
        }
        for(int i=0;i<this.vertices;i++){
            for(Integer nbr:adjLists.get(i)){
                revGraph.get(nbr).add(i);
            }
        }
        return revGraph;
    }
    private void dfs(int node,LinkedList<Integer>stack,boolean[] visited){
        visited[node]=true;
        // System.out.print(node+" ");
        for(Integer nbr:adjLists.get(node)){
            if(!visited[nbr]){
                dfs(nbr, stack, visited);
            }
        }
        stack.addFirst(node);
    }
    public void revDfs(int node,List<List<Integer>>revGraph,boolean[] vis){     //  normal dfs
        vis[node]=true;
        for(Integer nbr:revGraph.get(node)){
            if(!vis[nbr]){
                revDfs(nbr, revGraph, vis);
            }
        }
    }
    public int StronglyConnectedComponents(){
        // store value in topological order 
        LinkedList<Integer>stack=new LinkedList<>();
        boolean[] visited=new boolean[this.vertices];
        for(int i=0;i<this.vertices;i++){
            if(!visited[i]){
                dfs(i, stack, visited);
            }
        }
        int count=0;
        // transpose graph
        List<List<Integer>>revGraph=reverseGraph();
        // run dfs on reverse graph
        boolean[] vis=new boolean[this.vertices]; 
        while(!stack.isEmpty()){
            int tp=stack.getFirst();
            stack.removeFirst();
            if(!vis[tp]){
                count++;
                revDfs(tp, revGraph, vis);
            }
        }
        return count;
    }
}
public class SCC {
   public static void main(String[] args) {
     Graph gp=new Graph(5);
    gp.addList(0, 1);
    gp.addList(1, 3);
    gp.addList(2, 4);
    gp.addList(1, 2);
    gp.addList(4, 2);
    gp.addList(3, 0);
    //  System.out.println();
    
    System.out.println(gp.StronglyConnectedComponents());
   } 
}
