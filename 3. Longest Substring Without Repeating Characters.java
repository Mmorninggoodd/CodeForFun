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
	Similar Solution, but using an array to store index of char
	Faster, but we need to know the range of character
*/
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0, lastIndex = -1;
        int[] loc = new int[256]; // ASCII, in order to avoid initialization of -1, loc starts from 1
		// Arrays.fill(loc, -1);
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            lastIndex = Math.max(loc[(int) c] - 1, lastIndex);
            maxLength = Math.max(maxLength, i - lastIndex);
            loc[(int) c] = i + 1;
        }
        return maxLength;
    }
}
