/*

A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

click to show spoilers.

Note:
Your solution should be in logarithmic complexity.

*/

public class Solution {
	/*
		Binary search. O(lgn)
		
		Just find any one index that start decreasing
		
		When there is uphill, then there must exists downhill on the right side.
		Similarly, when there is downhill, then there must exists uphill on the left side.
		
		Since every number must be distinct, so there must has a peak between them.
	*/
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while(left < right) {
            int mid = (left + right) >>> 1;
            if(nums[mid] < nums[mid + 1]) left = mid + 1;  // Not afraid of exceeding boundary, because mid is left shift, and left < right make sure that mid + 1 won't exceed right.
            else right = mid;
        }
        return left;
    }
}