/*

Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.

*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
	
	/*
		O(n) time O(1) space
		Don't need dummy node, only need two pointers to proceed and one more to record head.
	
	*/
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode cur = head;  // Current start node
        head = head.next;
        while(cur != null && cur.next != null) {
			/* 
				Assume: 
					cur points to the first node of current batch (2 nodes)
					previous next already points to cur.next
			*/
            ListNode next = cur.next.next; // Next start node
            cur.next.next = cur;
			// Handle all cases of next batch
            cur.next = next != null ? (next.next != null ? next.next: next) : null;
            cur = next;
        }
        return head;
    }
	
	
}