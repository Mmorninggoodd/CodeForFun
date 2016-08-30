/*

Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Update (2015-02-12):
For C programmers: Try to solve it in-place in O(1) space.

click to show clarification.

Clarification:
What constitutes a word?
A sequence of non-space characters constitutes a word.
Could the input string contain leading or trailing spaces?
Yes. However, your reversed string should not contain leading or trailing spaces.
How about multiple spaces between two words?
Reduce them to a single space in the reversed string.

*/

public class Solution {
	/*
		version 1
	*/
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i = s.length() - 1, j = 0; i >= 0; i--) {
            if(s.charAt(i) != ' ') { // find a new word
                for(j = i; j > 0 && s.charAt(j - 1) != ' '; j--); // find start index of this word
                if(sb.length() != 0) sb.append(' ');
                sb.append(s.substring(j, i + 1));
                i = j;
            }
        }
        return sb.toString();
    }
	/*
		version 2: a bit faster
	*/
	public String reverseWords(String s) {
        char[] charArray = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i = s.length() - 1, j = 0; i >= 0; i--) {
            if(charArray[i] != ' ') { // find a new word
                for(j = i; j > 0 && charArray[j - 1] != ' '; j--); // find start index of this word
                if(sb.length() != 0) sb.append(' ');
                sb.append(charArray, j, i - j + 1);
                i = j;
            }
        }
        return sb.toString();
    }
}