/*
There are n coins in a line. Two players take turns to take one or two coins from right side until there are no more coins left. The player who take the last coin wins.

Could you please decide the first play will win or lose?

*/
public boolean firstWillWin(int n) {
	return n % 3 != 0;
}
/*
There are n coins with different value in a line. Two players take turns to take one or two coins from left side until there are no more coins left. The player who take the coins with the most value wins.

Could you please decide the first player will win or lose?

Example Given values array A = [1,2,2], return true.

Given A = [1,2,4], return false.

*/
 /*
	DP O(n) time & space
	(space actually can be reduced to O(1))
	
	dp[i] = max coins you can gain more than the other player when you start from i.
	
	dp[i] = Math.max(values[i] - dp[i+1], values[i] + values[i+1] - dp[i+2])
	
	Boundary: dp[n - 1] = values[n - 1]
			  dp[n - 2] = values[n - 1] + values[n - 2]
 */
public boolean firstWillWin(int[] values) {
	int n = values.length;
	if(n < 3) return true;
	int[] dp = new int[n];
	dp[n - 1] = values[n - 1];
	dp[n - 2] = values[n - 1] + values[n - 2];
	for(int i = n - 3; i >= 0; i--) {
		dp[i] = Math.max(values[i] - dp[i+1], values[i] + values[i+1] - dp[i+2]);
	}
	return dp[0] > 0;
}


/*
There are n coins in a line. Two players take turns to take a coin from one of the ends of the line until there are no more coins left. The player with the larger amount of money wins.

Could you please decide the first player will win or lose?

Example
Given array A = [3,2,2], return true.

Given array A = [1,2,4], return true.

Given array A = [1,20,4], return false.

Follow Up Question:
If n is even. Is there any hacky algorithm that can decide whether first player will win or lose in O(1) memory and O(n) time?

*/
/*
	dp[i,j] = max coins you can gain more than the other player in [i,j]
	
	dp[i,j] = max(values[i] - dp[i+1,j], values[j] - dp[i,j-1])
	
	Boundary: dp[i,j] = values[i] when i == j
*/
public static boolean firstWillWin(int[] values) {
	int n = values.length;
	if(n < 3) return true;
	int[][] dp = new int[n][n];
	for(int len = 1; len <= n; len++) {
		for(int start = 0; start + len <= n; start++) {
			int end = start + len - 1;
			if(start == end) dp[start][end] = values[start];
			else dp[start][end] = Math.max(values[start] - dp[start+1][end], values[end] - dp[start][end-1]);
		}
	}
	return dp[0][n-1] > 0;
}