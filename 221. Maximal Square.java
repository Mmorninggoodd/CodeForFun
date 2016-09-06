/*
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 4.
*/

public class Solution {
    /* 9ms 93.49%
		DP O(m) space, O(n*m) time
		
        dp[i,j] = max length of side of the square whose right bottom corner located at (i,j)
        
        when matrix[i,j] == 0, dp[i,j] = 0
        when matrix[i,j] == 1, dp[i,j] = min(dp[i-1,j-1], dp[i-1,j], dp[i,j-1]) + 1
        
        Boundary:
        dp[i,j] = matrix[i,j], for i = 0 or j = 0
        
    */
    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int[] dp = new int[matrix[0].length];
        int max = 0;
        for(int i = 0, pre = dp[0]; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                int tmp = dp[j];
                if(matrix[i][j] == '0') dp[j] = 0;
                else if(i == 0 || j == 0) dp[j] = 1;
                else dp[j] = Math.min(pre, Math.min(dp[j], dp[j-1])) + 1;
                pre = tmp; // dp[i-1,j-1]
                if(dp[j] > max) max = dp[j];
            }
        }
        return max * max;
    }
}