/*

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

*/

public class Solution {
	/*
		Assume we can purchase at the point that start to increase, and sell it at the point that it stops increasing.
		
		So just add up all increasing consecutive prices' differences
	*/
    public int maxProfit(int[] prices) {
        int profit = 0;
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] - prices[i-1] > 0) profit += prices[i] - prices[i-1];
        }
        return profit;
    }
}