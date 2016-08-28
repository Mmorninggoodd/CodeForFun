/*

Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X

*/

public class Solution {
    public void solve(char[][] board) {
        if(board == null || board.length == 0) return;
        int n = board.length, m = board[0].length;
		
		// fill boundary's and their connected area's 'O' with 'C'
        for(int i = 0; i < n; i++){
            fill(board, i, 0);
            fill(board, i, m-1);
        } 
        for(int j = 0; j < m; j++){
            fill(board, 0, j);
            fill(board, n-1, j);
        }
        for(int i = 0; i < n; i++ ){
            for(int j = 0; j < m; j++ ){
                if(board[i][j] == 'C'){
                    board[i][j] = 'O';
                }
                else if(board[i][j] == 'O')
                    board[i][j] = 'X';
            }
        }
    }
    
	// fill all 'O' connected area lead by boundary cell(x,y) 
    private void fill(char[][] board, int x, int y){
        if(board[x][y] != 'O') return;
        board[x][y] = 'C';
		// Trick: narrow checked area to avoid stack overflow
        if(x - 1 >= 1) fill(board, x - 1, y);
        if(x + 1 <= board.length - 2) fill(board, x + 1, y);
        if(y - 1 >= 1) fill(board, x, y - 1);
        if(y + 1 <= board[0].length - 2) fill(board, x, y + 1);
    }
}