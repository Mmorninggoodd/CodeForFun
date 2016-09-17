/*

Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

For example:

Given "aacecaaa", return "aaacecaaa".

Given "abcd", return "dcbabcd".

*/

public class Solution {
	/*
		KMP based solution
		Time O(n) Space O(n)
		
		If we tried the latter solution, we know that we spend a lot of time to
		find longest substring palindrome starts from index 0.
		
		We can use KMP to find that palindrome in O(n):
		
		String: ababc
		
		Construct a new string: ababc#cbaba
		Then we build the prefix function array, where array[i] = the length of prefix = suffix in string[0:i]
		[0,0,1,2,0,0,0,0,1,2,3]
		
		So the last element of array is what we want, i.e. the longest substring palindrome starts from 0. (aba)
	*/
	public String shortestPalindrome(String s) {
		int[] table = constructTable(s + "#" + new StringBuilder(s).reverse());
		int len = table[table.length - 1];
		return new StringBuilder(s.substring(len)).reverse() + s.substring(0, len) + s.substring(len);
	}
	private int[] constructTable(String s) {
		int[] table = new int[s.length()];
		for(int i = 1; i < table.length; i++) {
			int len = table[i - 1];
			while(len > 0 && s.charAt(len) != s.charAt(i)) len = table[len - 1];  // shorten length
			if(s.charAt(len) == s.charAt(i)) len++;   // increase length
			table[i] = len;
		}
		return table;
	}
	/* LTE
		try to find largest substring palindrome that start from index 0.
		
		aacecaaa -> largest substring palindrome starts from 0 is aacecaa, so we just only reverse remaining part (a)
		and then append it in front of s.
		
		O(n^2) time O(1) space
	*/
    public String shortestPalindrome(String s) {
        int start = 0, end = s.length() - 1, bound = end;
        while(start < end) {
            if(s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
            }
            else {  // current s[0:bound] isn't palindrome, left shift bound
                start = 0;
                end = --bound;
            }
        }
        return new StringBuilder(s.substring(bound + 1)).reverse().append(s).toString();
    }
}