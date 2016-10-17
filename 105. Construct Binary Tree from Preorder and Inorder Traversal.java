/*

Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

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
	/* 98% 2ms
		Recursively
	*/
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, inorder, 0, preorder.length - 1, 0);
    }
    private TreeNode buildTree(int[] preorder, int[] inorder, int inStart, int inEnd, int preStart) {
        if(inStart > inEnd) return null;
        int inMid = inEnd;
        while(inorder[inMid] != preorder[preStart]) inMid--;
        TreeNode node = new TreeNode(preorder[preStart]);
        node.left = buildTree(preorder, inorder, inStart, inMid - 1, preStart + 1);
        node.right = buildTree(preorder, inorder, inMid + 1, inEnd, preStart + (inMid - inStart + 1));
        return node;
    }
}