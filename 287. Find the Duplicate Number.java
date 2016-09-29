/*

Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.

*/

public class Solution {
	/*
		Pigeonhole Principle
		
		Each time divide search range (value range, not index range) into two part.
		[left, mid] and [mid, right]
		
		If count of left part > mid (1 based), then there must exists one duplicate number in that range.
		
		Time O(nlgn) Space O(1)
	*/
    public int findDuplicate(int[] nums) {
        int left = 1, right = nums.length - 1;
        while(left < right) {
            int mid = (left + right) >>> 1;
            int count = 0;
            for(int num : nums) 
                if(num <= mid) count++;    // count number of <= mid
            if(count <= mid) left = mid + 1; // compare with mid so that you don't need to consider odd case
            else right = mid;
        }
        return left;
    }
	
	/*
		Find cycle in graph
		https://discuss.leetcode.com/topic/25685/java-o-n-time-and-o-1-space-solution-similar-to-find-loop-in-linkedlist
		
		Thought of index -> (value - 1) as a edge. (value is 1 based)
		So duplicate value will have degree of more than one, which forms a cycle.
		
		Note that we must start from the last element, because the largest value is n - 1.
		
		Time O(n) Space O(1)
	*/
	public int findDuplicate(int[] nums) {
        int n = nums.length;
        int slow = n, fast = n;
        do{
            slow = nums[slow - 1];
            fast = nums[nums[fast - 1] - 1];
        } while (slow != fast);  // two pointers meet
        slow = n;
        while(slow != fast) {  // find start point of cycle
            slow = nums[slow - 1];
            fast = nums[fast - 1];
        }
        return slow;
    }
	
}