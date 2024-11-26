// package LeetCode;
import java.util.*;
class DSU {
    private int[] parent; // Stores the parent of each node
    private int[] rank;   // Used for union by rank

    // Constructor: Initializes DSU for 'n' elements
    public DSU(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i; // Each node is its own parent initially
            rank[i] = 0;   // Rank starts at 0
        }
    }

    // Find function with path compression
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    // Union function with rank optimization
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return false; // Already in the same set
        }

        // Union by rank
        if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }

        return true;
    }

    // Utility function to check if two elements are in the same set
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    // Example usage
    public static void main(String[] args) {
        DSU dsu = new DSU(5); // Create DSU for 5 elements (0 to 4)

        dsu.union(0, 1);
        dsu.union(1, 2);

        System.out.println(dsu.isConnected(0, 2)); // true
        System.out.println(dsu.isConnected(0, 3)); // false

        dsu.union(3, 4);

        System.out.println(dsu.isConnected(3, 4)); // true
        System.out.println(dsu.isConnected(2, 4)); // false
    }
}
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n=edges.length;
        DSU a=new DSU(n+1);
        int cnt=0;
        int[] ans={0,0};
        for(int i=0;i<n;i++){
            if(!a.isConnected(edges[i][0], edges[i][1])){
                cnt++;
                System.out.println(a.find(edges[i][0]) +"  "+ a.find(edges[i][1]));
                a.union(edges[i][0],edges[i][1]);
            }else{
                ans[0]=edges[i][0];
                ans[1]=edges[i][1];
            }   
        }
        return ans;
    }

}
public class LeetCode684 {
    public static void main(String[] args) {
        Solution A=new Solution();
        int[][] edges={{1,4},{3,4},{1,3},{1,2},{4,5}};
        int[] ans=A.findRedundantConnection(edges);
        System.out.println(ans[0]+" "+ans[1]);
    }   
}
