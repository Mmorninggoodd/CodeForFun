/*
Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?

*/

/*
	Time O(nlgn) Space O(n)
	
	A typical DP problem. Maintain a current increasing subsequence in a array.
*/
public class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for(int num : nums) {
            int left = 0, right = len;  // find the first index of number that >= num
            while(left < right) {
                int mid = (left + right) >>> 1;
                if(dp[mid] < num) left = mid + 1;
                else right = mid;
            }
            dp[left] = num;
            len = Math.max(len, left + 1);
        }
        return len;
    }
}