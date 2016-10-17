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
	/* 93% 2ms
		Similar with # 105
	*/
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, postorder, 0, inorder.length - 1, postorder.length - 1);
    }
    private TreeNode buildTree(int[] inorder, int[] postorder, int inStart, int inEnd, int postIndex) {
        if(inStart > inEnd) return null;
        int inMid = inEnd;
        while(inorder[inMid] != postorder[postIndex]) inMid--;
        TreeNode node = new TreeNode(postorder[postIndex]);
        node.left = buildTree(inorder, postorder, inStart, inMid - 1, postIndex - (inEnd - inMid + 1));
        node.right = buildTree(inorder, postorder, inMid + 1, inEnd, postIndex - 1);
        return node;
    }
}