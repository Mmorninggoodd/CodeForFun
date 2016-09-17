/*
Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.


*/

public class Solution {
	
	/*
		Manacher Algorithm
		DP version time O(n)
		This version of DP doesn't need any assumption on the string, and any transformation.
	
	*/
	private static String LPS_version1(String s){
        int[][] R = new int[2][s.length()];  // j == 1: odd, j == 0, even
        int max = 0, n = s.length(), start = 0;
        for(int j = 0; j < 2; j++) {
            int r = 0;
            for(int i = 0; i < n;) {
				// Try to expand at index i
                while(i - r - j >= 0 && i + r + 1 < n && s.charAt(i - r - j) == s.charAt(i + r + 1)) r++;
                R[j][i] = r;
                
                if(2 * r + j > max) {  // Update longest substring palindrome
                    max = 2 * r + j;
                    start = i - r - j + 1;
                }
                
                int k = 1;
                while(k < r && r - k != R[j][i - k]) {  // Try to reuse result of left half of current coverage [i - r, i + r]
                    R[j][i + k] = Math.min(R[j][i - k], r - k);
                    k++;
                }
                r = Math.max(0, r - k);  // If stop early (r - k == R[j][i - k]), can reuse r - k next time
                i += k;
            }
        }
        return n == 0 ? "" : s.substring(start, start + max);
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