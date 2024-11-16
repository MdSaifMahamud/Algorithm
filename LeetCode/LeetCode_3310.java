import java.util.*;
class Solution {
    private boolean dfs1(List<List<Integer>>graph,Set<Integer>Sus,List<Integer>l,int n){
        boolean f=true;
        for(int i=0;i<n;i++){
            if(Sus.contains(i)){
                continue;
            }
            for(Integer nbr:graph.get(i)){
                if(Sus.contains(nbr)){
                    return false;
                }
            }
        }
        return f;
    }
    private void dfs(int node,Set<Integer> Sus,List<List<Integer>>graph){
        if(Sus.contains(node)){
            return;
        }
        Sus.add(node);
        for(Integer nbr:graph.get(node)){
            dfs(nbr, Sus, graph);
        }
        return;
    }
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<List<Integer>>graph=new ArrayList<>();
        //List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<n;i++){
            graph.add(new ArrayList<>());
        }
        for(int[] invokes:invocations){
            int from=invokes[0];
            int to=invokes[1];
            graph.get(from).add(to);
        }
        List<Integer>ans=new ArrayList<>();
        for(int i=0;i<n;i++){
            ans.add(i);
        }
        Set<Integer>sus=new HashSet<>();
        dfs(k, sus, graph);
        if(dfs1(graph, sus, ans, n)){
            ans.clear();
            for(int i=0;i<n;i++){
                if(!sus.contains(i)){
                    ans.add(i);
                }
            }
            return ans;
        }
        return ans;
    }
}
public class Leetcode_3310 {
    public static void main(String[] args) {
        // n = 4, k = 1, invocations = [[1,2],[0,1],[3,2]]
        //n = 5, k = 0, invocations = [[1,2],[0,2],[0,1],[3,4]]
        //n = 3, k = 2, invocations = [[1,2],[0,1],[2,0]]
        int n=3;
        int k=2;
        int[][] invokes={{1,2}, {0,1},{2,0}};
        Solution A=new Solution();
       List<Integer>ans= A.remainingMethods(n, k, invokes);
       System.out.println(ans);
    }
}
