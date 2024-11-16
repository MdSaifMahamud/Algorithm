import java.util.*;
class Solution{
    private void dfs(int[][] grid,int n,int m,int[] cnt,boolean[][] vis,int i,int j,int[] disx,int[] disy){
        vis[i][j]=true;
        cnt[0]=cnt[0]+1;
        // System.out.println(cnt);
        for(int p=0;p<4;p++){
            int nx=i+disx[p];
            int ny=j+disy[p];
            if (nx >= 0 && ny >= 0 && nx < n && ny < m && !vis[nx][ny] && grid[nx][ny] == 1) {
                dfs(grid, n, m, cnt, vis, nx, ny, disx, disy);
            }
        }
    }
    public int maxAreaOfIsland(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        boolean[][] vis=new boolean[n][m];
        int[] disx={1,0,-1,0};
        int[] disy={0,1,0,-1};
        int ans=0;
        for(int i=0;i<n;i++){
            
            for(int j=0;j<m;j++){
                int[] cnt={0};
                if(grid[i][j]==1 && !vis[i][j]){
                    dfs(grid, n, m, cnt, vis, i, j, disx, disy);
                    ans=Math.max(ans, cnt[0]);
                }

            }
        }
      return ans;  
    }
}
public class LeetCode695 {
    public static void main(String[] args) {
        Solution A=new Solution();
        int[][] grid={{0,0,1,0,0,0,0,1,0,0,0,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,1,1,0,1,0,0,0,0,0,0,0,0},{0,1,0,0,1,1,0,0,1,0,1,0,0},{0,1,0,0,1,1,0,0,1,1,1,0,0},{0,0,0,0,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,0,0,0,0,0,0,1,1,0,0,0,0}};
        System.out.println(A.maxAreaOfIsland(grid));
    }
}
