/*
Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution.

*/


/* 6ms 99%
	Just a DFS, and memorization from 36. Valid Sudoku.
*/
public class Solution {
    public void solveSudoku(char[][] board) {
        boolean[][] row = new boolean[9][9], col = new boolean[9][9], block = new boolean[9][9];
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] != '.') {
                    int idx = board[i][j] - '1';
                    row[i][idx] = true;
                    col[j][idx] = true;
                    block[(i/3)*3 + j/3][idx] = true;
                }
            }
        }
        solveSudoku(board, 0, 0, row, col, block);
    }
    private boolean solveSudoku(char[][] board, int i, int j, boolean[][] row, boolean[][] col, boolean[][] block) {
        if(i == 9) return true;   // finish the whole board
        while(j < 9 && board[i][j] != '.') j++;  // find next number that needed to be changed
        if(j == 9) return solveSudoku(board, i + 1, 0, row, col, block);  // current row is done
        for(int next = 0; next < 9; next++) {   // try each number
            if(row[i][next] || col[j][next] || block[(i/3)*3 + j/3][next]) continue;
            row[i][next] = true;
            col[j][next] = true;
            block[(i/3)*3 + j/3][next] = true;
            board[i][j] = (char) (next + '1');
            if(solveSudoku(board, i, j + 1, row, col, block)) return true;
            row[i][next] = false;
            col[j][next] = false;
            block[(i/3)*3 + j/3][next] = false;
            board[i][j] = '.';
        }
        return false;
    }
}