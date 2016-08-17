/*
Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.

Note: m and n will be at most 100.
*/

public class Solution {
	/*
		DP almost the same as #62 Unique Paths
		Just make sure don't forget to update dp[j] as 0 when there is a obstacle
		and the boundary condition check.
	*/
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) return 0;
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        int[] dp = new int[m];
        for(int i = 0; i < n; i++) {
			// Boundary check
            if(obstacleGrid[i][0] == 0 && (i == 0 || dp[0] != 0)) dp[0] = 1;
            else dp[0] = 0;
            for(int j = 1; j < m; j++) {
                if(obstacleGrid[i][j] == 0) dp[j] += dp[j-1];
                else dp[j] = 0;
            }
        }
        return dp[m-1];
    }
}