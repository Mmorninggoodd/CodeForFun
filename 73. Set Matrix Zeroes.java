/*

Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

click to show follow up.

Follow up:
Did you use extra space?
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?

*/


public class Solution {
	/* 2ms 
		Use first row and column to record which row or column needed to be set to zeros.
		Use two boolean variables to record if the first row or column needed to be set to zeros.
		Avoid changing matrix[0][0] during flagging.
	
	*/
    public void setZeroes(int[][] matrix) {
        boolean rowZero = false, colZero = false;
        int n = matrix.length, m = matrix[0].length;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(matrix[i][j] == 0) {
                    if(i == 0) rowZero = true;
                    if(j == 0) colZero = true;
                    if(j != 0) matrix[0][j] = 0;
                    if(i != 0) matrix[i][0] = 0;
                }
            }
        }
        for(int i = n - 1; i >= 0; i--) {
            for(int j = m - 1; j >= 0; j--) {
                if(matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;
                if((i == 0 && rowZero) || (j == 0 && colZero)) matrix[i][j] = 0;
            }
        }
    }
}