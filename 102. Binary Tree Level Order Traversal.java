/*

Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]

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
		Recursively DFS
	*/
	public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        levelOrder(root, res, 0);
        return res;
    }
    private void levelOrder(TreeNode root, List<List<Integer>> res, int level) {
        if(root == null) return;
        while(res.size() <= level) res.add(new ArrayList<Integer>());
        res.get(level).add(root.val);
        levelOrder(root.left, res, level + 1);
        levelOrder(root.right, res, level + 1);
    }
	/*
		Iterative BFS
	*/
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        List<TreeNode> level = new ArrayList<>();
        if(root != null) level.add(root);
        while(level.size() != 0) {
            List<TreeNode> newLevel = new ArrayList<>();
            List<Integer> values = new ArrayList<>();
            for(TreeNode node : level) {
                if(node.left != null) newLevel.add(node.left);
                if(node.right != null) newLevel.add(node.right);
                values.add(node.val);
            }
            res.add(values);
            level = newLevel;
        }
        return res;
    }
}