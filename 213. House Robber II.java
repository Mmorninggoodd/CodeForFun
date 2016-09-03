/*

Note: This is an extension of House Robber.

After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

*/

public class Solution {
    /*
        Almost the same as 198 House Robber.
        dp[i] = max money can rob until index i
        dp[i] = max(nums[i] + dp[i-2], dp[i-1])
        
        Since there is a cycle, if we rob the first house, then we cannot rob the last house.
        So we can seperate two cases and run two dps:
        
        1. Rob the first house: then total money = nums[0] + dp run through [2:n-1] (not including the last house)
        2. Not to rob the first house: total money = dp run through [1:n]
        
        Then choose larger one
    */
    public int rob(int[] nums) {
        int n = nums.length - 1;
        if(n < 0) return 0;
        return Math.max(rob(nums, 2, n - 1) + nums[0], rob(nums, 1, n));
    }
    private int rob(int[] nums, int start, int end) {
        int pre = 0, cur = 0;
        for(; start <= end; start++) {
            int tmp = cur;
            cur = Math.max(nums[start] + pre, cur);
            pre = tmp;
        }
        return cur;
    }
}