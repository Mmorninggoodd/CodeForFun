/*

Given a singly linked list, determine if it is a palindrome.

Follow up:
Could you do it in O(n) time and O(1) space?

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
		Fist find half point using fast-slow
		Then reverse latter half part.
		Then traverse two parts to check if it is palindrome.
		Finally restore the list.
		
		Be careful two cases:
		odd: 1 2 3 2 1
		even: 1 2 3 3 2 1
	*/
    public boolean isPalindrome(ListNode head) {
        if(head == null) return true;
        ListNode fast = head.next, slow = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode tail = slow, tail2 = slow.next;      // tails of first and second half
        slow = reverse(slow.next); // head of reversed half part
        boolean isPalin = true;
        while(slow != null) {
            if(head.val != slow.val) isPalin = false;
            head = head.next;
            slow = slow.next;
        }
        reverse(tail2);  // restore original list
        tail.next = tail2;
        return isPalin;
    }
    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        while(head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}