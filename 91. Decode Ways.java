/*MORE

A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.

*/

public class Solution {
    /*
        DP O(n) time O(1) space
        dp[i] number of decodings ending at i position of string s
        when s[i] == '0', dp[i] = 0
        else dp[i] = dp[i-1]
        when 10 <= s[i-1:i] <= 26, dp[i] += dp[i-2]
        
    */
    public int numDecodings(String s) {
        if(s.length() == 0) return 0;
        int pre = 1, cur = s.charAt(0) == '0' ? 0 : 1;  // Boundary
        for(int i = 1; i < s.length(); i++) {
            int tmp = cur;
            int x = Integer.valueOf(s.substring(i - 1, i + 1));
            if(s.charAt(i) == '0') cur = 0;
            if(x <= 26 && x >= 10) cur += pre;
            pre = tmp;
        }
        return cur;
    }
}