import java.util.*; 
class Graph{
    private int vertices;
    private List<List<Integer>>adjLists;
    private int time=0,forwardEdge=0,backedge=0,crossEdge=0,treeEdge=0;
    Graph(int v){
        this.vertices=v;
        adjLists=new ArrayList<>();
        for(int i=0;i<v;i++){
            adjLists.add(new ArrayList<>());
        }
    }
    public void addEdge(int i,int j)    // O base
    {
        adjLists.get(i-1).add(j-1);
    }
    public void addEdge(int i,int j,boolean isUndir){
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
    private boolean dfs(int node,boolean[] visited,int parent){
        visited[node]=true;
        for(Integer nbr:adjLists.get(node)){
            if(!visited[nbr]){
                if(dfs(nbr, visited, node)){
                    return true;
                }
            }else if(nbr!=parent){
                return true;
            }
        }
        return false;
    }
    public boolean containsCycleUndirected(){// 
        boolean[] visited=new boolean[this.vertices];
        // for(int i=0;i<this.vertices;i++){
        //     visited[i]=false;
        // }
        boolean isContainCycle=false;
        for(int i=0;i<this.vertices;i++){
            if(!visited[i]){
                if(dfs(1, visited, -1)){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean dfs(int node,boolean[] visited,boolean[] stack){
        visited[node]=true;
        stack[node]=true;
        for(Integer nbr:adjLists.get(node)){
            if(stack[nbr]){
                return true;
            }
            else if(!visited[nbr]){
                if(dfs(nbr, visited, stack)){
                    return true;
                }
            }
        }
        stack[node]=false;
        return false;
    }
    public boolean containsCycleDirected(){
        boolean[] visited=new boolean[this.vertices];
        boolean[] stack=new boolean[this.vertices];
        for(int i=0;i<this.vertices;i++){
            if(!visited[i]){
                if(dfs(i, visited, stack)){
                    return true;
                }
            }
        }
        return false;
    }
}
    

public class CycleDetection {
    public static void main(String[] args) {
        Graph gp=new Graph(6);
        // gp.addEdge(1, 2, true);
        // gp.addEdge(2, 3, true);
        // gp.addEdge(1, 6, true);
        // gp.addEdge(6, 4, true);
        // gp.addEdge(4, 5, true);
        // gp.addEdge(3, 1,true);
        // gp.addEdge(5, 6, true);

        gp.addEdge(6, 1);
        gp.addEdge(1, 2);
        gp.addEdge(2, 3);
        gp.addEdge(3, 6);
        gp.addEdge(6, 4);
        gp.addEdge(6, 5);
        gp.addEdge(5, 4);
        System.out.println(gp.containsCycleDirected());
        // System.out.println(gp.containsCycleUndirected());
    }
}
