/*

You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Return the array [2, 1, 1, 0].

*/


/*
	BST  O(nlgn) time O(n) space
	https://discuss.leetcode.com/topic/31405/9ms-short-java-bst-solution-get-answer-when-building-bst
*/
class TreeNode {
    TreeNode left, right;
    int leftCount, val, dup;
    TreeNode(int val) {
        this.val = val;
        this.leftCount = 0;
        this.dup = 0;
    }
    
}
public class Solution {
    public List<Integer> countSmaller(int[] nums) {
        Integer[] count = new Integer[nums.length];
        if(nums.length == 0) return Arrays.asList(count);
        TreeNode root = new TreeNode(nums[nums.length - 1]);
        for(int i = nums.length - 1; i >= 0; i--) {
            count[i] = insertAndCount(root, nums[i]);
        }
        return Arrays.asList(count);
    }
    private int insertAndCount(TreeNode root, int num) {
        int count = 0;
        while(root.val != num) {
            if(root.val > num) {
                root.leftCount++;
                if(root.left == null) root.left = new TreeNode(num);
                root = root.left;
            } else {
                count += root.leftCount + root.dup;   // including root node
                if(root.right == null) root.right = new TreeNode(num);
                root = root.right;
            }
        }
        root.dup++;
        return count + root.leftCount;
    }
}