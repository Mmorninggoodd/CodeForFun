/*

Remove all elements from a linked list of integers that have value val.

Example
Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
Return: 1 --> 2 --> 3 --> 4 --> 5

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
		pretty easy. Just remove those elements when found.
		use a fake node to simplified the code.
	*/
    public ListNode removeElements(ListNode head, int val) {
        ListNode fakeHead = new ListNode(0), cur = fakeHead;
        fakeHead.next = head;
        while(cur.next != null) {
            if(cur.next.val == val) cur.next = cur.next.next;
            else cur = cur.next;
        }
        return fakeHead.next;
    }
}