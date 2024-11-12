import java.util.*;
//   Definition for a binary tree node.
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
    private void dfs(TreeNode node,int targetSum,int cur,List<Integer>l,List<List<Integer>>ans){
        if(node==null){
            return;
        }
        cur+=node.val;
        l.add(node.val);
        if(node.left==null && node.right==null && cur==targetSum){
            ans.add(new ArrayList<>(l));
        }else{
            dfs(node.left,targetSum,cur,l,ans);
            dfs(node.right,targetSum,cur,l,ans);
        }
        l.remove(l.size()-1);
    }
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> A=new ArrayList<>();
        List<Integer>a=new ArrayList<>();
        dfs(root, targetSum, 0, a, A);
        return A;
    }
}
public class LeetCode113 {
    public static void main(String[] args) {
        Solution A=new Solution();
        List<List<Integer>>ans;
        ans=A.pathSum(null, 0);
        System.out.println(ans);
    }
}
