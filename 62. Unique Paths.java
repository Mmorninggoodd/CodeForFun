/*

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?


Above is a 3 x 7 grid. How many possible unique paths are there?

Note: m and n will be at most 100.

*/

public class Solution {
	/*
		DP O(m*n) time O(min(m,n)) space
		dp[i,j] = # of unique paths to [i,j]
		Recursion:
			dp[i,j] = dp[i-1,j] + dp[i,j-1]
		Boundary:
			dp[i,j] = 1 for i = 0 or j = 0
	*/
    public int uniquePaths(int m, int n) {
        if(m > n) {
            int tmp = m; m = n; n = tmp;
        }
        // Now m is smaller one
        int[] dp = new int[m]; 
        dp[0] = 1;			// Boundary
        for(int i = 0; i < n; i++) {
            for(int j = 1; j < m; j++) {
                dp[j] += dp[j-1];
            }
        }
        return dp[m-1];
    }
}