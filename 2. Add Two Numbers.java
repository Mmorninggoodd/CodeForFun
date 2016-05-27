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
        boolean carry = false;
        ListNode head = new ListNode(0);
        ListNode curNode = head;
        while(l1 != null || l2 != null || carry){
            int sum = carry? 1 : 0;
            carry = false;
            if(l1 != null){
                sum += l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                sum += l2.val;
                l2 = l2.next;
            }
            if(sum >= 10){  // carry
                sum = sum % 10;
                carry = true;
            }
            curNode.next = new ListNode(sum);
            curNode = curNode.next;
        }
        return head.next;
    }
}