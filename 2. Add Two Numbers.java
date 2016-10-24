/*
You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8

*/


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 /*
		Time Complexity O(n1 + n2), Space Complexity O(n1 + n2)
 
 */
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode head = new ListNode(0), cur = head;
        while(l1 != null || l2 != null || carry != 0) {
            int val = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
            carry = val / 10;
            cur.next = new ListNode(val % 10);
            cur = cur.next;
            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }
        return head.next;
    }
}