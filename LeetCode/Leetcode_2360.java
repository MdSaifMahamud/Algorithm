import java.util.*;
class Solution {
    private int dfs(int node,Set<Integer> visit,int[] edges,int[] depth,int cur_depth){
        if(visit.contains(node) || node==-1){
            return -1;
        }   
        if(depth[node]<cur_depth){
            return cur_depth-depth[node];
        } 
        depth[node]=cur_depth;
        int val=dfs(edges[node],visit,edges,depth,cur_depth+1); 
        visit.add(node);
        return val;
        
    }
    public int longestCycle(int[] edges) {
        int max_cycle=-1;
        Set<Integer> visit=new HashSet<>();
        int[] depth=new int[edges.length];
        for(int i=0;i<edges.length;i++){
            depth[i]=Integer.MAX_VALUE;
        }
        for(int i=0;i<edges.length;i++){
            int x=dfs(i,visit,edges,depth,0);
            if(x>max_cycle){
                max_cycle=x;
            }
        }
        return max_cycle;
    }
}
public class Leetcode_2360 {
    public static void main(String[] args) {
        Solution A=new Solution();
        int[] edges={2,-1,3,1};
        System.out.println(A.longestCycle(edges));
    }
}
