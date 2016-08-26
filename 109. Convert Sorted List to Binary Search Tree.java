/*

Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
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
		Similar with in-order traverse
		space O(lgn) time O(1)
		Use one class variable
	*/
    ListNode curNode;
    public TreeNode sortedListToBST(ListNode head) {
        curNode = head;
        int length = 0;
        while(head != null) {
            length++;
            head = head.next;
        }
        return sortedListToBST(1, length);
    }
    public TreeNode sortedListToBST(int start, int end) {
        if(start > end) return null;
        int mid = (start + end) >>> 1;
        TreeNode left = sortedListToBST(start, mid - 1);
        TreeNode root = new TreeNode(curNode.val);
        curNode = curNode.next;  // advance curNode here
        root.left = left;
        root.right = sortedListToBST(mid + 1, end);
        return root;
    }
}