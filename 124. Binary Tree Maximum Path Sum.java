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
    public int maxPathSum(TreeNode root) {
		int[] max = new int[]{Integer.MIN_VALUE};
        maxPathDown(root, max);
        return max[0];
    }
	/*
		Return max down path sum starting from current node
	*/
    private int maxPathDown(TreeNode root, int[] max) {
        if(root == null) return 0;
        int left = Math.max(0, maxPathDown(root.left, max));
        int right = Math.max(0, maxPathDown(root.right, max));
        max[0] = Math.max(max[0], left + right + root.val);
        return Math.max(left, right) + root.val;
    }
}