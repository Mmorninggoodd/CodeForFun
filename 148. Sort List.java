/*

Sort a linked list in O(n log n) time using constant space complexity.

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
		Bottom-up merge sort  O(nlgn) time O(1) space
		Many people claimed their merge sort is O(1) space, however, top-down merge-sort actually need O(lgn) space.
		
	*/
	public ListNode sortList(ListNode head) {
		int width = 1, length = 0;
		ListNode fakeHead = new ListNode(0);
		fakeHead.next = head;
		for(ListNode cur = head; cur != null; cur = cur.next) length++;
		while(width < length) {
			ListNode pre = fakeHead, head2 = fakeHead.next;
			int nCluster = length / (2 * width) + (length % (2 * width) != 0 ? 1 : 0);  // number of cluster of size (2*width)
			for(int cluster = 0 ; cluster < nCluster; cluster++) {  // sort each cluster 
				head = head2;  // get the head of first list (== pre.next)
				for(int i = 0; i < width && head2 != null; i++) head2 = head2.next; // get head of second list
				
				// merge two lists
				int leftLen = Math.min(width, length - cluster * 2 * width);
				int rightLen = Math.min(width, length - cluster * 2 * width - width);  // remaining length of second list
				while(leftLen > 0 || rightLen > 0) {
					if(leftLen <= 0 || (rightLen > 0 && head2.val <= head.val)) {
						rightLen--;
						pre.next = head2;
						head2 = head2.next;
					}
					else {
						leftLen--;
						pre.next = head;
						head = head.next;
					}
					pre = pre.next;
				}
				// now head2 should point to start node of next cluster
				// pre shoudl point to end node of previous cluster
				pre.next = head2; // let end of current cluster connect to head of unsorted next cluster
			}
			width *= 2; // double the width
		}
		return fakeHead.next;
	}
}