import java.util.*;
class Edge{
    int to;
    double weight;
    Edge(int to,double weight){
        this.to=to;
        this.weight=weight;
    }
}
class Graph{
    private int vertices;
    private List<List<Edge>>adjLists;
    Graph(int vertices){
        this.vertices=vertices;
        adjLists=new ArrayList<>();
        for(int i=0;i<this.vertices;i++){
            adjLists.add(new ArrayList<>());
        }
    }  
    public void addEdge(int i,int j,double weight){
        adjLists.get(i).add(new Edge(j, weight));
        adjLists.get(j).add(new Edge(i, weight));
    }
    public double dijkstra(int src,int dest){
        double[] prob=new double[this.vertices];
        Arrays.fill(prob,0);
        prob[src]=1;
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(b[1], a[1])); // Max heap by probability
        pq.add(new double[]{src,1});
        while (!pq.isEmpty()) {
            double[] curr = pq.poll();
            int node = (int) curr[0];
            double currProb = curr[1];
            
            // If we reached the end node, return the probability
            if (node == dest) {
                return currProb;
            }
            
            // Explore neighbors
            for (Edge neighbor : adjLists.get(node)) {
                int neighborNode = neighbor.to;
                double edgeProb = neighbor.weight;
                
                // Calculate new probability for the neighbor
                double newProb = currProb * edgeProb;
                
                // If the new probability is better, update and push to the priority queue
                if (newProb > prob[neighborNode]) {
                    prob[neighborNode] = newProb;
                    pq.offer(new double[]{neighborNode, newProb});
                }
            }
        }
        return 0;
    } 
}
class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
            Graph grp=new Graph(n);
            for(int i=0;i<edges.length;i++){
                grp.addEdge(edges[i][0], edges[i][1], succProb[i]);
            }
            return grp.dijkstra(start_node, end_node);
        // return 0;
    }
}
public class LeetCode1514 {
    public static void main(String[] args) {
        Solution A=new Solution();
        //n = 3,[[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
        int n=3;
        int[][] edges = {{0,1}};
        double[] succProb={0.2};
        int start = 0, end = 2;
       
        System.out.println( A.maxProbability(n, edges, succProb, start, end));
    }
}
