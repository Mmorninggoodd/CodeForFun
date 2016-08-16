/*

Follow up for N-Queens problem.

Now, instead outputting board configurations, return the total number of distinct solutions.

*/
public class Solution {
	/*
		Almost the same as #51 N-Queens, just return count
	*/
    public int totalNQueens(int n) {
        if(n == 0) return 0;
        return totalNQueens(0, new boolean[n], new boolean[2*n], new boolean[2*n]);
    }
    private int totalNQueens(int row, boolean[] cols, boolean[] d1, boolean[] d2) {
        int n = cols.length;
        if(row == n) return 1;
        int count = 0;
        for(int col = 0; col < n; col++) {
            if(cols[col] || d1[col + row] || d2[n - col + row]) continue;
            cols[col] = true;
            d1[col + row] = true;
            d2[n - col + row] = true;
            count += totalNQueens(row + 1, cols, d1, d2);
            cols[col] = false;
            d1[col + row] = false;
            d2[n - col + row] = false;
        }
        return count;
    }
}