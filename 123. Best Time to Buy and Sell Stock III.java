/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

*/

public class Solution {
    /*
        buy1[i]: max profit when buying first stock until i
        sell[i]: max profit when selling first stock until i
        buy2[i]: max profit when buying second stock until i
        sell2[i]: max profit when selling second stock until i
        
        Transaction:
        buy1[i] = max(buy1[i-1], -price[i])  -- actually looking for lowest price
        sell1[i] = max(sell1[i-1], buy1[i-1] + price[i]) -- when use sell[i-1], it means previous selling point is better, i.e. getting more profit
        buy2[i] = max(buy2[i-1], sell1[i-1] - price[i])
        sell2[i] = max(sell2[i-1], buy2[i-1] + price[i])
        
        Boundary:
        buy1[0] = buy2[0] = -price[i]
        sell1[0] = sell2[0] = 0
        
        Note that if there is only one profitable transaction, then sell1[n] == sell2[n]
		Note2: using "if(a < b) a = b" would be faster than "a = Math.max(a,b)"
    */
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) return 0;
        int buy1 = -prices[0], buy2 = -prices[0], sell1 = 0, sell2 = 0;
        for(int price : prices) {
            sell2 = Math.max(sell2, buy2 + price);
            buy2 = Math.max(buy2, sell1 - price);
            sell1 = Math.max(sell1, buy1 + price);
            buy1 = Math.max(buy1, -price);
        }
        return sell2;
    }
}