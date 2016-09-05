/*

Given an array of integers, find out whether there are two distinct indices i and j in the array such that the difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.

*/


/* 10ms 98%
	Use a Binary Search Tree. 
	Only maintain k + 1 elements in the tree. Each time we insert a new element, we also check if there is a almost duplicate element.
	
	Each time insert and find duplicate takes O(max depth) time, each time deletion takes O(max depth) time.
	Space O(k).
*/

public class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        BST tree = new BST();
        for(int i = 0; i < nums.length; i++) {
            if(i > k) tree.remove(nums[i - k - 1]);
            if(tree.insertAndFindDuplicate(nums[i], t)) return true;
        }
        return false;
    }
}
class BST {
    public BSTNode root;
    public BST() {}
    public boolean insertAndFindDuplicate(int val, int t) {
        if(root == null) {
            root = new BSTNode(val);
            return false;
        }
        return insertAndFindDuplicate(root, val, t);
    }
    private boolean insertAndFindDuplicate(BSTNode node, int val, int t) {
        if(Math.abs((long)node.val - (long)val) <= (long) t) return true; // find duplicate
        if(val > node.val) {
            if(node.right == null) node.right = new BSTNode(val);
            else return insertAndFindDuplicate(node.right, val, t);
        }
        else {
            if(node.left == null) node.left = new BSTNode(val);
            else return insertAndFindDuplicate(node.left, val, t);
        }
        return false;
    }
    public void remove(int val) {
        root = remove(root, val);
    }
    private BSTNode remove(BSTNode node, int val) {
		if (node == null) return null;
        if(node.val == val) { // found the node to be removed
            if(node.left == null && node.right == null) return null;
            if(node.left == null) return node.right;
            if(node.right == null) return node.left;
            // both children are present
            BSTNode replacedNode = findMin(node.right);
            node.val = replacedNode.val;
            node.right = remove(node.right, node.val);
        }
        else if(node.val > val) {
            node.left = remove(node.left, val);
        }
        else node.right = remove(node.right, val);
        return node;
    }
    private BSTNode findMin(BSTNode node) {
        if (node == null) return null;
        while (node.left != null) node = node.left;
        return node;
    }
}

class BSTNode {
    public int val;
    public BSTNode left;
    public BSTNode right;
    public BSTNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}