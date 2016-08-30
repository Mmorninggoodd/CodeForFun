/*
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.
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
        First find half point.
        Then reverse latter part.
        Finally merge.
    */
    public void reorderList(ListNode head) {
        if(head == null) return;
        ListNode fast = head.next, slow = head; // a trick here is to fast = head.next such that latter part will be shorter than or same length as former one (so in merge part, you won't miss last element)
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode reverse = slow.next;
        slow.next = null;
        
        // reverse
        ListNode pre = null;
        while(reverse != null) {
            ListNode next = reverse.next;
            reverse.next = pre;
            pre = reverse;
            reverse = next;
        }
        reverse = pre;
        // merge
        slow = head;
        while(slow != null) {
            ListNode next = slow.next;
            slow.next = reverse;
            slow = reverse;
            reverse = next;
        }
    }
}