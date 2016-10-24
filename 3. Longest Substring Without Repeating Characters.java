/*
Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

*/

/*
	Time and space Complexity O(N)
	One pass
	HashMap to store (char, last Index of this char) pairs
*/
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0, lastIndex = -1;
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                lastIndex = Math.max(map.get(c), lastIndex);
            }
            maxLength = Math.max(maxLength, i - lastIndex);
            map.put(c, i);
        }
        return maxLength;
    }
}

/*
	Maximum Sliding Window
	
	O(n) time O(1) space
*/
public int lengthOfLongestSubstring(String s) {
	int[] counts = new int[256];  // counts of each letter in current window
	int left = 0, maxLen = 0;
	for(int i = 0; i < s.length(); i++) {
		counts[s.charAt(i)]++;
		while(counts[s.charAt(i)] > 1) {  // contains more than one occurrence, then move left bound until valid
			counts[s.charAt(left++)]--;
		}
		maxLen = Math.max(maxLen, i - left + 1);
	}
	return maxLen;
}
