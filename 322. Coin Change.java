/*

You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
coins = [1, 2, 5], amount = 11
return 3 (11 = 5 + 5 + 1)

Example 2:
coins = [2], amount = 3
return -1.

Note:
You may assume that you have an infinite number of each kind of coin.

*/

public class Solution {
    /*
        dp[n] = min coin changes for amount of n
        dp[i] = 1 + min(dp[i - coins[j]]) for all j
        
        Boundary: dp[0] = 0
        
        Time O(n*m) Space O(n)
        
		Note that actually it is possible to only use min(amount, max(coins)) space.
    */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for(int i = 1; i <= amount; i++) {
            for(int j = 0; j < coins.length; j++) {
                if(i - coins[j] >= 0 && dp[i - coins[j]] != -1) {
                    dp[i] = dp[i] == -1 ? dp[i - coins[j]] + 1 : Math.min(dp[i], dp[i - coins[j]] + 1);
                } 
            }
        }
        return dp[amount];
    }
}