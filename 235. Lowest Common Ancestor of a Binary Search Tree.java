/*

Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

        _______6______
       /              \
    ___2__          ___8__
   /      \        /      \
   0      _4       7       9
         /  \
         3   5
For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.

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
	/* 9ms 97.30%
		Iterative version 
		O(1) space O(max depth) time
	*/
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while(root != null) {
            if((root.val - (long)p.val) * (root.val - (long)q.val) <= 0) return root; // Using int will overflow, when p.val = Integer.MIN_VALUE, q.val = 11, root.val = 10.
            else if(root.val > p.val) root = root.left;
            else root = root.right;
        }
        return root;
    }
	/*
		Recursive
		Time & space O(max depth)
	*/
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;
        if( (root.val - (long)p.val) * (root.val - (long)q.val) <= 0 ) return root; // here is <= 0, means that case of equal also return root.
        if(root.val > Math.max(p.val, q.val)) return lowestCommonAncestor( root.left, p, q);
        return lowestCommonAncestor( root.right, p, q);
    }
}