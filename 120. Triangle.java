/*

Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.

*/
public class Solution {
    /*
		DP O(n) time O(row) space
        dp[i,j] = min path sum from root to [i,j]
        dp[i,j] = min(dp[i-1,j-1], dp[i-1,j]) + val[i,j]
        boundary:
            dp[0,0] = root val
    */
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle.size() == 0) return 0;
        int[] dp = new int[triangle.size()];
        int min = Integer.MAX_VALUE;
        for(int row = 0; row < triangle.size(); row++) {
            List<Integer> curRow = triangle.get(row);
            for(int col = curRow.size() - 1; col >= 0; col--) {
                if(col == 0) dp[col] += curRow.get(col);
                else if(col == curRow.size() - 1) dp[col] = dp[col - 1] + curRow.get(col);
                else dp[col] = Math.min(dp[col - 1], dp[col]) + curRow.get(col);
                if(row == triangle.size() - 1 && min > dp[col]) min = dp[col];
            }
        }
        return min;
    }
}