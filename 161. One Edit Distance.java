/*

Given two strings S and T, determine if they are both one edit distance apart.

*/

/*
	Use DP solution of 72. Edit Distance would be inefficient O(n) space and O(m*n) time

	So we can just one pass here. Time O(min(m, n)), space O(1)
	Try to find first different char, then we can compare remaining strings according to three cases.
	If cannot find first different char, then see if difference of lengths is 1.
*/
public boolean isOneEditDistance(String s, String t) {
	int minLen = Math.min(s.length(), t.length());
	for(int i = 0; i < minLen; i++) {
		if(s.charAt(i) != t.charAt(i)) {
			if(s.length() == t.length()) return s.substring(i + 1).equals(t.substring(i + 1)); // replace
			else if(s.length() > t.length()) return s.substring(i + 1).equals(t.substring(i)); // insert or delete
			else return s.substring(i).equals(t.substring(i + 1));
		}
	}
	return Math.abs(s.length() - t.length()) == 1;  // insert or delete last char
}