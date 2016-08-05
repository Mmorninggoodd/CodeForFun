/*
Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.


*/

public class Solution {
	
	/*
		DP version time O(n)
		This version of DP doesn't need any assumption on the string, and any transformation.
	
	*/
	private static String LPS_version1(String s){
        if(s == null || s.length() == 0) return s;
        int n = s.length();
        int[][] Radius = new int[2][n+1]; // 0: even (center between i-1 and i), 1: odd

        int maxLen = 1, maxLeft = 0, maxRight = 0;
        for(int j = 0; j < 2; j++) {
            int rp = 0;
            for(int i = 1; i <= n;) {
                // expand centered at i
                while(i - rp - 1 >= 0 && i + j + rp < n
                        && s.charAt(i - rp - 1) == s.charAt(i + j + rp))
                    rp++;

                // update Radius for current center
                Radius[j][i] = rp;

                // Update max leng
                if(rp * 2 + j > maxLen) {
                    maxLen = rp * 2 + j;
                    maxLeft = i - rp - 1 + 1;
                    maxRight = i + rp + j - 1;
                }

                // try to use current coverage to update remaining part
                int k = 1;
                while(i - k >= 0 && Radius[j][i - k] != rp - k && k < rp) {
                    Radius[j][i + k] = Math.min(Radius[j][i - k], rp - k);
                    k++;
                }

                // Make sure rp is not negative and increment i
                rp = Math.max(rp - k, 0);
                i += k;
            }
        }
        return s.substring(maxLeft, maxRight + 1);
    }
	
	/*
		A fast normal version (but slow compared with DP version)
		Time O(n^2) Space O(n)
		Expand the boundaries when meet same characters in the center area,
		then expand when two sides' characters are the same.
		(Don't need to handle two cases of centering)
		
		Next time can skip center area when setting a new center.
	
	*/
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0) return s;
        int optLeft = 0, optRight = 0;
        for(int i = 0; i < s.length(); i++){
            int left = i, right = i;
            while(right + 1 < s.length() && s.charAt(right) == s.charAt(right + 1)){
                i++;
                right++;
            } 
            while(left > 0 && right < s.length() - 1 && s.charAt(left-1) == s.charAt(right+1)){
                left--;
                right++;
            }
            if(right - left > optRight - optLeft){
                optLeft = left;
                optRight = right;
            }
        }
        return s.substring(optLeft, optRight + 1);
    }
}