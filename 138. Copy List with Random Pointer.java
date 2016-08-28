/*

A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

*/

/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
	/*
		A O(n) time & space solution
		Using a HashMap to store (old node, new node) pairs.
		
		Two loops:
			#1: create new nodes
			#2: assign next and random pointers
	
	*/
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) return null;
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode cur = head;
        while(cur != null) {
            map.put(cur, new RandomListNode(cur.label));
            cur = cur.next;
        }
        cur = head;
        while(cur != null) {
            RandomListNode cloned = map.get(cur);
            cloned.next = cur.next == null ?  null : map.get(cur.next);
            cloned.random = cur.random == null ?  null : map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }
	
	/*
		Another O(n) time & space solution, however, it doesn't need additional space for HashMap.
	
	*/
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) return null;
        RandomListNode cur = head;
        while(cur != null) {  // put new node after each old node
            RandomListNode newNode = new RandomListNode(cur.label);
            newNode.next = cur.next;
            cur.next = newNode;
            cur = newNode.next;
        }
        cur = head;
        while(cur != null) { // assign random and next pointer
            cur.next.random = cur.random == null ? null : cur.random.next;
            cur = cur.next.next;
        }
        RandomListNode newHead = head.next, newCur = head.next;
        cur = head;
        while(newCur.next != null) {  // extract new nodes
            cur.next = newCur.next;
            newCur.next = newCur.next.next;
            cur = cur.next;
            newCur = newCur.next;
        }
        cur.next = null;
        return newHead;
    }
	
}