/*
Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
Assume the length of given string will not exceed 1,010.

Example:

Input:
"abccccdd"

Output:
7

Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.

*/

/*
	Just count frequencies and only add even numbers first. Then add odd if there is any.
*/
public class Solution {
    public int longestPalindrome(String s) {
        int[] counts = new int[256];
        for(char c : s.toCharArray()) counts[c]++;
        int length = 0, odd = 0;
        for(int count : counts) {
            if(count % 2 == 1) odd = 1;
            length += count - (count % 2); // only add even counts (e.g. 5 only count 4 here)
        }
        return length + odd;
    }
}