/*

Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.

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
		One pass O(n).
		
		Loop invariant: node before and on cur are non-duplicate nodes, so cur cannot be null as well.
	*/
    public ListNode deleteDuplicates(ListNode head) {
        ListNode fakeHead = new ListNode(0), cur = fakeHead;
        fakeHead.next = head;
        while(cur.next != null && cur.next.next != null) { // detect duplicate after cur
            if(cur.next.val == cur.next.next.val) {
                ListNode dup = cur.next.next;
                while(dup != null && dup.val == cur.next.val) dup = dup.next; // stop until dup become null or non-duplicate node
                cur.next = dup;
            }
            else cur = cur.next; // only advance when we know cur.next is not null and non-duplicate node
        }
        return fakeHead.next;
    }
}