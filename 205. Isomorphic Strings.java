/*

Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

For example,
Given "egg", "add", return true.

Given "foo", "bar", return false.

Given "paper", "title", return true.

Note:
You may assume both s and t have the same length.
*/

public class Solution {
	
	/*
		use two hashmap O(n) space & time
	*/
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for(Integer i = 0; i < s.length(); i ++) {   // Note that here using Integer, otherwise values returned by map will be compared with their addresses not values. (If using equals, we need to check null as well)
            if(map1.put(s.charAt(i), i) != map2.put(t.charAt(i), i)) return false;
        }
        return true;
    }
	
	/*
		If we need the range of character ASCII (0~256) though char is 16-bit
	*/
	public boolean isIsomorphic(String s1, String s2) {
        int[] m = new int[512];
        for (int i = 0; i < s1.length(); i++) {
            if (m[s1.charAt(i)] != m[s2.charAt(i)+256]) return false;
            m[s1.charAt(i)] = m[s2.charAt(i)+256] = i+1;
        }
        return true;
    }
}