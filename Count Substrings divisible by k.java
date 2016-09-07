/*

Count the number of substrings divisible by k
(Not necessary continuous substring)
*/

/*
	dp[i, r] = number of substrings from first i chars of string whose remainder is r after divided by k
	
	dp[i, r] = dp[i - 1, r] + sum(dp[i - 1, j] where j satisfy (j*10 + the ith number) % k == r
	Here we use a helper array to get the index j faster:
	We add j into indexes[i] when (10*j) % k == i
	So when we are searching for j such that (j*10 + digit) % k == i, 
	which implies (j*10) % k = (i - digit) % k,
	we can just use indexes[(i - digit) % k] to get those valid indexes.
	
	Boundary:
	dp[0, 0] = 1 (empty string is divisible by k)
	dp[0, j] = 0 for j > 0
	
	Final result is dp[s.length(), 0] - 1
	
	Complexity analysis:
	Each time update dp[i,r] only takes amortized O(1), since each possible remainder only exists once in the helper array.
	So to update dp, we needs O(k*n) time. To create the helper array, it takes O(k^2） time. 
	
	Time O(k*n + k^2), space O(k)
	
	When k is small, we can see it as time O(n), space O(1)
	
	Note that Java can return negative remainder, so we just use (a - b % k + k) % k to get positive remainder.
*/

public static int numSubstringDivisibleByK(String s, int k) {
	// Build helper array -- indexes
	ArrayList<Integer>[] indexes = new ArrayList[k];
	for(int i = 0; i < k; i++) {
		indexes[i] = new ArrayList<>();
		for(int j = 0; j < k ; j++) {
			if((j * 10) % k == i) indexes[i].add(j);
		}
	}
	// Update dp array -- count
	int[] count = new int[k];
	count[0] = 1;
	for(char c : s.toCharArray()) {
		int[] tmp = new int[k];
		for(int i = 0; i < k; i++) {
			tmp[i] = count[i];
			for(Integer index : indexes[(i - (c - '0') % k + k) % k])
				tmp[i] += count[index];
		}
		count = tmp;
	}
	return count[0] - 1;
}