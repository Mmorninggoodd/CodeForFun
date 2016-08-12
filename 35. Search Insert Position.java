/*MORE

Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0

*/

public class Solution {
	/*
		left <= right: Don't need to handle one element case seperately (result can be 0 or 1).
					   If we use left < right, one element case won't enter the loop
					   Left and right will be on the right and left of target position respectively.
					   here we want left, which is on the right of target position, to be the index inserted into
					   Also, we don't have left = mid or right = mid which will cause infinite loop when left <= right.
	    In this question, left / round shift doesn't matter, since we don't have left = mid or right = mid.
	*/
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length -1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) return mid;
            if(nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }
}