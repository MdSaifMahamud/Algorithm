// package CF;
import java.util.*;
public class Hierarchy {
    static class Edge{
        int to;
        int weigth;
        Edge(int to,int weigth){
            this.to=to;
            this.weigth=weigth;
        }
    }
    private static long findEdgeCost(int n,List<List<Edge>>adjList){
        int[] minCost=new int[n+1];
        int parentAssigned=0;
        long totalCost=0;
        Arrays.fill(minCost,Integer.MAX_VALUE);
        for(int i=0;i<=n;i++){
            if(adjList.get(i).isEmpty()){continue;}
            int cost=Integer.MAX_VALUE;
            for(Edge ed:adjList.get(i)){
                cost=Math.min(cost,ed.weigth);
            }
            if(cost!=Integer.MAX_VALUE){
                minCost[i]=cost;
                parentAssigned++;
                totalCost+=cost;
            }
        }  
        if(parentAssigned==n-1){
            return totalCost;
        }else{
            return -1;
        }
    } 
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] q=new int[n+1];
        for(int i=1;i<=n;i++){
            q[i]=sc.nextInt();
        }
        List<List<Edge>>adjList=new ArrayList<>();
        for(int i=0;i<=n;i++){
            adjList.add(new ArrayList<>());
        }
        int m=sc.nextInt();
        for(int i=0;i<m;i++){
            int a=sc.nextInt();
            int b=sc.nextInt();
            int c=sc.nextInt();
            if(q[a]>q[b]){
                adjList.get(b).add(new Edge(a, c));
            }
        }
        long ans=findEdgeCost(n, adjList);
        System.out.println(ans);

    }
}
