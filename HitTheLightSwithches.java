import java.util.*;
public class HitTheLightSwithches {
    public static void main(String[] args) throws Exception{
        Solution a=new Solution();

    }
}

class Solution {
    public static void main(String[] args) throws Exception {
         Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        sc.nextLine();
        while(t--!=0){
            int n=sc.nextInt();
            int m=sc.nextInt();
            sc.nextLine();
            List<List<Integer>>adjList=new ArrayList<>();
            for(int i=0;i<n;i++){adjList.add(new ArrayList<>());}
            for(int i=0;i<m;i++){
                int x=sc.nextInt();
                int y=sc.nextInt();
                sc.nextLine();
                adjList.get(x-1).add(y-1);
            }
            LinkedList<Integer> ordering=toposort(adjList, n);
            int tot_node=0;
            int ans=0;
            boolean[] visited=new boolean[n];
            Arrays.fill(visited,false);
            while(!ordering.isEmpty()){
                int x=ordering.peek();
                ordering.remove();
               if(!visited[x]){
                    ans++;
                    int[] a={0};
                    dfs(adjList, x, visited, a);
                    tot_node+=a[0];
                    a[0]=0;
               }
               if(tot_node==n){
                    break;
               }

            }
            System.out.println("Case "+t+": "+ans);
            
        }
    }
    private static void dfs(List<List<Integer>> adjList,int node,boolean[] visited,int[] a){
        visited[node]=true;
        a[0]++;
        for(int nbr:adjList.get(node))
        {
            if(!visited[nbr]){
                dfs(adjList, nbr, visited, a);
            }
        }
    }
    private static  LinkedList<Integer> toposort( List<List<Integer>> adjList,int n){
        LinkedList<Integer> srt=new LinkedList<>();
        boolean[] visited=new boolean[n];
        Arrays.fill(visited,false);
        for(int i=0;i<n;i++){
            if(!visited[i]){
                dfs(i, visited, srt, adjList);
            }
        }
        return srt;
    }
    private static void  dfs(int node,boolean[] visited,LinkedList<Integer> srt,List<List<Integer>> adjList){
        visited[node]=true;
        for(int nbr:adjList.get(node)){
            if(!visited[nbr]){
                dfs(nbr, visited, srt, adjList);
            }
        }
        srt.addFirst(node);
    }
}

// lightoj