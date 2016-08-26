/*

Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

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
		Use one class variable.
	*/
    int min = Integer.MAX_VALUE;
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        minDepth(root, 0);
        return min;
    }
    private void minDepth(TreeNode root, int depth) {
        if(depth >= min || root == null) return;  // terminate early
        if(root.left == null && root.right == null) min = depth + 1;
        else {
            minDepth(root.left, depth + 1);
            minDepth(root.right, depth + 1);
        }
    }
}