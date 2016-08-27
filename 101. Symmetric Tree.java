/*
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following [1,2,2,null,3,null,3] is not:
    1
   / \
  2   2
   \   \
   3    3
Note:
Bonus points if you could solve it both recursively and iteratively.
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
		Recursively
	*/
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return isSymmetric(root.left, root.right);
    }
    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if(left == null && right == null) return true;
        if(left == null || right == null) return false;
        if(left.val != right.val) return false;
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }
	
	/*
		In-order iteratively
	*/
	public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        Deque<TreeNode> leftStack = new ArrayDeque<>();
        Deque<TreeNode> rightStack = new ArrayDeque<>();
        push(leftStack, root.left, true);
        push(rightStack, root.right, false);
        while(!leftStack.isEmpty() && !rightStack.isEmpty()) {
            TreeNode left = leftStack.pop();
            TreeNode right = rightStack.pop();
            if(left.val != right.val || leftStack.size() != rightStack.size()) return false;
            push(leftStack, left.right, true);
            push(rightStack, right.left, false);
        }
        return leftStack.isEmpty() && rightStack.isEmpty();
    }
    private void push(Deque<TreeNode> stack, TreeNode root, boolean left) {
        while(root != null) {
            stack.push(root);
            if(left) root = root.left;
            else root = root.right;
        }
    }
}