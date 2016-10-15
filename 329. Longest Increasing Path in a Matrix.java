/*

Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

nums = [
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Return 4
The longest increasing path is [1, 2, 6, 9].

Example 2:

nums = [
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Return 4
The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
*/


/*
	DP + dfs.
	It is very easy to find that brute force way will have a lot of duplicate calculations.
	And there exists a subproblem optimal structure.
	
	dp[i][j] = longest path length starting from [i,j]
	dp[i][j] = 1 + max(dp[x][y]) for all feasible neighbors [x,y]
				where matrix[x][y] > matrix[i][j]
	
	It is not easy to fill the dp matrix in certain direction.
	So we can use memorization way (top-down)
	
	Time O(m*n) Space O(m*n)
*/
public class Solution {
    final static int[][] dirs = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int n = matrix.length, m = matrix[0].length;
        int[][] dp = new int[n][m];
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                max = Math.max(max, dfs(matrix, dp, i, j));
            }
        }
        return max;
    }
    private int dfs(int[][] matrix, int[][] dp, int i, int j) {
        if(dp[i][j] != 0) return dp[i][j];
        dp[i][j] = 1;    // don't forget to initial here
        for(int[] dir : dirs) {
            int nextI = dir[0] + i, nextJ = dir[1] + j;
            if(nextI < 0 || nextI >= matrix.length || nextJ < 0 || nextJ >= matrix[0].length
                    || matrix[nextI][nextJ] <= matrix[i][j]) continue;
            dp[i][j] = Math.max(dp[i][j], 1 + dfs(matrix, dp, nextI, nextJ));
        }
        return dp[i][j];
    }
}