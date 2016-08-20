/*

Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.

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
		Use two fake heads to simplify the code
	*/
    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(0), tail1 = small;
        ListNode large = new ListNode(0), tail2 = large;
        while(head != null) {
            if(head.val < x) {
                tail1.next = head;
                tail1 = tail1.next;
            }
            else {
                tail2.next = head;
                tail2 = tail2.next;
            }
            head = head.next;
        }
        tail1.next = large.next;
        tail2.next = null;
        return small.next;
    }
}