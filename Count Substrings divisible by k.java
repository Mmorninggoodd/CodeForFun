/*

Count the number of substrings divisible by k
(Not necessary continuous substring)
*/

/*
	dp[i, r] = number of substrings from first i chars of string whose remainder is r after divided by k
	
	dp[i, r] = dp[i - 1, r] + sum(dp[i - 1, j] where j satisfy (j*10 + the ith number) % k == r

	Boundary:
	dp[0, 0] = 1 (empty string is divisible by k)
	dp[0, j] = 0 for j > 0
	
	Final result is dp[s.length(), 0] - 1
	
	Complexity analysis:
	Each time update dp[i,r] only takes O(1)
	So to update dp, we needs O(k*n) time.
	
	Time O(k*n), space O(k)
	
	When k is small, we can see it as time O(n), space O(1)
*/

public static int numSubstringDivisibleByK(String s, int k) {
	int[] count = new int[k];
	count[0] = 1;
	for(char c : s.toCharArray()) {
		int[] tmp = new int[k];
		for(int i = 0; i < k; i++) {
			tmp[i] = count[i];
			tmp[(10 * i + c - '0') % k] += count[i];
		}
		count = tmp;
	}
	return count[0] - 1;
}