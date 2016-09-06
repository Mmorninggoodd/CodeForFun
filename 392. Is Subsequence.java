/*
Given a string s and a string t, check if s is subsequence of t.

You may assume that there is only lower case English letters in both s and t. t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:
s = "abc", t = "ahbgdc"

Return true.

Example 2:
s = "axc", t = "ahbgdc"

Return false.

*/

public class Solution {
    /* 53ms
        Two pointers solution.
    */
    public boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        while(i < s.length() && j < t.length()) {
            if(s.charAt(i) == t.charAt(j)) i++;  // only advance when find same char
            j++;
        }
        return i == s.length();
    }
	/* 2ms
		Use indexOf(char, startIndex)
	*/
	public boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        for(; i < s.length() && j < t.length(); i++, j++) {
            j = t.indexOf(s.charAt(i), j);
            if(j == -1) return false;  // cannot find s.charAt(i)
        }
        return i == s.length(); // make sure it advance to s.length(), other fails case "abc" ""
    }
}
