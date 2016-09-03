/*

Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.

*/
public class Solution {
	/* 5ms 64%
		Backtracking
		
	*/
    public boolean isScramble(String s1, String s2) {
        if(s1.equals(s2)) return true;
        if(!isAnagram(s1, s2)) return false;
        int n = s1.length();
        for(int i = 1; i < n; i++) {
            if(isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) return true;
            if(isScramble(s1.substring(0, i), s2.substring(n - i)) && isScramble(s1.substring(i), s2.substring(0, n - i))) return true;
        }
        return false;
    }
    private boolean isAnagram(String s1, String s2) {
        if(s1 == null || s2 == null || s1.length() != s2.length()) return false;
        int[] letters = new int[26];
        for(int i = 0; i < s1.length(); i++) {
            letters[s1.charAt(i) - 'a']++;
            letters[s2.charAt(i) - 'a']--;
        }
        for(int letter : letters) if(letter != 0) return false;
        return true;
    }
	/* 2ms 96%
		Optimized Backtracking
		Use two array to count characters on the fly, only when it satisfy anagram we pass it into next recursion.
	*/
	public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        if (n == 1) return s1.equals(s2);
        int[] letters1 = new int[26], letters2 = new int[26];
        for (int i = 0, count1 = 0, count2 = 0; i < n-1; i++) {
            if (letters1[s1.charAt(i) - 'a']++ < 0) count1++;
            if (letters1[s2.charAt(i) - 'a']-- > 0) count1++;
            if (count1 == i + 1 &&   // s1[0:i] and s2[0:i] are anagrams
                isScramble(s1.substring(0, count1), s2.substring(0, count1)) && 
                isScramble(s1.substring(count1), s2.substring(count1))) return true;
            if (letters2[s1.charAt(i) - 'a']++ < 0) count2++;
            if (letters2[s2.charAt(n - 1 - i) - 'a']-- > 0) count2++;
            if (count2 == i + 1 &&   //  s1[i+1:n] and s2[0,n-i-1] are anagrams
                isScramble(s1.substring(0, count2), s2.substring(n - count2)) &&
                isScramble(s1.substring(count2), s2.substring(0, n - count2))) return true;
        }
        return false;
    }
}
