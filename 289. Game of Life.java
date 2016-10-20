/*

According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state.

Follow up: 
Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?

*/

/*
	Time O(n*m) Space O(1)
	Store two states in board.
	(next) (now)
		1    0
		0    1
		1    1 
		0    0
	
	Note that: if input if boolean[][], then we cannot do it in place.
*/
public class Solution {
	static final int[][] dirs = new int[][]{{1,0},{0,1},{1,1},{-1,0},{0,-1},{-1,-1},{1,-1},{-1,1}};
    public static void gameOfLife(int[][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                int count = getNumLiving(board, i, j);
                if(count >= 2 && count <= 3 && board[i][j] == 1) board[i][j] = 3;
                if(count == 3 && board[i][j] == 0) board[i][j] = 2;
            }
        }
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                board[i][j] >>= 1;
            }
        }
    }
    private static int getNumLiving(int[][] board, int x, int y) {
        int count = 0;
		for(int[] dir : dirs) {
			if(x + dir[0] >= 0 && x + dir[0] < board.length && y + dir[1] >= 0 && y + dir[1] < board[0].length) {
				count += board[x + dir[0]][y + dir[1]] & 1;
			}
		}
        return count;
    }
}

/*
	Follow up: What if the board is very large, how to handle it?
	
	Can only store those coordinate has life in a list.
	
*/