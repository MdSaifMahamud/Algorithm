import java.util.*;

class Solution {
    private void dfs(char[][] board, int n, int m, int i, int j, boolean[][] vis) {
        vis[i][j] = true;
        int[] disx = {1, -1, 0, 0}; 
        int[] disy = {0, 0, 1, -1};

        for (int p = 0; p < 4; p++) {
            int ix = i + disx[p];
            int jy = j + disy[p];
            if (ix >= 0 && ix < n && jy >= 0 && jy < m && !vis[ix][jy] && board[ix][jy] == 'X') {
                dfs(board, n, m, ix, jy, vis); 
            }
        }
    }

    public int countBattleships(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        boolean[][] vis = new boolean[n][m];
        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'X' && !vis[i][j]) {
                    ans++; 
                    dfs(board, n, m, i, j, vis); 
                }
            }
        }

        return ans;
    }
}

public class LeetCode419 {
    public static void main(String[] args) {
        Solution A = new Solution();
        char[][] board = {
            {'.', '.', '.', 'X'},
            {'.', '.', 'X', 'X'},
            {'.', '.', '.', 'X'}
        };
        System.out.println(A.countBattleships(board));
    }
}
