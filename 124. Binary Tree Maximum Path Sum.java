/*

Given a binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path does not need to go through the root.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.

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
		DFS
		O(n) time O(depth) space
	*/
    int max;
    public int maxPathSum(TreeNode root) {
        max = Integer.MIN_VALUE;
        maxPathDown(root);
        return max;
    }
	/*
		Return max down path sum starting from current node
	*/
    private int maxPathDown(TreeNode root) {
        if(root == null) return 0;
        int left = Math.max(0, maxPathDown(root.left));
        int right = Math.max(0, maxPathDown(root.right));
        if(max < left + right + root.val) max = left + right + root.val;
        return Math.max(left, right) + root.val;
    }
}