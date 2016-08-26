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
	/* 95%
		Recursively
	*/
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, inorder, 0, 0, preorder.length - 1);
    }
    private TreeNode buildTree(int[] preorder, int[] inorder, int preIndex, int inStart, int inEnd) {
        if(inStart > inEnd) return null;
        TreeNode root = new TreeNode(preorder[preIndex]);
        if(inStart == inEnd) return root;
        int index = inEnd;
        while(inorder[index] != root.val) index--; // search root index in inorder array
        root.left = buildTree(preorder, inorder, preIndex + 1, inStart, index - 1);
        root.right = buildTree(preorder, inorder, preIndex + index - inStart + 1, index + 1, inEnd); // Becareful indexes here
        return root;
    }
}