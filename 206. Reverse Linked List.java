/*

Reverse a singly linked list.

click to show more hints.

Hint:
A linked list can be reversed either iteratively or recursively. Could you implement both?
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
		iteratively
	*/
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        while(head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
	
	/*
	
		Tail-Recursive
	*/
	public ListNode reverseList(ListNode head) {
        return reverseList(head, null);
    }
    private ListNode reverseList(ListNode head, ListNode pre) {
        if(head == null) return pre; 
        ListNode next = head.next;
        head.next = pre;
        return reverseList(next, head);
    }
	/*
		Non-tail recursively
	*/
	private ListNode reverseList(ListNode head, ListNode pre) {
        if(head == null) return head; 
        ListNode newHead = reverseList(head.next, head);
        head.next = pre;
        return newHead == null ? head : newHead;
    }
}

/*
	Follow up: What if linked list contains cycle.
	
	Then you will reach head of linked list again, and the cycle part was reversed, the rest part is unchanged.
	
*/