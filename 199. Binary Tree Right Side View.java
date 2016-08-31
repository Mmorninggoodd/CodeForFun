/*

Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
You should return [1, 3, 4].
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
		Space O(depth) Time O(n)
		DFS but starting from right children
		Each time try to put current node into corresponding level's position in the result list.
		
	*/
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        rightSideView(root, 0, res);
        return res;
    }
    private void rightSideView(TreeNode root, int level, List<Integer> res) {
        if(root == null) return;
        if(res.size() == level) res.add(root.val);  // rightmost node will be the first one to add
        rightSideView(root.right, level + 1, res);
        rightSideView(root.left, level + 1, res);
    }
	
	/*
		If we want to do it iteratively, then we can use two stack: one to record node, the other one
		record depth of this node. Then use reversed pre-order traverse.
		
		Or we can create a new class that contains node and depth.
		
		Also, level order traverse usually takes O(n) space which is not that efficient when tree is wide.
	*/
}