/*

Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

[
  ["aa","b"],
  ["a","a","b"]
]

*/

public class Solution {
	/*
		There are at most O(2^(n-1)) combinations, so complexity must be exponential.
		basically use DFS (backtracking)
		The only trick we can use is to memorize previous checked results of isPalindrome, however, this would cost more space.
	*/
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        partition(s, res, new ArrayList<String>(), 0);
        return res;
    }
    private void partition(String s, List<List<String>> res, List<String> list, int start) {
        if(start == s.length()) res.add(new ArrayList<>(list));
        else {
            for(int i = start; i < s.length(); i++) {
                if(isPalindrome(s, start, i)) {
                    list.add(s.substring(start, i + 1));
                    partition(s, res, list, i + 1);
                    list.remove(list.size() - 1);
                }
            }
        }
    }
    private boolean isPalindrome(String s, int start, int end) {
        while(start < end) {
            if(s.charAt(start) != s.charAt(end)) return false;
            start++;
            end--;
        }
        return true;
    }
}