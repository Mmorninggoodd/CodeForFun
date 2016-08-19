/*

Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.

*/

public class Solution {
	/* 10ms 83%
		A simple backtracking/dfs solution
		Another trick is board[i][j] ^= 256, since regular ASCII range from 0 to 127
	*/
    public boolean exist(char[][] board, String word) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(exist(board, i, j, word, 0)) return true;
            }
        }
        return false;
    }
    private boolean exist(char[][] board, int i, int j, String word, int index) {
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || word.charAt(index) != board[i][j]) return false;
        if(index == word.length() - 1) return true;
        board[i][j] = '.';  // so that it won't use it again
        boolean result = exist(board, i + 1, j, word, index + 1) || exist(board, i, j + 1, word, index + 1) || exist(board, i - 1, j, word, index + 1) || exist(board, i, j - 1, word, index + 1);
        board[i][j] = word.charAt(index);  // restore
        return result;
    }
}