/*
You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?
*/

public class Solution {
	/* 0ms
		Time O(N) Space O(1)
		Rotate by ring.
	*/
    public void rotate(int[][] matrix) {
        for(int min = 0, max = matrix.length - 1; min < max; min++, max--) {
            for(int i = 0; i < max - min; i++) {
                int tmp = matrix[min][min + i];
                matrix[min][min + i] = matrix[max - i][min];
                matrix[max - i][min] = matrix[max][max - i];
                matrix[max][max - i] = matrix[min + i][max];
                matrix[min + i][max] = tmp;
            }
        }
    }
}