/*
Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.
Note: You may assume the string contain only lowercase letters.


*/

public class Solution {
    public int firstUniqChar(String s) {
        int[] counts = new int[26];
        int uniqueIndex = 0;
        for(int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
            while(uniqueIndex < s.length() && counts[s.charAt(uniqueIndex) - 'a'] >= 2) uniqueIndex++;
        }
        return uniqueIndex == s.length() ? -1 : uniqueIndex;
    }
}