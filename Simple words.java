/*
 Similar with Word Break.
 
 Just output those words cannot be break.

*/

/*
	Use DP to check each word. 
	Time O(nWord * (word len) ^ 2)
	Space O(nWord)
*/
static String[] simpleWords(String[] words) {
	Set<String> dict = new HashSet<>();
	List<String> res = new ArrayList<>();
	Collections.addAll(dict, words);
	for(String word : words) {
		dict.remove(word);
		if(word.length() == 0 || !canBreak(word, dict)) res.add(word);
		dict.add(word);
	}
	return res.toArray(new String[0]);
}
static boolean canBreak(String word, Set<String> dict) {
	boolean[] dp = new boolean[word.length() + 1];
	dp[0] = true;
	for(int end = 1; end <= word.length(); end++) {  // exclusive
		for(int start = 0; start < end; start++) {
			if(dp[start] && dict.contains(word.substring(start, end))) dp[end] = true;
		}
	}
	return dp[word.length()];
}