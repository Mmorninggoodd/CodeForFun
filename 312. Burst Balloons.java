/*
Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note: 
(1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
(2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

Example:

Given [3, 1, 5, 8]

Return 167

    nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
   coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167

*/


public class Solution {
    /*
        If we try to solve it by brute force, we will find that there exists duplicate calculations.
        Because there exists optimal substructure problem.
        
        dp[i,j] = max coins can gain from nums[i,j]
        dp[i,j] = max(dp[i,k-1] + dp[k+1,j] + nums[i-1]*nums[k]*nums[j+1]) for all k such that i <= k <= j
            where j is the last balloon to be burst
        
        Boundary:
        dp[i,j] = 0 when i > j
        nums[-1] == nums[n] == 1
        
        Time O(n^3) Space O(n^2)
        
        It is easier to implement it in top-down memorization version
    */
    public int maxCoins(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int n = nums.length;
        return maxCoins(nums, new int[n][n], 0, n - 1);
    }
    private int maxCoins(int[] nums, int[][] memo, int i, int j) {
        if(i > j) return 0;
        if(memo[i][j] != 0) return memo[i][j];
        for(int lastBall = i; lastBall <= j; lastBall++) {
            memo[i][j] = Math.max(memo[i][j], maxCoins(nums, memo, i, lastBall - 1) + maxCoins(nums, memo, lastBall + 1, j)
						+ (i - 1 < 0 ? 1 : nums[i - 1]) * nums[lastBall] * (j + 1 >= nums.length ? 1 : nums[j + 1]));
        }
        return memo[i][j];
    }
	
	
	/*
		DP version (bottom up)
	*/
	public int maxCoins(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[][] dp = new int[n][n];
        for(int len = 1; len <= n; len++) {
            for(int start = 0; start < n - len + 1; start++) {
                int end = start + len - 1;
                for(int lastBall = start; lastBall <= end; lastBall++) {
                    dp[start][end] = Math.max(dp[start][end], (lastBall == start ? 0 : dp[start][lastBall-1]) 
                            + (lastBall == end ? 0 : dp[lastBall+1][end])
                            + (start - 1 < 0 ? 1 : nums[start - 1]) * nums[lastBall] * (end + 1 >= n ? 1 : nums[end + 1]));
                }
            }
        }
        return dp[0][n - 1];
    }
}