/* Minimum insertions to form a palindrome
	Given a string, find the minimum number of characters to be inserted to convert it to palindrome.

	Before we go further, let us understand with few examples:
		ab: Number of insertions required is 1. bab
		aa: Number of insertions required is 0. aa
		abcd: Number of insertions required is 3. dcbabcd
		abcda: Number of insertions required is 2. adcbcda which is same as number of insertions in the substring bcd(Why?).
		abcde: Number of insertions required is 4. edcbabcde

*/

/*
	dp[i][j] = min insertions needed to transform the string[i:j] into palindrome
	dp[i][j] = dp[i+1][j-1] if s[i] == s[j]
			 = min(dp[i][j-1], dp[i+1][j]) + 1, otherwise
	
	Boundary: dp[i][j] = 0 when i >= j 
	Fill dp from left top to right bottom
	
	Time & Space O(N^2)

*/
public static int minPalidrome(String str) {
	int[][] dp = new int[str.length()][str.length()];
	for(int i = dp.length - 1; i >= 0 ; i--) {
		for(int j = i + 1; j < dp.length; j++) {
			if(str.charAt(i) == str.charAt(j)) dp[i][j] = dp[i+1][j-1];
			else dp[i][j] = Math.min(dp[i][j-1], dp[i+1][j]) + 1;
		}
	}
	return dp[0][str.length() - 1];
}