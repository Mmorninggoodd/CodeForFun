/*

Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true

*/

public class Solution {
	/*
		A space-saver DP method.
		dp[m,n] : true if first m chars of s (s[-1:m-1]) can be matched by pattern p[0:n]
		space O(n) time O(m*n)
	*/
	public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        if(m == 0 && n == 0) return true;
        if(n == 0) return false;
        boolean[] dp1 = new boolean[n];
        boolean[] dp2 = new boolean[n];
        for(int j = 1; j < n && p.charAt(j) == '*'; j += 2) dp2[j] = true;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(p.charAt(j) == '*' && 
                        ((j-2 >= 0 && dp1[j-2]) || 
                            (dp2[j] && isEqual(s.charAt(i), p.charAt(j-1)))))
                        dp1[j] = true;
                else if(((j == 0 && i == 0) || (j > 0 && dp2[j-1])) && isEqual(s.charAt(i), p.charAt(j)))
                    dp1[j] = true;
                else
                    dp1[j] = false; // to overwrite old value
            }
			// switch two rows
            boolean[] tmp = dp1;
            dp1 = dp2;
            dp2 = tmp;
			// To here, d2 is the newest dp row
        }
        return dp2[n-1];
    }
	
	/*
		A simple dp method.
		dp[m,n] : true if first m chars of s (s[-1:m-1]) can be matched by pattern p[0:n]
		space O(m*n) time O(m*n)
	*/
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        if(m == 0 && n == 0) return true;
        if(n == 0) return false;
        boolean[][] dp = new boolean[m+1][n];
        for(int j = 1; j < n && p.charAt(j) == '*'; j += 2) dp[0][j] = true;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(p.charAt(j) == '*') {
                    if((j-2 >= 0 && dp[i+1][j-2]) || 
                        (dp[i][j] && isEqual(s.charAt(i), p.charAt(j-1)))) 
                        dp[i+1][j] = true;
                }
                else if(((j == 0 && i == 0) || (j > 0 && dp[i][j-1])) && isEqual(s.charAt(i), p.charAt(j))) dp[i+1][j] = true;
            }
        }
        return dp[m][n-1];
    }
    private boolean isEqual(char s, char p) {
        return (p == '.' || s == p);
    }
}