/*

Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level 

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
 
 /* 54ms 97.93%
	Time O(lgn)^2 Space O(lgn)
 
	Each time compare height of two sub trees (along right children).
	If both heights are the same, then right tree must be full, then its nodes = 2^height - 1.
	If not, then left tree must be full.
	
	Each time takes O(lgn) time, this would run O(lgn) times.
 */
public class Solution {
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        return countNodes(root, getHeightR(root.right));
    }
    private int countNodes(TreeNode root, int rightHeight) {
        if(root == null) return 0;
        int leftHeight = getHeightR(root.left);
        if(leftHeight == rightHeight) { // right tree is full
            return countNodes(root.left, leftHeight - 1) + (1 << rightHeight);
        }
        return countNodes(root.right, rightHeight - 1) + (1 << leftHeight); // left tree is full
    }
    private int getHeightR(TreeNode root) {
        int height = 0;
        while(root != null) {
            root = root.right;
            height++;
        }
        return height;
    }
}