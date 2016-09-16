/*

Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
*/

public class Solution {
	/*
		left points to first zero
		right iterate the array, if meet non-zero then swap with left.
		
		Note that make sure right >= left
	*/
    public void moveZeroes(int[] nums) {
        int left = 0, right = 0;
        while(right < nums.length) {
            if(right < left) right = left;
            else if(nums[left] != 0) left++;
            else if(nums[right] != 0) {
                nums[left++] = nums[right];
                nums[right++] = 0;
            }
            else right++;
        }
    }
	
	/*
		Another implementation.
	*/
	public void moveZeroes(int[] nums) {
        int zero = 0;
        for(int i = 0; i < nums.length; i++) {
            while(zero < nums.length && nums[zero] != 0) zero++;
            if(i < zero) i = zero;
            else if(nums[i] != 0) {
                nums[zero++] = nums[i];
                nums[i] = 0;
            }
        }
    }
}