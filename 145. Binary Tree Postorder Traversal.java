/*

Given a binary tree, return the postorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [3,2,1].

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
        Handle node after traverse all its children.
        Just reversed of reversed left/right version of pre-order traverse
        
                1
            2      3
          4   5  #   6
    
        Pre-order:  1 2 4 5 3 6
        In-order:   4 2 5 1 3 6
        Post-order: 4 5 2 6 3 1
        
        
    */
    public List<Integer> postorderTraversal(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> res = new LinkedList<>();
        while(root != null || !stack.isEmpty()) {
            while(root != null) {
                stack.push(root);
                res.add(0, root.val);
                root = root.right;
            }
            root = stack.pop();
            root = root.left;
        }
        return res;
    }
}