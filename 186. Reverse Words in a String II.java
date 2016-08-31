/*

Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.

The input string does not contain leading or trailing spaces and the words are always separated by a single space.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Could you do it in-place without allocating extra space?

*/



/*
	First reverse whole string.
	Then reverse each word
*/
public static void reverseWords(char[] s) {
	reverse(s, 0, s.length - 1);
	for(int i = 0; i < s.length; i++) {
		if(s[i] != ' ') {
			int j = i;
			while(j < s.length - 1 && s[j + 1] != ' ')  j++;
			reverse(s, i, j);
			i = j + 1;
		}
	}
	return;
}
private static void reverse(char[] s, int start, int end) {
	for(; start < end; start++, end--) {
		char tmp = s[start];
		s[start] = s[end];
		s[end] = tmp;
	}
}