/*

Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: 
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

Hint:

Try to utilize the property of a BST.
What if you could modify the BST node's structure?
The optimal runtime complexity is O(height of BST).

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
	/* 1ms
		In-order traverse.
		O(max depth) space O(k + max depth) time
	*/
    int count = 0;
    int res = 0;
    public int kthSmallest(TreeNode root, int k) {
        if(root == null) return res;
        kthSmallest(root.left, k);
        if(++count == k) {  // find and return
            res = root.val;
            return res;
        }
        return kthSmallest(root.right,k);
    }
	/*
		Morris Traverse can in-order traverse with O(1) space
	*/
	/*
		For follow-up, we just add one member in the node class.
		size -- size of sub tree that rooted at this node.
		
		Each time we add/delete, we need to update all its parents' size. O(depth) time
		Each time we search, we can perform binary search O(depth) time.
		
		main idea:
		
		int leftSize = root.left == null ? 0 : root.left.size;
		if(leftSize + 1 == k) return root.val;
		if(leftSize + 1 > k) return search(root.left, k);
		else return search(root.right, k - leftSize - 1);
		
	*/
}