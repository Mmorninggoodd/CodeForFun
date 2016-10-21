/*

Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.
Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.

*/

public class Solution {
    public boolean wordPattern(String pattern, String str) {
        Map<String, Character> wordToPattern = new HashMap<>();
        Set<Character> cachedPattern = new HashSet<>();
        String[] strs = str.split(" ");
        if(strs.length != pattern.length()) return false;
        for(int i = 0; i < strs.length; i++) {
            if(!wordToPattern.containsKey(strs[i])) {
                if(!cachedPattern.add(pattern.charAt(i))) return false; // use this pattern before
                wordToPattern.put(strs[i], pattern.charAt(i));
            }
            else if(wordToPattern.get(strs[i]) != pattern.charAt(i)) return false;  // not following existing mapping
        }
        return true;
    }
}