/*

Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

For example:
Given a binary tree {1,2,3,4,5},
   1
  / \
 2   3
/ \
4 5

return the root of the binary tree [4,5,2,#,#,3,1].
  4
 / \
5   2
   / \
  3   1
 
*/

/*
	Recursively O(depth) space

*/
public TreeNode UpsideDownBinaryTree(TreeNode root) {  
	if(root == null || root.left == null) return root; 
	TreeNode newRoot = UpsideDownBinaryTree(root.left);
	root.left.left = root.right;
	root.left.right = root;
	root.left = null;
	root.right = null;
	return newRoot;
}

/*
	Iterative O(1) space
*/
public TreeNode UpsideDownBinaryTree(TreeNode root) {
	TreeNode preRoot = null, preRight = null;
	while(root != null) {
		TreeNode next = root.left;
		root.left = preRight;
		preRight = root.right;  // save
		root.right = preRoot;
		preRoot = root;  // save
		root = next;  // advance
	}
	return preRoot;
}