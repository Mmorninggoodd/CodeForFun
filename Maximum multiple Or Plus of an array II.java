/*
	Only can add * or +

*/

/*
	dp[i] = max value from first i number of arr

	dp[i] = max( prod(j,i) + dp[j - 1]) for all 0 < j <= i

	dp[0] = 0
	
	Time O(n^2) Space O(n)

 */
public static int maxProductOrPlus(int[] arr) {
	int[] dp = new int[arr.length + 1];
	for(int i = 1; i <= arr.length; i++) {
		int prod = 1;
		for(int j = i; j > 0; j--) {
			prod *= arr[j - 1];
			dp[i] = Math.max(dp[i], prod + dp[j - 1]);
		}
	}
	return dp[dp.length - 1];
}