/*
Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        levelOrderBottom(root, res, 0);
        return res;
    }
    private void levelOrderBottom(TreeNode root, List<List<Integer>> res, int level) {
        if(root == null) return;
        if(res.size() == level) res.add(0, new ArrayList<>());  // add to front of the list
        res.get(res.size() - level - 1).add(root.val);
        levelOrderBottom(root.left, res, level + 1);
        levelOrderBottom(root.right, res, level + 1);
    }
	
	/*
		BFS iteratively
	*/
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        List<TreeNode> level = new LinkedList<>();
        if(root != null) level.add(root);
        int numlevel = 0;
        while(level.size() != 0) {
            List<TreeNode> newLevel = new LinkedList<>();
            res.add(0, new ArrayList<>());
            for(TreeNode node : level) {
                if(node.left != null) newLevel.add(node.left);
                if(node.right != null) newLevel.add(node.right);
                res.get(res.size() - numlevel - 1).add(node.val);
            }
            numlevel++;
            level = newLevel;
        }
        return res;
    }
}