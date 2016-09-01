/*

Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

click to show more practice.

More practice:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).

*/


public class Solution {
	/*
		O(n) time O(1) space solution: Just a sliding window, and record minimal length of the window
	*/
    public int minSubArrayLen(int s, int[] nums) {
        int sum = 0, leftBound = 0, minLen = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while(sum - nums[leftBound] >= s) {   // narrow left bound
                sum -= nums[leftBound++];
            }
            if(sum >= s) minLen = Math.min(minLen, i - leftBound + 1); // record length
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
	
	/*
		O(nlgn) solution 1: Fixed window size
		Find if window size of k exists. Using binary search to narrow size range, using linear search to see if window exists. So it is O(n * lgn) time complexity, O(1) space
	*/
	public int minSubArrayLen(int s, int[] nums) {
        int left = 1, right = nums.length, minLen = 0;
        while(left <= right) {    // Not guarantee must exist, then use <=
            int mid = (left + right) >>> 1;
            if(existsWindow(s, nums, mid)) {
                minLen = mid;
                right = mid - 1;
            }
            else left = mid + 1;
        }
        return minLen;
    }
    private boolean existsWindow(int s, int[] nums, int size) {
        int sum = 0, right = 0;
        while(right < nums.length) {
            if(right >= size) sum -= nums[right - size];
            sum += nums[right++];
            if(sum >= s) return true;
        }
        return false;
    }
	
	/*
		O(nlgn) solution 2: Prefix Sum Array -> Range Sum Query
		Space O(n) Time O(nlgn)
	*/
	public int minSubArrayLen(int s, int[] nums) {
        int[] prefixSum = new int[nums.length + 1]; // right shift one position
        int minLen = Integer.MAX_VALUE;
        for(int i = 1; i <= nums.length; i++) prefixSum[i] = prefixSum[i-1] + nums[i-1];
        for(int left = 1; left <= nums.length; left++) {
            int right = binarySearch(prefixSum, left, nums.length, prefixSum[left - 1] + s);
            if(right == prefixSum.length) break;
            minLen = Math.min(minLen, right - left + 1);
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
    private int binarySearch(int[] nums, int left, int right, int target) {
        while(left <= right) {    // Not gurantee must exist, then use <=
            int mid = (left + right) >>> 1;
            if(nums[mid] >= target) right = mid - 1;
            else left = mid + 1;
        }
        return left;
    }
}