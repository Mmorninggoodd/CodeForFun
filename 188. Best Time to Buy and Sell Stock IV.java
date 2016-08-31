/*

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

*/

public class Solution {
    /* 4ms 80%
        Just generalization of 123. Best Time to Buy and Sell Stock III and combine with 122. Best Time to Buy and Sell Stock II
        
        DP + greedy
        
        buy[i][j]: max profit when buying i stock until time j
        sell[i][j]: max profit when selling i stock until time j
        
        transaction:
        buy[0][j] = max(buy[0][j-1], - price[j])
        buy[i][j] = max(buy[i][j-1], sell[i-1][j-1] - price[j])
        sell[i][j] = max(sell[i][j-1], buy[i][j-1] + price[j])
        
        Boundary:
        buy[i][0] = - price[0] for all i
        sell[i][0] = 0 for all i
    */
    public int maxProfit(int k, int[] prices) {
        if(prices.length == 0 || k == 0) return 0;
        if(k > prices.length / 2) return greedySolve(prices);
        int[] buy = new int[k];
        int[] sell = new int[k];
        Arrays.fill(buy, -prices[0]);
        for(int price : prices) {
            for(int i = k - 1; i >= 0; i--) {
                if(sell[i] < buy[i] + price) sell[i] = buy[i] + price;
                if(i != 0 && buy[i] < sell[i - 1] - price) buy[i] = sell[i - 1] - price;
                if(i == 0 && buy[i] < - price) buy[i] = - price;
            }
        }
        return sell[k - 1];
    }
    private int greedySolve(int[] prices) {
        int profit = 0;
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] - prices[i - 1] > 0) profit += prices[i] - prices[i - 1];
        }
        return profit;
    }
}