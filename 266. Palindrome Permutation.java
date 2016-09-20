/* Palindrome Permutation
Problem Description:

Given a string, determine if a permutation of the string could form a palindrome.

For example,
"code" -> False, "aab" -> True, "carerac" -> True.

Hint:

Consider the palindromes of odd vs even length. What difference do you notice?
Count the frequency of each character.
If each character occurs even number of times, then it must be a palindrome. How about character which occurs odd number of times?

*/

/*
	O(n) time O(1) space
	Can use HashMap<Character, Integer> as well
*/
public boolean canPermutePalindrome(String s) {
	int[] counts = new int[256];
	for(char c : s.toCharArray()) counts[c]++;
	int odd = 0;
	for(int count : counts) if(count % 2 == 1) odd++;
	return odd <= 1;  // at most have one odd count
}