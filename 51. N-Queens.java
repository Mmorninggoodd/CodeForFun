/*

The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]

*/

public class Solution {
	
	/* 3ms 97%
		Each recursion handle one row.
		Use boolean[] cols to record columns occupied
		Use boolean[] d1 and d2 to record diagonals occupied
		If two queens can attack each other in diagonal directions, they must satisfy either condition of:
			(1) row1 + col1 = row2 + col2
			(2) row1 - col1 = row2 - col2
			
		Then we can just check these three arrays to determine whether it is valid.
		
		Note: SrtingBuilder is used here to reduce time complexity of modifing board.
	
	*/
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        StringBuilder[] board = new StringBuilder[n];
        for(int i = 0; i < n; i++) {
            board[i] = new StringBuilder(n);
            for(int j = 0; j < n; j++)    board[i].append('.');
        }
        solveNQueens(0, new boolean[n], new boolean[2*n], new boolean[2*n], board, res);
        return res;
    }
	
    private void solveNQueens(int row, boolean[] cols, boolean[] d1, boolean[] d2,  StringBuilder[] board, List<List<String>> res) {
        if(row == board.length) {
            List<String> list = new ArrayList<>();
            for(StringBuilder sb : board) list.add(sb.toString());
            res.add(list);
            return;
        }
        for(int col = 0; col < board.length; col++) {
            int id1 = row + col, id2 = board.length + row - col; // maske sure it is non-negative
            if(!cols[col] && !d1[id1] && !d2[id2]) {
                board[row].setCharAt(col, 'Q');
                cols[col] = true;
                d1[id1] = true;
                d2[id2] = true;
                solveNQueens(row + 1, cols, d1, d2, board, res);
                cols[col] = false;
                d1[id1] = false;
                d2[id2] = false;
                board[row].setCharAt(col, '.');
            }
        }

    }
}