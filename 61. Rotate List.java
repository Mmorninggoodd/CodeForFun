/*
Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
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
	/* 1ms
	
		Simple problem. O(n) time O(1) space
		
		First find the length of list, and get the tail node at the same time.
		Then use a pointer go to the node right before shift position. (cur)
		Then we just change some next pointers of head, tail, cur.
	
	*/
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null) return head;
        int len = 1;
        ListNode tail = head, cur = head;
        for(; tail.next != null; len++) tail = tail.next;
        k %= len;  // in case k > len
        if(k == 0) return head;
        for(int i = 0; i < len - k - 1; i++) cur = cur.next; // len - k - 1 go to the one right before shift position
        tail.next = head;
        head = cur.next;
        cur.next = null;
        return head;
    }
}