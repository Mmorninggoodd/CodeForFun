/*
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

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
		O(n) time O(depth) space
	*/
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }
    public int height(TreeNode root) {
        if(root == null) return 0;
        int left = height(root.left);
        int right = height(root.right);
        return (left == -1 || right == -1 || Math.abs(left - right) > 1) ? -1 : Math.max(left, right) + 1;
    }
}