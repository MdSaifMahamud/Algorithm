import java.util.*;
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
    private void Depth(TreeNode root,int depth,int maxdepth,List<Integer>ans){
        
        if(root==null){
            return;
        }
        depth+=1;
        Depth(root.left, depth, maxdepth,ans);
        Depth(root.right, depth, maxdepth,ans);
        maxdepth=Math.max(maxdepth,depth);
        ans.add(maxdepth);
        depth--;
    }
    private void dfs(TreeNode root,int depth,int maxd,List<Integer>sum){
        if(root==null){
            return ;
        }
        depth++;
        if(depth==maxd )
        {
            sum.add(root.val);
        }
        dfs(root.left, depth, maxd, sum);
        dfs(root.right, depth, maxd, sum);
        depth--;
        return;
    }
    public int deepestLeavesSum(TreeNode root) {
        List<Integer>ans=new ArrayList<>();
        Depth(root, 0, 0,ans);
        int maxdepth=0;
        for(Integer x:ans){
            maxdepth=Math.max(x, maxdepth);
        }
        List<Integer>ans1=new ArrayList<>();
        dfs(root, 0, maxdepth, ans1);
        int mans=0;
        for(Integer x:ans1){
            mans+=x;
        }
        return mans;
    }
}
public class LeetCode1302 {
    public static void main(String[] args) {
        //[1,2,3,4,5,null,6,7,null,null,null,null,8]
        // TreeNode root=new TreeNode();
    }
}
