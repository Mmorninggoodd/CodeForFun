/*

Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].
Note:
You may assume that all inputs are consist of lowercase letters a-z.

click to show hint.

You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?

If the current candidate does not exist in all words' prefix, you could stop backtracking immediately. What kind of data structure could answer such query efficiently? Does a hash table work? Why or why not? How about a Trie? If you would like to learn how to implement a basic trie, please work on this problem: Implement Trie (Prefix Tree) first.

*/

/* 20ms 89%
	Use trie tree to store all target words.
	Then just we run dfs starting from root TrieNode which actually can search for all words at the same time. 

*/
class TrieNode {
    String word = null;
    TrieNode[] children = new TrieNode[26];
    public TrieNode(){}
}

public class Solution {
	TrieNode root = new TrieNode();
    public List<String> findWords(char[][] board, String[] words) {
        buildTree(words);
        List<String> res = new ArrayList<>();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, res);
            }
        }
        return res;
    }
    private void dfs(char[][] board, int i, int j, TrieNode node, List<String> res) {
        if(node == null || i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '.') return;
        char c = board[i][j];
        TrieNode child = node.children[c - 'a'];
        if(child == null) return;
        if(child.word != null) { // find word
            res.add(child.word);
            child.word = null;   // remove this word
        }   // not to return here, because you might miss other words that has same prefix
        board[i][j] = '.';
        dfs(board, i + 1, j, child, res);
        dfs(board, i - 1, j, child, res);
        dfs(board, i, j + 1, child, res);
        dfs(board, i, j - 1, child, res);
        board[i][j] = c;
    }
    private void buildTree(String[] words) {
        for(String word : words) {
            TrieNode cur = root;
            for(char c : word.toCharArray()) {
                int i = c - 'a';
                if(cur.children[i] == null) cur.children[i] = new TrieNode();
                cur = cur.children[i];
            }
            cur.word = word;  // flag as a word
        }
    }
}