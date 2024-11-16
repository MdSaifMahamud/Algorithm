import java.util.*;
class Solution {
    public int minDays(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        if(countIslans(grid, n, m)!=1){
            return 0;
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    grid[i][j]=0;
                    if(countIslans(grid, n, m)!=1){
                        return 1;
                    }
                    grid[i][j]=1;
                }
            }
        }
        return 2;

    }
    private int countIslans(int[][] grid,int n,int m){
        boolean[][] vis=new boolean[n][m];
        int cnt=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1 && !vis[i][j]){
                    dfs(grid, n, m, i, j, vis);
                    cnt++;
                }
            }
        }
        return cnt;
    }
    private void dfs(int[][] grid,int n,int m,int i,int j,boolean[][] vis){
        if(i<0 || i>=n || j<0 || j>=m){
            return ;
        }
        if(grid[i][j]==0 || vis[i][j]){
            return;
        }
        vis[i][j]=true;
        dfs(grid, n, m, i+1, j, vis);
        dfs(grid, n, m, i-1, j, vis);
        dfs(grid, n, m, i, j+1, vis);
        dfs(grid, n, m, i, j-1, vis);
    }
}
public class LeetCode1568 {
    public static void main(String[] args) {
        
    }
}
