/*

Count the number of continuous substrings divisible by k

*/

/*
	Almost the same as "Count the number of substrings divisible by K" in which problem, substrings can be non-continuous.
	
	dp[i, r] = number of continuous substrings that ending at index i divisible by k
	
	dp[i, r] = (digit % k == r ? 1 : 0) + sum(dp[i - 1, j]) where j satisfy (j*10 + the ith number) % k == r   // only difference here
	
	(digit % k == r ? 1 : 0) means substrings that starting and ending at i
	sum(dp[i - 1, j]) means substrings starting before i but ending at i - 1.
	
	Note that it would be much easier to update in this way:
	for(digit in string) {   // from left to right
		dp[i, digit % k] = 1 // substring starting at i
		for(j in range[0-6]) {
			dp[i, (10*j + digit) % k] += dp[i-1, j];
		}
	}
	
	
	Boundary:
	dp[-1, r] = 0
	
	Final answer is sum(dp[i,0]) for all i >= 0

	Time O(k*n) Space O(k)

*/

public static int numContinuousSubstringDivisibleByK(String s, int k) {
	int[] count = new int[k];
	int total = 0;
	for(char c : s.toCharArray()) {
		int[] tmp = new int[k];
		tmp[(c - '0') % k]++;   // starting at i  (one difference)
		for(int i = 0; i < k; i++) {   // starting before and at i - 1
			//tmp[i] = count[i];    // another difference between non-continuous and continuous
			tmp[(10 * i + c - '0') % k] += count[i];
		}
		count = tmp;
		total += count[0];  // add all satisfied substrings
	}
	return total;
}


/*

Here is a bruce force way
Time O(n^2) Space O(1)
*/

public static int bruceForce(String s, int k) {
	int total = 0;
	for(int i = 0; i < s.length(); i++) {  // start index
		int remainder = 0;
		for(int j = i + 1; j <= s.length(); j++) {  // end index (exclusive)
			remainder = (remainder * 10 + s.charAt(j-1) - '0') % k;  // avoid overflow
			if(remainder == 0) total++;
		}
	}
	return total;
}

