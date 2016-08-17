/*

Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
*/

public class Solution {
	/*
		Almost the same as #54 Spiral Matrix
		Both solutions work here. Here just provide one.
	*/
    public int[][] generateMatrix(int n) {
        if(n <= 0) return new int[n][n]; //throw new IllegalArgumentException("Non-positive input.");
        int[][] matrix = new int[n][n];
        int num = 1, total = n*n, dir = 0, x = 0, y = -1;
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        while(num <= total) {
            for(int i = 0; i < n; i++) {
                x += dirs[dir][0];
                y += dirs[dir][1];
                matrix[x][y] = num;
                num++;
            }
            if(dir % 2 == 0) n--; // decrement n every two iterations
            dir = (dir + 1) % 4;
        }
        return matrix;
    }
}