import java.util.*;
class DSU {
    private int[] parent;
    private int[] rank;

    public DSU(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    // Find with path compression
    public int find(int node) {
        if (parent[node] != node) {
            parent[node] = find(parent[node]);
        }
        return parent[node];
    }

    // Union by rank
    public void union(int root1, int root2) {
        if (rank[root1] < rank[root2]) {
            parent[root1] = root2;
        } else if (rank[root1] > rank[root2]) {
            parent[root2] = root1;
        } else {
            parent[root2] = root1;
            rank[root1]++;
        }
    }
}
class Edge {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge(" + src + " -- " + dest + ", weight: " + weight + ")";
    }
}

// class Graph {
//     int vertices;
//     List<Edge> edges;
//     public Graph(int vertices) {
//         this.vertices = vertices;
//         edges = new ArrayList<>();
//     }
//     public void addEdge(int src, int dest, int weight) {
//         edges.add(new Edge(src, dest, weight));
//     }

//     // Kruskal's algorithm to find MST
//     public List<Edge> kruskalMST() {
//         // Sort edges by weight
//         Collections.sort(edges, Comparator.comparingInt(e -> e.weight));

//         // Initialize DSU
//         DSU dsu = new DSU(vertices);
//         List<Edge> mst = new ArrayList<>();

//         for (Edge edge : edges) {
//             int rootSrc = dsu.find(edge.src);
//             int rootDest = dsu.find(edge.dest);

//             // Check if adding this edge creates a cycle
//             if (rootSrc != rootDest) {
//                 mst.add(edge); // Add to MST
//                 dsu.union(rootSrc, rootDest);
//             }
//             if (mst.size() == vertices - 1) {
//                 break;
//             }
//         }

//         return mst;
//     }
// }
class Graph{
    private int vertices;
    private List<Edge>EdgeList;
    Graph(int vertices){
        this.vertices=vertices;
        EdgeList=new ArrayList<>();
    }
    public void addEdge(int i,int j,int w){
        EdgeList.add(new Edge(i, j, w));
    }
    public int kruskalMST(){
        Collections.sort(EdgeList,Comparator.comparingInt(e -> e.weight));
        DSU dsu=new DSU(vertices);
        int ans=0;
        int cnt=0;
        for(Edge nbr:EdgeList){
            if(dsu.find(nbr.src)!=dsu.find(nbr.dest)){
                ans+=nbr.weight;
                dsu.union(nbr.dest, nbr.src);
                cnt++;
            }
            if(cnt==vertices-1){
                break;
            }
        }
        return ans;
    }
}
public class KruskalsAlgorithm {
    public static void main(String[] args) {
        Graph grp=new Graph(7);
        grp.addEdge(0, 5, 10);
        grp.addEdge(0, 1, 28);
        grp.addEdge(5, 4, 25);
        grp.addEdge(4, 6, 24);
        grp.addEdge(6, 1, 14);
        grp.addEdge(4, 3, 22);
        grp.addEdge(3, 6, 18);
        grp.addEdge(3, 2, 12);
        grp.addEdge(1, 2, 16);
        System.out.println(grp.kruskalMST());        
    }
}
