/*

Given an encoded string, return it's decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

*/

public class Solution {
    public String decodeString(String s) {
        if(s.length() == 0) return s;
        Deque<Integer> ks = new ArrayDeque<>();
        Deque<StringBuilder> patterns = new ArrayDeque<>();
        patterns.push(new StringBuilder());   // For the result
        int k = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ('0' <= c && c <= '9') k = 10 * k + (c - '0');
            else if (c == '[') {   // each time meet a '[', push k and a new StringBuilder in pattern
                ks.push(k);
                patterns.push(new StringBuilder());
                k = 0;
            }
            else if (c == ']') {   // each time meet a ']', use top of stack as current pattern, and add repeated pattern into next layer 
                StringBuilder pattern = patterns.pop();
                k = ks.pop();
                for(; k > 0; k--) {
                    patterns.peek().append(pattern);
                }
            }
            else patterns.peek().append(c);
        }
        return patterns.pop().toString();
    }
}