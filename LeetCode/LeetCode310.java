import java.util.*;
class Solution {    
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer>res=new ArrayList<>();
        if(n<=0){return res;}
        if(n==1){
            res.add(0);
            return res;
        }
        List<List<Integer>>adjList=new ArrayList<>();
        int[] degree=new int[n];
        for(int i=0;i<n;i++){
            adjList.add(new ArrayList<>());
        }
        for(int[] ed:edges){
            adjList.get(ed[0]).add(ed[1]);
            adjList.get(ed[1]).add(ed[0]);
            degree[ed[0]]++;
            degree[ed[1]]++;
        }
        Queue<Integer>q=new LinkedList<>();
        for(int i=0;i<n;i++){
            if(degree[i]==1){
                q.add(i);
            }
        }
        while(n>2){
            int sz=q.size();
            n-=sz;
            while(sz-- >0){
                int v=q.poll();
                for(int i:adjList.get(v)){
                    degree[i]--;
                    if(degree[i]==1){
                        q.add(i);
                    }
                }
            }
        }
        res.addAll(q);
        return res;
    }
}
public class LeetCode310 {
    public static void main(String[] args) {
        Solution A=new Solution();
        int n=6;
        int[][] edges= {{3,0},{3,1},{3,2},{3,4},{5,4}};
        List<Integer>ans=A.findMinHeightTrees(n, edges);
        for(Integer a:ans){
            System.out.print(a+" ");
        }
        System.out.println();
    }   
}
