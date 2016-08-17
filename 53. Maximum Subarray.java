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
		Each sub problem return:
			(1) maxSoFar: Max sum
			(2) leftMax: Max sum starting from left bound
			(3) rightMax: Max sum starting from right bound
			(4) currSum: Sum of current sub array
		
		Merge:
			maxSoFar = max(left's maxSoFar, right's maxSoFar, left's rightMax + right's leftMax)
			leftMax = max(left's leftMax, left's currSum + right's leftMax)
			rightMax = max(right's rightMax, right's currSum + left's rightMax)
			currSum = left's currSum + right's currSum
			
		Time complexity: T(n) = 2T(n/2) + O(1) = O(n)
		Space complexity: At most logn call stacks at a time, so it is O(logn)
	*/
	private void search(int[] nums, int[] rslt, int left, int right) {
		if (left == right) {
			Arrays.fill(rslt, nums[left]);
			return;
		}

		int mid = left + (right - left) / 2;
		int[] leftRslt = new int[4];
		search(nums, leftRslt, left, mid);
		int[] rightRslt = new int[4];
		search(nums, rightRslt, mid + 1, right);

		rslt[0] = Math.max(Math.max(leftRslt[0], rightRslt[0]), leftRslt[2] + rightRslt[1]);
		rslt[1] = Math.max(leftRslt[1], leftRslt[3] +  + rightRslt[1]);
		rslt[2] = Math.max(rightRslt[2], rightRslt[3] +  + leftRslt[2]);
		rslt[3] = leftRslt[3] + rightRslt[3];
    }

    public int maxSubArray(int[] nums) {
		if (nums == null || nums.length == 0)
		throw new IllegalArgumentException("null or empty input array");

		int[] rslt = new int[4]; //maxSoFar, leftMax, rightMax, currSum
		search(nums, rslt, 0, nums.length - 1);
		return rslt[0];
    }
}