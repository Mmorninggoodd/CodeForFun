/*

Write a function to find the longest common prefix string amongst an array of strings.

*/


public class Solution {
	
	
	/*
		First sort string array, then only use the first one and the last one to get LCP
		Time O(n*m*lgn)
	*/
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) return "";
        Arrays.sort(strs);
        int length = 0, max = Math.min(strs[0].length(), strs[strs.length - 1].length());
        while(length < max && strs[0].charAt(length) == strs[strs.length - 1].charAt(length)) length++;
        return strs[0].substring(0, length);
    }
	
	/*
		Brute force
		Time O(n*m)
	*/
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) return "";
        int len = strs[0].length();
        for(String str : strs) {
            if(str.length() < len) len = str.length();
            int i = 0;
            for(; i < len && str.charAt(i) == strs[0].charAt(i); i++);
            len = i;
        }
        return strs[0].substring(0, len);
    }
}