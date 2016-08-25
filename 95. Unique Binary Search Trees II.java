/*
Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    /*
        dp[i] = dp[j] * (dp[i-j-1] + j + 1) for 0 <= j < i
        Only copy right sub nodes and shift their values
    */
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode>[] dp = new ArrayList[n + 1];
        dp[0] = new ArrayList<TreeNode>();
        if(n == 0) return dp[0];
        dp[0].add(null);
        for(int i = 1; i <= n; i++) {
            dp[i] = new ArrayList<TreeNode>();
            for(int j = 0; j < i; j++) {
                for(TreeNode left : dp[j]) {
                    for(TreeNode right : dp[i - j - 1]) {
                        TreeNode newRight = copy(right, j + 1);
                        TreeNode newRoot = new TreeNode(j + 1);
                        newRoot.left = left;
                        newRoot.right = newRight;
                        dp[i].add(newRoot);
                    }
                }
            }
        }
        return dp[n];
    }
    private TreeNode copy(TreeNode root, int shift) {
        if(root == null) return null;
        TreeNode newNode = new TreeNode(root.val + shift);
        newNode.left = copy(root.left, shift);
        newNode.right = copy(root.right, shift);
        return newNode;
    }
}