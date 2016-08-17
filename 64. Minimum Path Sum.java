/*

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

*/
public class Solution {
    /*
        DP O(n*m) time 
        dp[i,j] = minimum path sum to [i,j]
        Recursion:
            dp[i,j] = grid[i,j] + min(dp[i-1,j], dp[i,j-1])
        Boundary:
            dp[0,0] = grid[0,0]
			dp[0,j] = grid[0,j] + dp[0,j-1] for j > 0
			dp[i,0] = grid[i,0] + dp[i-1,0] for i > 0
    
    */
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int n = grid.length, m = grid[0].length;
        int[] dp = new int[m];
        for(int i = 0; i < n; i++) {
            dp[0] += grid[i][0];
            for(int j = 1; j < m; j++) {
                dp[j] = grid[i][j] + (i == 0 ? dp[j-1] : Math.min(dp[j], dp[j-1])); // Be careful here
            }
        }
        return dp[m-1];
    }
}