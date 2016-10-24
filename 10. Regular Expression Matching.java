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
        match[i,j] = true when first i chars of s can be matched by first j chars of p
        
        if p[j - 1] == '*': match[i, j] = match[i, j - 2] (not using x* at all) || (match[i - 1, j] && s[i - 1] == p[j - 2]) (used * to match)
        else: match[i, j] = match[i - 1, j - 1] && (s[i - 1] == p[j - 1] || p[j - 1] == '.')
        
        Boundary:
            match[0,0] = true
			match[0,j] = true when match[0,j-2] && p[j-1] == '*'
    */
    public static boolean isMatch(String s, String p) {
        int n = s.length(), m = p.length();
        boolean[] match = new boolean[m + 1];
        match[0] = true;
        for(int j = 1; j < m && p.charAt(j) == '*'; j += 2) match[j + 1] = true;
        for(int i = 1; i <= n; i++) {
            boolean pre = match[0];
            match[0] = false;   // update match[i,0] = false when i > 0
            for(int j = 1; j <= m; j++) {
                boolean tmp = match[j];   // match[i-1,j-1]
                if(p.charAt(j-1) == '*') match[j] = match[j-2] || (match[j] && (s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.'));
                else match[j] = pre && (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.');
                pre = tmp;
            }
        }
        return match[m];
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