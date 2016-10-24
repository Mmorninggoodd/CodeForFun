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
        match[i,j] = true if first i char of s can be matched by first j char of p
        
        if p[j-1] == '*', match[i, j] = match[i-1, j] || match[i, j-1] 
        else, match[i, j] = match[i-1, j-1] && s[i-1] == p[j-1]
        
        Boundary: match[0,0] = true, match[0, j] = false when j > 0
                  match[0, j + 1] = match[0, j] && p[j] == '*'
    */
    public static boolean isMatch(String s, String p) {
        int n = s.length(), m = p.length();
        if(m == 0) return n == 0;
        boolean[] match = new boolean[m + 1];
        for(int i = 0; i < m && p.charAt(i) == '*'; i++) match[i + 1] = true;
        match[0] = true;
        for(int i = 1; i <= n; i++) {
            boolean pre = match[0];  // pre is match[i-1,j-1]
            match[0] = false;   // update to match[i,0] = false, when i > 0
            for(int j = 1; j <= m; j++) {
                boolean tmp = match[j];
                if(p.charAt(j-1) == '*') match[j] = match[j] || match[j-1];
                else match[j] = pre && (p.charAt(j-1) == '?' || p.charAt(j-1) == s.charAt(i-1));
                pre = tmp;
            }
        }
        return match[m];
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