/*

Sort a linked list using insertion sort.

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
		A simple problem. Just three cases.
	*/
    public ListNode insertionSortList(ListNode head) {
        if(head == null) return null;
        ListNode sortedHead = head, sortedTail = head;
        head = head.next;
        while(head != null) {
            ListNode next = head.next;
            if(head.val <= sortedHead.val) {
                head.next = sortedHead;
                sortedHead = head;
            }
            else if(head.val >= sortedTail.val) {
                sortedTail.next = head;
                head.next = null;
                sortedTail = head;
            }
            else {
                for(ListNode cur = sortedHead.next, pre = sortedHead; cur != null; cur = cur.next) {
                    if(cur.val >= head.val) {
                        pre.next = head;
                        head.next = cur;
                        break;
                    }
                    pre = cur;
                }
            }
            head = next;
        }
        sortedTail.next = null;  // cut the tail
        return sortedHead;
    }
}