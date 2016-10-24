/*
Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.


A partially filled sudoku which is valid.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
*/

public class Solution {
	
	/*
		For n*n board,
		Time O(n^2) Space O(n)
		Use boolean[] to record number existing.
		Need to scan the board three times.
	*/
    public boolean isValidSudoku(char[][] board) {
        int[][] dirs = new int[][]{{1, 0}, {0, 1}};
        // By row and by column
        for(int[] dir : dirs) {
            for(int i = 0, j = 0; i < 9 && j < 9; i += dir[0], j += dir[1]) {
                boolean[] flag = new boolean[9];
                for(int newI = i, newJ = j; newI < 9 && newJ < 9; newI += dir[1], newJ += dir[0]) {
                    if(!isValidCell(flag, board[newI][newJ])) return false;
                }
            }
        }
        // 3*3 blocks
        for(int i = 0; i < 9; i++) { // control which 3*3 block
            boolean[] flag = new boolean[9];
            for(int j = 0; j < 9; j++) { // control which cell in current block
                int newI = (i % 3) * 3 + (j % 3), newJ = (i / 3) * 3 + (j / 3); 
                if(!isValidCell(flag, board[newI][newJ])) return false;
            }
        }
        return true;
    }
    private boolean isValidCell(boolean[] flag, char c) {
        if(c == '.') return true;
        if(c <= '0' || c > '9') return false;
        int index = c - '1';
        if(flag[index]) return false;
        flag[index] = true;
        return true;
    }
	
	/*
		Only scan the board one time, but require 3*n*n bits to record number existing.
		Time O(n*2) space O(n^2)
		Note that we could use int and bit operations which might be faster, but it is not a 
		good practice, since each int only has 32 bit which is not general for all kind of boards.
	*/
	public boolean isValidSudoku(char[][] board) {
		boolean[][] vset = new boolean[9][9];
		boolean[][] hset = new boolean[9][9];
		boolean[][] bckt = new boolean[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.') {
					int idx = board[i][j] - '1' ;  // to avoid out of boundary
					if (hset[i][idx] || vset[j][idx] || bckt[(i / 3) * 3 + j / 3][idx]) return false;
					hset[i][idx] = true;
					vset[j][idx] = true;
					bckt[(i / 3) * 3 + j / 3][idx] = true;
				}
			}
		}
		return true;
	}
}