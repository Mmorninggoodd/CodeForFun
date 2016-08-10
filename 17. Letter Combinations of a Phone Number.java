/*
Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.



Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
*/


public class Solution {
	
	/*
		Use StringBuilder. 3 loops: digit -> each current sb (record size) -> add each new char for this digit
		Time and space O(4 ^ digits.length()) exponential
	*/
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if(digits.length() == 0) return res;
        List<StringBuilder> sbs = new ArrayList<>();
        String[] chars = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        sbs.add(new StringBuilder());
        for(int i = 0; i < digits.length(); i++) {
            int digit = digits.charAt(i) - '0';
            int size = sbs.size(); // Record this size!
            for(int j = 0; j < size; j++) {
                StringBuilder sb = sbs.get(j);
                for(int k = 1; k < chars[digit].length(); k++) {
                    char c = chars[digit].charAt(k);
                    sbs.add(new StringBuilder(sb).append(c));
                }
                sb.append(chars[digit].charAt(0)); 
            }
        }
        for(StringBuilder sb : sbs) res.add(sb.toString());
        return res;
    }
	
	
	/*
	
		Similar version, but use String directly, since complexity is already exponential.
	
	*/
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if(digits.length() == 0) return res;
        String[] chars = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        res.add("");
        for(int i = 0; i < digits.length(); i++) {
            int digit = digits.charAt(i) - '0';
            while(res.get(0).length() == i) {  // A small trick to find current level's string
                String s = res.remove(0); // remove it
                for(char c : chars[digit].toCharArray()) {
                    res.add(s + c);
                }
            }
        }
        return res;
    }
}