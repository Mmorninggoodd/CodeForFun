/*
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.

*/

public class Solution {
	/*
		Just check from two sides O(n) time
	*/
    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while(left < right) {
            if(!Character.isLetterOrDigit(s.charAt(left))) left++;
            else if(!Character.isLetterOrDigit(s.charAt(right))) right--;
            else {
                if(Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) return false;
                left++; right--;
            }
        }
        return true;
    }
}