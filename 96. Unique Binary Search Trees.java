/*

Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
   
*/

public class Solution {
    /*
        dp[i]: number of unique binary search trees using i unique values
        dp[i] = sum(dp[j] * dp[i-j-1]) for 0 <= j <= i-1
        
        Boundary: dp[0] = 1
    */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= i - 1; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }
}