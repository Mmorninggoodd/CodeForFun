/*
Implement strStr().

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
*/

public class Solution {
	/*
		Brute Force. Time O(m*n) Space O(1)
	*/
    public int strStr(String haystack, String needle) {
        for(int i = 0; i <= haystack.length() - needle.length(); i++) {
            if(haystack.startsWith(needle, i)) return i;
        }
        return -1;
    }
	/*
		Also there are KMP and Robin-Karp algorithms.
		Not necessary to learn them to prepare interview.
	*/
	
}