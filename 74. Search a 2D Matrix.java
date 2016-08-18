/*

Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.
*/

public class Solution {
	/*
		Just transform the matrix into a sorted array.
		O(lgn) time O(1) space Binary Search
		
	*/
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length, m = matrix[0].length;
        int left = 0, right = n * m - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            int x = mid / m, y = mid % m;  // Transform
            if(matrix[x][y] == target) return true;
            if(matrix[x][y] > target) right = mid - 1;
            else left = mid + 1;
        }
        return false;
    }
}