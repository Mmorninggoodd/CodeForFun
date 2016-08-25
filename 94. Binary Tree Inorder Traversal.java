/*
Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree [1,null,2,3],
   1
    \
     2
    /
   3
return [1,3,2].

Note: Recursive solution is trivial, could you do it iteratively?

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
		Iterative
	*/
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        pushLeft(stack, root);
        while(!stack.isEmpty()) {
            root = stack.pop();
            res.add(root.val);
            pushLeft(stack, root.right);
        }
        return res;
    }
    private void pushLeft(Deque<TreeNode> stack, TreeNode root) {
        while(root != null) {
            stack.push(root);
            root = root.left;
        }
    }
	/*
		Recursively
	*/
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorderTraversal(res, root);
        return res;
    }
    private void inorderTraversal(List<Integer> res, TreeNode root) {
        if(root == null) return;
        inorderTraversal(res, root.left);
        res.add(root.val);
        inorderTraversal(res, root.right);
    }
}