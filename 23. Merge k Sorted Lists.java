/*

Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

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
		Just use a minheap to store all front nodes, and exact one by one.
		Time O(nlogk) where k is number of lists, Space O(n)
	*/
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        ListNode fakeHead = new ListNode(0), cur = fakeHead;
        PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>(){
            @Override
            public int compare(ListNode n1, ListNode n2){
                return Integer.compare(n1.val, n2.val);
            }});
        for(ListNode n : lists) {
            if(n != null) heap.offer(n);
        }
        while(!heap.isEmpty()) {
            cur.next = heap.poll();
            cur = cur.next;
            if(cur.next != null) heap.offer(cur.next);
        }
        return fakeHead.next;
    }
	
	/*
		Use Merge Sort. O(nlogk)
		merge takes O(n) time and partition takes O(logk) time
	*/
	public ListNode mergeKLists(ListNode[] lists) {
        return mergeKLists(lists, 0, lists.length - 1);
    }
    public ListNode mergeKLists(ListNode[] lists, int start, int end) {
        if(start > end) return null;  // when lists.length == 0
        if(start == end) return lists[start];
        int mid = (start + end) / 2;
        ListNode fakeHead = new ListNode(0), cur = fakeHead;
        ListNode l1 = mergeKLists(lists, start, mid), l2 = mergeKLists(lists, mid + 1, end);
        // Merge two lists
		while(l1 != null || l2 != null) {
            if(l2 == null || (l1 != null && l1.val < l2.val)) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
		// Set to null so that won't effect other partitions
        cur.next = null;
        return fakeHead.next;
    }
}