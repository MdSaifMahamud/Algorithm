
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
    private void dfs(int node,String[] color,LinkedList<Integer> l,int[] d,int[] f){
        color[node]="gray";
        time++;
        d[node]=time;
        for(Integer nbr:adjLists.get(node)){
            if(color[nbr]=="white"){
                dfs(nbr, color, l,d,f);
            }
        }
        color[node]="black";
        time++;
        f[node]=time;
        l.addFirst((node+1));
    }
    public LinkedList<Integer> topological_sort(){
        LinkedList<Integer> l=new LinkedList<>();
        int[] d=new int[this.vertices];
        int[] f=new int[this.vertices];
        String[] color=new String[this.vertices]; 
        if(this.containsCycleDirected()==false){
            for(int i=0;i<this.vertices;i++){
                color[i]="white";
                d[i]=0;
                f[i]=0;
            }
            this.time=0;
            for(int i=0;i<this.vertices;i++){
                if(color[i]=="white"){
                    dfs(i, color, l,d,f);
                }
            }
        }else{
            System.out.println("Not Possible");
            return null;
        }
        return l;
    }
    public void display(){
        for(int i=0;i<adjLists.size();i++){
            System.out.print((i+1)+" : ");
            for(Integer nbr: adjLists.get(i)){
                System.out.print((nbr+1)+" ");
            }
            System.out.println();
        }
    }
}
    
public class Topological_Sort {
    public static void main(String[] args) {
    //   Graph g=new Graph(9);
    //     g.addEdge(1, 2);
    //     g.addEdge(1, 9);
    //     g.addEdge(2, 9);
    //     g.addEdge(8, 9);
    //     g.addEdge(2, 3);
    //     g.addEdge(3, 6);
    //     g.addEdge(4, 3);
    //     g.addEdge(4, 5);
    //     g.addEdge(5, 6);

        // g.display();
    //    LinkedList<Integer> l=g.topological_sort();
    //    System.out.println(l);
    Graph gp=new Graph(2);
    gp.addEdge(2, 1);
    System.out.println(gp.containsCycleDirected());
        
    }
}
