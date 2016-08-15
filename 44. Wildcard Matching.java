/*
Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false
*/

public class Solution {
    /*
		17ms 58%
		
        Similar with #10 Regular Expression Matching
        No true linear complexity solution for this kind of problem.
        
        Time O(m*n) Space O(n) where n = s.length(), m = p.length()
        dp[i][j]: true if s[0:i-1] can be matched by p[0:j-1]
        if p[i-1] == '*' && any(dp[k][j-1]) for k <= i, then dp[i][j] = true 
        if (p[i-1] == '?' || s[i-1] = p[i-1]) && dp[i-1][j-1], then dp[i][j] = true
        
    */
    public boolean isMatch(String s, String p) {
		
		// This part is for speedup
		int n = s.length(), m = p.length();
        int count = 0;
        for(int i = 0; i < m; i++) if(p.charAt(i) == '*') count++;
        if((count == 0 && m != n) || (m - count > n)) return false;
		
		// Below are almost the same as DP solution of #10
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for(int j = 1; j <= m; j++) {
            boolean flag = false; // any(dp[k][j-1]) for k <= i
            boolean pre = false; // store dp[i-1][j-1]
            for(int i = 0; i <= n; i++) {  // note that start from 0
                boolean cur = dp[i];
                flag |= dp[i];
                if(p.charAt(j - 1) == '*' && flag) dp[i] = true;
                else if(i != 0 && (p.charAt(j - 1) == '?' || p.charAt(j - 1) == s.charAt(i - 1)) && pre) dp[i] = true;
                else dp[i] = false;
                pre = cur;
            }
        }
        return dp[n];
    }
	
	/*
		6ms 71%
		Backtracking solution which is also time O(m*n) but space O(1)
		
		Note 1: Many people say this is linear complexity, however, a simple example proves it is O(m*n):
			s = "aaaaaaaaaaaaaaaaaaaa"
			p = "*aaaaaaaaaaaaaaaaaab"
		Note 2: This solution will fail in the following cases:
			s = "a*a"
			p = "a*"
			It will return false, while correct answer is true.
		So this solution is NOT recommended, although it pass LC OJ right now, it is because test cases are not comprehensive.
	
	*/
	public boolean isMatch(String str, String pattern) {
        int s = 0, p = 0, match = 0, astroidIdx = -1;            
        while (s < str.length()){
            // advancing both pointers
            if (p < pattern.length()  && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))){
                s++;
                p++;
            }
            // * found, only advancing pattern pointer
            else if (p < pattern.length() && pattern.charAt(p) == '*'){
                astroidIdx = p;
                match = s;
                p++;
            }
			// last pattern pointer was *, advancing string pointer
            else if (astroidIdx != -1){
                p = astroidIdx + 1;
                match++;
                s = match;
            }
			//current pattern pointer is not *, last pattern pointer was not *
			//characters do not match
            else return false;
        }
        
        //check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*')
            p++;
        
        return p == pattern.length();
    }
}