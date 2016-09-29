/*

	Given two strings A, B. Check if A contains substring is anagram of B.

*/

/*
	Use a sliding window. Record counts of current window.
	Once counts are become zeros, then it has anagram.
	
	O(n) time O(1) space.
	
	Note that we can use Map. When map's size == 0, then it has a valid anagram.
*/
public static boolean isAnagram(String s, String p) {
	if(s.length() < p.length()) return false;
	if(p.length() == 0) return true;
	int[] counts = new int[26];
	for(char c : p.toCharArray()) counts[c - 'a']--;  // decrease count
	for(int i = 0; i < s.length(); i++) {
		counts[s.charAt(i) - 'a']++;  // increase count when add new char
		if(i >= p.length()) counts[s.charAt(i - p.length()) - 'a']--; 
		if(isValid(counts)) return true;
	}
	return false;
}
private static boolean isValid(int[] counts) {
	for(int count : counts) if(count != 0) return false;
	return true;
}