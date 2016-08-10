/*

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]

*/

public class Solution {

	/*
		Use String directly. Recursively. DFS
	*/
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generateParenthesis(res, "", n, 0);
        return res;
    }
    public void generateParenthesis(List<String> res, String s, int open, int close) {
        if(open == 0 && close == 0) res.add(s);
        if(open != 0) generateParenthesis(res, s + '(', open - 1, close + 1);
        if(close != 0) generateParenthesis(res, s + ')', open, close - 1);
    }
	
	/*
		Use StringBuilder. Recursively. DFS. A bit faster.
	
	*/
	public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generateParenthesis(res, new StringBuilder(), n, 0);
        return res;
    }
    
    public void generateParenthesis(List<String> res, StringBuilder sb, int open, int close) {
        if(open == 0 && close == 0) res.add(sb.toString());
		int len = sb.length();
        if(open != 0) {
			sb.append('(');
			generateParenthesis(res, sb, open - 1, close + 1);
			sb.setLength(len);
		}
        if(close != 0) {
			sb.append(')');
			generateParenthesis(res, sb, open, close - 1);
			sb.setLength(len);
		}
    }
	
	/*
		DP. While it takes a lot of spaces, and slow.
		O(n^2) space
	*/
	public static List<String> generateParenthesis(int n) {
		List<List<String>> cache = new LinkedList<>();
		cache.add(Arrays.asList(""));

		for (int i = 1; i <= n; i++) {
			List<String> nList = new LinkedList<>();
			for (int j = 0; j < i; j++) {
				List<String> inside = cache.get(j);
				List<String> tail = cache.get(i - j - 1);
				for (int k = 0; k < inside.size(); k++) {
					for (int l = 0; l < tail.size(); l++) {
						nList.add("(" + inside.get(k) + ")" + tail.get(l));
					}
				}
			}
			cache.add(nList);
		}
		return cache.get(n);
	}
}