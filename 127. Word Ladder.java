/*

Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the word list
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.

*/


public class Solution {
	/* 93%
		Two ends BFS
	*/
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        Set<String> startSet = new HashSet<>(), endSet = new HashSet<>();
        startSet.add(beginWord);
        endSet.add(endWord);
        wordList.remove(beginWord); // remove those added words
        wordList.remove(endWord);
        int len = 2;
        while(!startSet.isEmpty() && !endSet.isEmpty()) {  // If one of two sets is empty, then it is impossible to complete transformation
            if(startSet.size() > endSet.size()) {  // expand from smaller set
                Set<String> tmp = startSet;
                startSet = endSet;
                endSet = tmp;
            }
            Set<String> newSet = new HashSet<>();  // set of next level
            for(String str : startSet) {  // try to transform each string
                char[] strArray = str.toCharArray();
                for(int i = 0; i < strArray.length; i++) {  // at each char
                    char oldC = strArray[i];
                    for(char c = 'a'; c <= 'z'; c++) {  // change to any char
                        strArray[i] = c;
                        String newStr = String.valueOf(strArray);
                        if(endSet.contains(newStr)) return len;
                        if(wordList.contains(newStr)) {
                            wordList.remove(newStr);
                            newSet.add(newStr);
                        }
                    }
                    strArray[i] = oldC;  // restore old char
                }
            }
            startSet = newSet;
            len++;
        }
        return 0;
    }
}