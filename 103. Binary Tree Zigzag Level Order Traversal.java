/*

Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
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
		DFS recursively
	*/
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        zigzagLevelOrder(root, res, 0);
        return res;
    }
    private void zigzagLevelOrder(TreeNode root, List<List<Integer>> res, int level) {
        if(root == null) return;
        if(res.size() <= level) res.add(new LinkedList<Integer>());
        if(level % 2 == 0) res.get(level).add(root.val);
        else res.get(level).add(0, root.val);
        zigzagLevelOrder(root.left, res, level + 1);
        zigzagLevelOrder(root.right, res, level + 1);
    }
	
	
	/*
		BFS iteratively
	*/
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<TreeNode> level = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        int numlevel = 0;
        if(root != null) level.add(root);
        while(!level.isEmpty()) {
            List<TreeNode> newLevel = new ArrayList<>();
            List<Integer> values = new LinkedList<>();
            for(TreeNode node : level) {
                if(node.left != null) newLevel.add(node.left);
                if(node.right != null) newLevel.add(node.right);
                if(numlevel % 2 == 0) values.add(node.val);
                else values.add(0, node.val);
            }
            res.add(values);
            level = newLevel;
            numlevel++;
        }
        return res;
    }
}