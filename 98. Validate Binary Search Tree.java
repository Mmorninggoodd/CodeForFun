/*
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
Example 1:
    2
   / \
  1   3
Binary tree [2,1,3], return true.
Example 2:
    1
   / \
  2   3
Binary tree [1,2,3], return false.
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
		1ms
		DFS. Just pass min max bound.
	*/

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MAX_VALUE, Long.MIN_VALUE);
    }
    public boolean isValidBST(TreeNode root, long max, long min) {
        if(root == null) return true;
        if(root.val >= max || root.val <= min)
            return false;
        return isValidBST(root.left, root.val, min) && isValidBST(root.right, max, root.val);
    }



	/*	1ms
		Use one class variable.
		In order traverse.
	*/
    TreeNode pre = null;
    public boolean isValidBST (TreeNode root){
        return inOrder(root);
    }
    public boolean inOrder (TreeNode curNode){
        if(curNode == null) return true;
        if((!isValidBST(curNode.left)) || (pre != null && curNode.val <= pre.val))
           return false;
        pre = curNode;
        return isValidBST(curNode.right);
    }
	
	/*
		4ms
		In order traverse iterative vesion.
	*/
	public boolean isValidBST (TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while(root != null || !stack.isEmpty()) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(pre != null && root.val <= pre.val) return false;
            pre = root;
            root = root.right;
        }
        return true;
    }
	
}