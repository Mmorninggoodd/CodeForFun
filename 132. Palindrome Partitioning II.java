/*

Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.

*/

public class Solution {
    /* 24ms 80%
        If use solution of 131 which find all palindrome partitions, it would take O(2^n) time.
        Here we only need the number of min cut, so it would be a overkill.
        
		DP O(n^2) time O(n) space
		
        dp[i] = min cuts for first i characters of string s
        dp[i] = min(dp[j]) + 1 for all j < i when s[j + 1, i] is a palindrome
        
        Boundary:
        dp[0] = -1
    */
    public int minCut(String s) {
        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = -1;
        for(int i = 0; i < s.length(); i++) {
            for(int left = i, right = i; left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right); left--, right++) {
                dp[right + 1] = Math.min(dp[right + 1], dp[left] + 1); // odd number palindrome
            }
            for(int left = i - 1, right = i; left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right); left--, right++) {
                dp[right + 1] = Math.min(dp[right + 1], dp[left] + 1);  // even number
            }
        }
        return dp[s.length()];
    }
}