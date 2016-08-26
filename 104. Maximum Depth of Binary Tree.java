/*

Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
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
		DFS recursive O(depth) space
	*/
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
	
	/*
		BFS iteratively
	*/
	public int maxDepth(TreeNode root) {
        List<TreeNode> level = new ArrayList<>();
        int depth = 0;
        if(root != null) level.add(root);
        while(level.size() != 0) {
            List<TreeNode> newLevel = new ArrayList<>();
            for(TreeNode node : level) {
                if(node.left != null) newLevel.add(node.left);
                if(node.right != null) newLevel.add(node.right);
            }
            depth++;
            level = newLevel;
        }
        return depth;
    }
}