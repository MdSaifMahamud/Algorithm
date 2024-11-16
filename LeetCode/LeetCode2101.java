import java.util.*;
class Edge{
    int to;
    int weight;
    Edge(int to,int weight){
        this.to=to;
        this.weight=weight;
    }
}
class Graph{
    int vertices;
    List<List<Integer>>adjLists;
    Graph(int vertices){
        this.vertices=vertices;
        adjLists=new ArrayList<>();
        for(int i=0;i<vertices;i++){
            adjLists.add(new ArrayList<>());
        }
    }
    public void addEdge(int i,int j){
        adjLists.get(i).add(j);
        // adjLists.get(j).add(i);
    }
    public void dfs(int src, boolean[] visited,int[] cnt){
        visited[src]=true;
        cnt[0]++;
        for(Integer nbr:adjLists.get(src)){
            if(!visited[nbr]){
                dfs(nbr, visited, cnt);
            }
        }
    }
    
}
class Solution {
    public int maximumDetonation(int[][] bombs) {
        int ans=0;
        int n=bombs.length;
        Graph grp=new Graph(n);
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i==j){
                    continue;
                }

              if(isConnected(bombs[i], bombs[j])){
                grp.addEdge(i, j);
              }
            }
        }
        boolean[] vis=new boolean[n];
        for(int i=0;i<n;i++){
            int[] cnt={0};
            Arrays.fill(vis, false);
            if(!vis[i]){
                grp.dfs(i, vis, cnt);
                ans=Math.max(ans, cnt[0]);
            }

        }
        return ans;
    }
    public boolean isConnected(int[] a,int[] b){
        double tot_r=(a[2]+b[2]);
        double dis=Math.pow(Math.abs(a[0]-b[0]),2)+Math.pow(Math.abs(a[1]-b[1]),2);
        dis=Math.sqrt(dis);
        // System.out.println(a[2]+"  "+dis );
        if(a[2]>=dis){
            return true;
        }
        return false;
    }
    
}
public class LeetCode2101 {
    public static void main(String[] args) {
        Solution A=new Solution();
        int[][] bombs=  {{1,2,3},{2,3,1},{3,4,2},{4,5,3},{5,6,4}};
        System.out.println(A.maximumDetonation(bombs));
    }
}
