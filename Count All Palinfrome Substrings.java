/*
	Count all palindrome substrings. (continuous substring)
*/

/*
	Manacher's algorithm O(n) time & space
*/
public static int countPalindromes(String s) {
	int n = s.length(), count = n;  // add single chars
	if(n == 0) return 0;
	int[][] R = new int[2][n + 1];
	s = "@" + s + "#";

	for(int j = 0; j < 2; j++) {
		int rp = 0;
		R[j][0] = 0;
		for(int i = 1; i <= n;) {
			while(s.charAt(i - rp - 1) == s.charAt(i + j + rp)) rp++;
			R[j][i] = rp;
			int k = 1;
			while((R[j][i - k] != rp - k) && (k < rp)) {
				R[j][i + k] = Math.min(R[j][i - k], rp - k);
				k++;
			}
			rp = Math.max(rp - k, 0);
			i += k;
		}
		for(int r : R[j]) count += r;  // count
	}
	return count;
}

/*
	DP O(n^2) space & time
*/
public static int countPalindromes2(String s) {
	int n = s.length(), count = n;
	boolean[][] dp = new boolean[n][n];
	for(int j = 0; j < n; j++) {
		dp[j][j] = true;
		for(int i = j - 1; i >= 0; i--) {
			if((dp[i+1][j-1] || j == i + 1) && s.charAt(i) == s.charAt(j)) {
				dp[i][j] = true;
				count++;
			}
		}
	}
	return count;
}