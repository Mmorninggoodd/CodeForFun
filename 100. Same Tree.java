/*

Given two binary trees, write a function to check if they are equal or not.

Two binary trees are considered equal if they are structurally identical and the nodes have the same value.

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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        if(p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
	
	
	/*
	
		Iterative in-order traverse space O(depth)
		(BFS space O(n))
	*/
	public boolean isSameTree(TreeNode p, TreeNode q) {
        Deque<TreeNode> stack1 = new ArrayDeque<>();
        Deque<TreeNode> stack2 = new ArrayDeque<>();
        pushLeft(stack1, p);
        pushLeft(stack2, q);
        while(!stack1.isEmpty() && !stack2.isEmpty()) {
            p = stack1.pop();
            q = stack2.pop();
            if(p.val != q.val || stack1.size() != stack2.size()) return false;
            pushLeft(stack1, p.right);
            pushLeft(stack2, q.right);
        }
        return stack1.isEmpty() && stack2.isEmpty();
    }
    private void pushLeft(Deque<TreeNode> stack, TreeNode root) {
        while(root != null) {
            stack.push(root);
            root = root.left;
        }
    }
}