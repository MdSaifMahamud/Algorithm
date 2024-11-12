import java.util.*;
class Solution {
    public boolean canFinish(int num, int[][] pre) {
        List<List<Integer>>adjList=new ArrayList<>();
        for(int i=0;i<num;i++){
            adjList.add(new ArrayList<>());
        }
        for(int i=0;i<pre.length;i++){
            for(int j=0;j<2;j++){
                adjList.get(i).add(j);
            }
        }
        return !containsCycleDirected(num, adjList);
    }
    
      private boolean dfs(int node,boolean[] visited,boolean[] stack,List<List<Integer>>adjLists){
        visited[node]=true;
        stack[node]=true;
        for(Integer nbr:adjLists.get(node)){
            if(stack[nbr]){
                return true;
            }
            else if(!visited[nbr]){
                if(dfs(nbr, visited, stack,adjLists)){
                    return true;
                }
            }
        }
        stack[node]=false;
        return false;
    }
    public boolean containsCycleDirected(int n,List<List<Integer>>l){
        boolean[] visited=new boolean[n];
        boolean[] stack=new boolean[n];
        for(int i=0;i<n;i++){
            if(!visited[i]){
                if(dfs(i, visited, stack,l)){
                    return true;
                }
            }
        }
        return false;
    }
    

}
public class LeetCode207 {
    public static void main(String[] args) {
        Solution A=new Solution();
        int[][] pre={{1,0}};
        int n=2;
        System.out.println(A.canFinish(n, pre));   
    }
}
