/*
Implement strStr().

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

indexOf() search a needle in haystack.
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
		KMP
	*/
	
	/*
		 Also there is Robin-Karp or Boyer-Moore algorithm algorithm
	*/
	
}