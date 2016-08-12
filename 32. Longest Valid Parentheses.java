/*
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
*/

public class Solution {
	/*
		DP O(n) solution.
		dp[i] = max valid length ending at index i
		Only consider two major cases: ...() and ...((...))
	*/
    public static int longestValidParentheses(String s) {
        int[] dp = new int[s.length()]; // dp[i] = max valid length ending at index i
        int maxLen = 0;
        for(int i = 1; i < s.length(); i++) {
            if(s.charAt(i) == ')') {
                if(s.charAt(i - 1) == '(') {  // case: ...()
                    dp[i] = 2 + (i - 2 >= 0 ? dp[i - 2] : 0);  // 2: () + dp[i - 2]: the one before ()
                }
                else if(i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {  // case: ...((...))
                    dp[i] = 2 + dp[i - 1];  // 2: ...(...) + dp[i - 1]: 
                    dp[i] += (i - dp[i] > 0 ? dp[i - dp[i]] : 0);
                }
            }
            if(dp[i] > maxLen) maxLen = dp[i];
        }
        return maxLen;
    }
}