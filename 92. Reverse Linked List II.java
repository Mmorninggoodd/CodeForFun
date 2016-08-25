/*

Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.
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
		Method 1
		Use pre.next and start.next and next.
	*/
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode fake = new ListNode(0), pre = fake;
        fake.next = head;
        for(int i = 0; i < m - 1; i++) pre = pre.next; // point to the node before reversed part
        ListNode start = pre.next, next = start.next;  // start always points to start node of reversed part, next is the node will be reversed.
        for(int i = 0; i < n - m; i++) {
            start.next = next.next;
            next.next = pre.next;
            pre.next = next;
            next = start.next;
        }
        return fake.next;
    }
	
	/*
		Method 2
	*/
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(m == n) return head;
        ListNode fake = new ListNode(0);
        fake.next = head;
        ListNode tail, cur = fake;
        n -= m;
        while(--m > 0) cur = cur.next;
        // cur points to the node before start node
        tail = cur;
        ListNode tail2 = cur.next, pre = tail2;
        cur = pre.next;
        while(n-- > 0) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        tail.next = pre;
        tail2.next = cur;
        return fake.next;
    }
}