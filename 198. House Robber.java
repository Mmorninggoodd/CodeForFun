/*

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

*/

public class Solution {
    /*
        dp[i] = max money can rob from [0, i]
        
        transaction:
        dp[i] = max(dp[i - 2] + nums[i], dp[i - 1])
        
        boundary:
        dp[0] = nums[0]
        dp[1] = max(nums[0], nums[1])
		
		Can be optimized to space O(1) time O(n)
    */
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int pre = 0, cur = nums[0];
        for(int i = 1; i < nums.length; i++) {
            int tmp = cur;
            if(cur < pre + nums[i]) cur = pre + nums[i];
            pre = tmp;
        }
        return cur;
    }
}