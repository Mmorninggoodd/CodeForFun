/*
Word break
Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".

*/

public class Solution {
	/*
		A Time Limit Exceeded backtracking solution. Exponential time complexity.
	*/
    public boolean wordBreak(String s, Set<String> wordDict) {
        return wordBreak(s, wordDict, 0);
    }
    private boolean wordBreak(String s, Set<String> wordDict, int start) {
        if(start == s.length()) return true;
        for(int i = s.length() - 1; i >= start; i--) {
            if(wordDict.contains(s.substring(start, i + 1)) && wordBreak(s, wordDict, i + 1)) return true; 
        }
        return false;
    }
	
	/*
        Time O(n^2) Space O(n)
        DP: dp[i] = whether first i chars of s (s[0:i-1]) can be segmented into words
        
        dp[i] = true if any of (s[j, i - 1] is a word and dp[j] is true) for 0 <= j <= i - 1
        
        boundary:
        dp[0] = true
    */
    public boolean wordBreak(String s, Set<String> wordDict) {
        if(s.length() == 0) return true;
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for(int i = 1; i <= s.length(); i++) {
            for(int j = 0; j < i; j++) {
                if(dp[j] && wordDict.contains(s.substring(j, i))) dp[i] = true;
            }
        }
        return dp[s.length()];
    }
}