/*
word break II
Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].

*/

public class Solution {
	
	/*TLE
		Backtracking
	*/
	public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> res = new ArrayList<>();
        wordBreak(s, wordDict, 0, new StringBuilder(), res);
        return res;
    }
    private void wordBreak(String s, Set<String> wordDict, int start, StringBuilder sb, List<String> res) {
        if(start == s.length()) {
            res.add(sb.toString().trim());
            return;
        }
        int sbLen = sb.length();
        for(int end = start + 1; end <= s.length(); end++) {
            String word = s.substring(start, end);
            if(wordDict.contains(word)) {
                sb.append(" ").append(word);
                wordBreak(s, wordDict, end, sb, res);
                sb.setLength(sbLen);
            }
        }
    }
	
	
	/* 3ms 99.91%
		Backtracking. Use a boolean array to prune branches.
		invalid[i]: s[i:end] can not be breakable
		
		Also record max length of all words to prune branches.
	*/
    int maxLen = 0;  // max length of all words
    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> res = new ArrayList<>();
        for (String word : wordDict) if (word.length() > maxLen) maxLen = word.length();
        wordBreak(s, wordDict, new StringBuilder(), 0, new boolean[s.length()], res);
        return res;
    }
    private boolean wordBreak(String s, Set<String> wordDict, StringBuilder sb, int start, boolean[] invalid, List<String> res) {
        if (start == s.length()) { // reach the end
            res.add(sb.toString().trim());
            return true;
        }
        boolean breakable = false;
        int sbLen = sb.length();  // record current size
        int rightBound = Math.min(s.length(), start + maxLen);
        for (int end = start + 1; end <= rightBound; end++) {   // exclusive
            if (end != s.length() && invalid[end]) continue;    // check if s[end:] is unbreakable
            String word = s.substring(start, end);
            if (wordDict.contains(word)) {
                sb.append(" ").append(word);
                breakable |= wordBreak(s, wordDict, sb, end, invalid, res);
                sb.setLength(sbLen);
            }
        }
        invalid[start] = !breakable;
        return breakable;
    }
	
	/*
		Can also be solved by Trie when dictionary is large,
		or query will be called a lot times.
	*/
}