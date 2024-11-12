
// Definition for a binary tree node.
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
 }
 
class Solution {
    private boolean dfs(TreeNode node,int curSum,int target){
        if(node==null){
            return false;
        }
        curSum+=node.val;
        if(node.left==null && node.right==null){
            return curSum==target;
        }else{
            return dfs(node.left,curSum,target) || dfs(node.right,curSum,target);
        }

    }
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return dfs(root,0,targetSum);
    }
}
public class leetcode_112 {
    public static void main(String[] args) {
        Solution A=new Solution();
        // A.hasPathSum(null, 0)
    }
}
