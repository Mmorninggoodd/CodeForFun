/*
Write a function to generate the generalized abbreviations of a word.

Example:

Given word = "word", return the following list (order does not matter):

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]

*/

public List<String> generateAbbreviations(String word){
	List<String> res = new ArrayList<>();
	for(int len = 1; len <= word.length(); len++) {
		for(int start = 0; start + len <= word.length(); start++) {
			int end = start + len; // exclusive
			res.add((start == 0 ? "" : word.substring(0, start)) + len + (end == word.length() ? "" : word.substring(end)));
		}
	}
	res.add(word);
	return res;
}