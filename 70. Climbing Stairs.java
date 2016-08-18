/*
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
*/

public class Solution {
	/*
		A typical DP O(n) time O(1) space problem
		dp[i] = # of distinct ways to i
		dp[i] = dp[i-1] + dp[i-2]
		Boundary: dp[0] = 1
	
	*/
    public int climbStairs(int n) {
        int pre2 = 0, pre1 = 1, cur = 1; // pre2: dp[i-2], pre1: dp[i-1], cur: dp[i]
        for(int i = 0; i < n; i++) {
            cur = pre1 + pre2;
            pre2 = pre1;
            pre1 = cur;
        }
        return cur;
    }
}