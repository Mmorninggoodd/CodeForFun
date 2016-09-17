/*
edit distance

Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character

*/

public class Solution {
	/* 10ms 94%
		O(m*n) time O(m) space DP
		
        dp[i,j] : min distance between first i chars of word1 and first j chars of word2
                Note that dp[0,j] means none of word1's chars and j word2's chars
        Recursion:
            If word1[i-1] != word2[j-1]:
				dp[i,j] = 1 + min(dp[i-1, j-1] (replace), dp[i, j-1] (insert char into word2 or delete char of word1), dp[i-1, j] (similar))
            else:
				dp[i,j] = dp[i-1,j-1]
        Boundary:
            dp[0,j] = j
            dp[i,0] = i
            
    */
    public int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[] dp = new int[m + 1];
        for(int i = 0; i <= n; i++) {
            int pre = i - 1; // store dp[i-1, j-1]
            dp[0] = i;
            for(int j = 1; j <= m; j++) {
                int tmp = dp[j];
                if(i == 0)
                    dp[j] = j;
                else if(word1.charAt(i-1) != word2.charAt(j-1))
                    dp[j] = 1 + Math.min(pre, Math.min(dp[j-1], dp[j]));
                else dp[j] = pre;
                pre = tmp; 
            }
        }
        return dp[m];
    }



	/* LTE
		Backtracking Method. Exponential time.
	*/
    private int min;
    public int minDistance(String word1, String word2) {
        min = Math.max(word1.length(), word2.length());
        minDistance(word1, word2, 0, 0, 0);
        return min;
    }
    private void minDistance(String word1, String word2, int i1, int i2, int count) {
        if(count >= min) return;
        int n1 = word1.length(), n2 = word2.length();
        for(; i1 < n1 && i2 < n2 && word1.charAt(i1) == word2.charAt(i2); i1++, i2++);
        if(i1 == n1 || i2 == n2) {
            count += Math.max(n1 - i1, n2 - i2);
            if(min > count) min = count;
            return;
        }
        
        minDistance(word1, word2, i1 + 1, i2 + 1, count + 1); // replace word1 or word2 (don't need to really replace)
        // insert or delete (equivalent)
        minDistance(word1, word2, i1, i2 + 1, count + 1);  // insert into word1 or delete char in word2
        minDistance(word1, word2, i1 + 1, i2, count + 1);  // insert into word2 or delete char in word1
    }
}