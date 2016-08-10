/*
MORE

Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 * pre cur -> next ->
 */
public class Solution {
	
	/*
		Recursively. Time O(n) space O(n/k)
	
	*/
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode cur = head;
        int count = 0;
        while(head != null && count++ < k) head = head.next; // To the last node of current batch 
        if(count < k) return cur;
        head = reverseKGroup(head, k); // Head of reversed remaining part
        
		// cur now points to the head of unreversed part, head points to the head of reversed part
		// Run for k times to reverse current part
		// Let cur point to head, and move to next position
        while(k-- > 0) {
            ListNode next = cur.next;
            cur.next = head;
            head = cur;
            cur = next;
        }
        return head;
    }
	
	/*
		Iteratively. Time O(n) Space O(1)
		Use prev to record tail of previous batch,
		tail to record tail of current batch.
		Use prev.next and tail.next to avoid creating two new pointers.
	*/
	public ListNode reverseKGroup(ListNode head, int k) {
        int n = 0;
        for(ListNode node = head; node != null; n++, node = node.next);
        ListNode dmy = new ListNode(0);
        dmy.next = head;
        // prev: The tail of previous batch
        // tail: The tail of current batch
        for(ListNode prev = dmy, tail = head; n >= k; n -= k) {
            for (int i = 1; i < k; i++) { // Only need k-1 reverse for each batch
                // Reverse direction between prev.next and tail.next each time
                // Then both next pointers move to next positions (prev and tail don't move)
                ListNode next = tail.next.next;
                tail.next.next = prev.next;
                prev.next = tail.next;
                tail.next = next;
            }
            // Until here, prev already points to the head of this reversed batch
            // tail points to start node of next batch
            // Note that prev and tail still keep their positions
            // Now move prev and tail to next batch's
            prev = tail;
            tail = tail.next;
        }
        return dmy.next;
    }
}