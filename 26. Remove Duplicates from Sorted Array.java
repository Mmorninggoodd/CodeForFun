/*

Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.

*/

public class Solution {
	/*
		One pass. O(n) time O(1) space.
	*/
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int slow = 0, fast = 1;
        for(; fast < nums.length; fast++) {
            if(nums[fast] != nums[slow]) {
                if(fast != ++slow) nums[slow] = nums[fast]; // Only update when needed.
            }
        }
        return slow + 1;
    }
}