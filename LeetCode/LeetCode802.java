import java.util.*;
class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<List<Integer>>adjList=new ArrayList<>();
        int indegree[]=new int[graph.length];
        for(int i=0;i<graph.length;i++){
            adjList.add(new ArrayList<>());
        }
        int v=graph.length;
        for(int i=0;i<v;i++){
            for(int it:graph[i]){
                adjList.get(it).add(i);
                indegree[i]++;
            }
        }
        Queue<Integer>q=new LinkedList<>();
        List<Integer> safeNodes=new ArrayList<>();
        for(int i=0;i<v;i++){
            if(indegree[i]==0){
                q.add(i);
            }
        }
        while(!q.isEmpty()){
            int node=q.peek();
            q.remove();
            safeNodes.add(node);
            for(int nbr:adjList.get(node)){
                indegree[nbr]--;
                if(indegree[nbr]==0){
                    q.add(nbr);
                }
            }
        }
        Collections.sort(safeNodes);
        return safeNodes;
    }
}
public class LeetCode802 {
    public static void main(String[] args) {
        Solution A=new Solution();
        int[][] a= {{1,2},{2,3},{5},{0},{5},{},{}};
        List<Integer>ans=A.eventualSafeNodes(a);
        for(int x:ans){
            System.out.print(x+" ");
        }
        System.out.println();
    }
}
