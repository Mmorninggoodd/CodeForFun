/*
Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

For example, 
Given s = "Hello World",
return 5.
*/

public class Solution {
	/*
		One of the easiest problems.
		Time O(n)
	*/
    public int lengthOfLastWord(String s) {
        int len = 0, index = s.length() - 1;
        while(index >= 0 && s.charAt(index) == ' ') index--;
        for(; index >= 0 && s.charAt(index) != ' '; index--) len++;
        return len;
    }
}