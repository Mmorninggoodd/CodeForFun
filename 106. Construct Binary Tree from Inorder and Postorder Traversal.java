/*

Given inorder and postorder traversal of a tree, construct the binary tree.

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
	/* 90%
		Similar with # 105
	*/
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, postorder, 0, inorder.length - 1, inorder.length - 1);
    }
    private TreeNode buildTree(int[] inorder, int[] postorder, int inStart, int inEnd, int postIndex) {
        if(inStart > inEnd) return null;
        TreeNode root = new TreeNode(postorder[postIndex]);
        int index = inEnd;
        while(inorder[index] != root.val) index--;
        root.left = buildTree(inorder, postorder, inStart, index - 1, postIndex - (inEnd - index) - 1);
        root.right = buildTree(inorder, postorder, index + 1, inEnd, postIndex - 1);
        return root;
    }
}