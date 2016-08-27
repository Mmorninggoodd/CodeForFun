/*

Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
	
*/

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        TreeLinkNode dummy = new TreeLinkNode(0);
        dummy.next = root;  // dummy.next always points to head of each level
        while(dummy.next != null) {
            TreeLinkNode pre = dummy, cur = dummy.next;  // pre.next (dummy.next) will first points to head of next level
            dummy.next = null;
            for(; cur != null; cur = cur.next) {
                if(cur.left != null) {
                    pre.next = cur.left;
                    pre = pre.next;
                }
                if(cur.right != null) {
                    pre.next = cur.right;
                    pre = pre.next;
                }
            }
        }
    }
}