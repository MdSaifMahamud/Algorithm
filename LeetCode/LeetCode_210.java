import java.util.*;
/*private void dfs(int node,String[] color,LinkedList<Integer> l,int[] d,int[] f){
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
    } */
class Solution {
    public int[] findOrder(int num, int[][] pre) {
        List<List<Integer>>adjLists=new ArrayList<>();
        int[] a=new int[num];
        for(int i=0;i<num;i++){
            adjLists.add(new ArrayList<>());
        }
        for(int i=0;i<num;i++){
            adjLists.get(pre[i][1]).add(pre[i][0]);
        }
        if(isCintainCycle(num, adjLists)){
            return a;
        }
        List<Integer> l=top_sort(num, adjLists);
        for(int i=0;i<l.size();i++){
            a[i]=l.get(i);
        }
        return a;
    }
    private boolean dfs(int node , List<List<Integer>>adjLists,boolean[] vis,boolean[] stack){
        vis[node]=true;
        stack[node]=true;
        for(Integer nbr:adjLists.get(node)){
            if(stack[nbr]){
                return true;
            }else if(!vis[nbr]){
                if(dfs(nbr, adjLists, vis, stack)){
                    return true;
                }   
            }
        }
        stack[node]=false;
        return false;
    }
    private boolean isCintainCycle(int n,List<List<Integer>> adjLists){
        boolean[] visited=new boolean[n];
        boolean[] stack=new boolean[n];
        for(int i=0;i<n;i++){
            if(!visited[i]){
                if(dfs(i, adjLists, visited, stack)){
                    return true;
                }
            }
        }
        return false;
    }
    private void dfs(int node,String[] color,int[] d,int[] f,int time,List<List<Integer>>adjLists,List<Integer>l){
        color[node]="gray";
        time++;
        d[node]=time;
        for(Integer nbr:adjLists.get(node)){
            if(color[nbr]=="white"){
                dfs(nbr, color, d, f, time, adjLists,l);
            }
        }
        color[node]="black";
        time++;
        f[node]=time;
        l.addFirst(node);

    }
    private LinkedList<Integer> top_sort(int num,List<List<Integer>>adLists){
        LinkedList<Integer> l=new LinkedList<>();
        int[] d=new int[num];
        int[] f=new int[num];
        String[] color=new String[num];
        int time=0;
        for(int i=0;i<num;i++){
            color[i]="white";
            d[i]=0;
            f[i]=0;
        }
        for(int i=0;i<num;i++){
            if(color[i]=="white"){
                dfs(i, color, d, f, time, adLists, l);
            }
        }
        return l;
    } 
}
public class LeetCode_210 {
    
}
