/*

Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.

*/

public class Solution {
	/*
		Similar with #30 Substring with Concatenation of All Words
		
		Use a moving window of a non-fixed size.
		
		A good reference is:
		https://discuss.leetcode.com/topic/30941/here-is-a-10-line-template-that-can-solve-most-substring-problems
	
	*/
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        int count = t.length(), start = 0, len = Integer.MAX_VALUE;
        for(char c : t.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1); // store (char, # occurrence) pairs
        for(int slow = 0, fast = 0; fast < s.length(); fast++) {
            char c = s.charAt(fast);
            if(map.containsKey(c)) {  // record occurrence of char in current window
                int val = map.get(c) - 1;
                map.put(c, val);
                if(val >= 0) count--;
            }
            if(count == 0) { // Form a valid window, try to advance slow
				// only stop when this char is necessary (i.e. it belongs to string t and occurrence in current window should not exceed t)
                while(!map.containsKey(s.charAt(slow)) || map.get(s.charAt(slow)) < 0) {
                    if(map.containsKey(s.charAt(slow))) map.put(s.charAt(slow), map.get(s.charAt(slow)) + 1); // decrease occurrence
                    slow++;
                }
                if(fast - slow + 1 < len) { // update window size
                    start = slow; 
                    len = fast - slow + 1;
                }
				// pop out front element from current window
                map.put(s.charAt(slow), map.get(s.charAt(slow)) + 1);
                count++;
                slow++;
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}