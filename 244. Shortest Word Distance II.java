/*

This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your method will be called repeatedly many times with different parameters. How would you optimize it?

Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = "coding”, word2 = "practice”, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
*/

/*
	use map to store (word, indexes list) pairs
	Set up takes O(n) time & space
	Each call shortest takes amortized O(1) time.
*/
public class WordDistance {
	Map<String, List<Integer>> map;
	public WordDistance(String[] words) {
		map = new HashMap<>();
		for(int i = 0; i < words.length; i++) {
			if(!map.containsKey(words[i])) map.put(words[i], new ArrayList<>());
			map.get(words[i]).add(i);
		}
	}
	public int shortest(String word1, String word2) {
		List<Integer> l1 = map.get(word1), l2 = map.get(word2);
		int min = Integer.MAX_VALUE;
		for(int i = 0, j = 0; i < l1.size() && j < l2.size();) {
			int p1 = l1.get(i), p2 = l2.get(j);
			if(p1 < p2) i++;
			else j++;
			min = Math.min(min, Math.abs(p1 - p2));
		}
		return min;
	}
}