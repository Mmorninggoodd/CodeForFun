/*
Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

For example,

   1
    \
     3
    / \
   2   4
        \
         5 
Longest consecutive sequence path is 3-4-5, so return 3.

   2
    \
     3
    / 
   2    
  / 
 1 
Longest consecutive sequence path is 2-3,not3-2-1, so return 2.

*/

/*
	Just dfs 
	Pass current length of consecutive path, and pre value, and current max
*/
public int longestConsecutive(TreeNode root) {
	if(root == null) return 0;
	return dfs(root, root.val, 0, 1);
}
private int dfs(TreeNode root, int pre, int len, int max) {
	if(root == null) return max;
	max = Math.max(len, max);
	if(root.val == pre + 1) len++;
	else len = 1;
	return Math.max(max, Math.max(dfs(root.left, root.val, len, max), dfs(root.right, root.val, len, max)));
}