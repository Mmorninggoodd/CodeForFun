/*

Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6.

click to show more practice.

More practice:
If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

*/
public class Solution {
	/* 1ms
		The idea is simple, traverse the array from left to right, and calculate current max sum.
		When curMax < 0, it means it is not worthy to use previous numbers for following range.
		
		For example,
		[1, 2, -4, 1, 3]
		We can think of this array is divided into two parts [1, 2] and [1, 3].
		Because [1, 2, -4]'s sum < 0, it is not worthy to added into the following part.
		
		From DP aspect: 
		https://discuss.leetcode.com/topic/6413/dp-solution-some-thoughts
	
	*/
	public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0) 
			throw new IllegalArgumentException("null or empty input array");
        int curMax = nums[0], max = curMax;
        for(int i = 1; i < nums.length; i++){
            curMax = nums[i] + (curMax > 0 ? curMax : 0); // only add curMax when curMan > 0
            if(max < curMax) max = curMax;
        }
        return max;
    }
	
	/*
		Divide and Conquer
	*/
}