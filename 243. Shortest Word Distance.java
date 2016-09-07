/*

Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = "coding", word2 = "practice", return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.

*/

/*
	Just record indexes of two words. One pass.
	O(n) time O(1) space
*/
public int shortestDistance(String[] words, String word1, String word2) {
	int start = -1, end = -1, min = Integer.MAX_VALUE;
	for(int i = 0; i < words.length; i++) {
		if(words[i].equals(word1)) start = i;
		if(words[i].equals(word2)) end = i;
		if(start != -1 && end != -1 && Math.abs(start - end) < min) min = Math.abs(start - end);
	}
	return min;
}