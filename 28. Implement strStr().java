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
		Construct table takes O(k) time and O(k) space
		Match process tables O(n) time.
		So Time O(k+n), Space O(k)
	*/
	public int strStr(String haystack, String needle) {
        if(haystack.length() < needle.length()) return -1;
        if(needle.length() == 0) return 0;
        int[] table = constructTable(needle);
        for(int i = 0, j = 0; i < haystack.length();) {
            if(haystack.charAt(i) == needle.charAt(j)) {  // match, then advance both pointers
                i++;
                j++;
                if(j == needle.length()) return i - needle.length(); // found
            }
            else if(j == 0) i++;  // start points are not matched
            else j = table[j - 1];  // go back to last matched point
        }
        return -1;
    }
    private int[] constructTable(String needle) {
        int[] table = new int[needle.length()];
        for(int i = 1; i < table.length; i++) {
            int len = table[i - 1];
            while(len > 0 && needle.charAt(len) != needle.charAt(i)) len = table[len - 1];
            if(needle.charAt(i) == needle.charAt(len)) len++;
            table[i] = len;
        }
        return table;
    }
	/*
		 Also there is Robin-Karp or Boyer-Moore algorithm algorithm
	*/
	
}