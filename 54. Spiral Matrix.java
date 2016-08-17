/*

Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].

*/

public class Solution {
	
	/* 0ms
		Regular method
	
	*/
	public List<Integer> spiralOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return new ArrayList<>();
            //throw new IllegalArgumentException("Null or not a matrix.");
        int rowMax = matrix.length - 1, colMax = matrix[0].length - 1, rowMin = 0, colMin = 0;
        int total = (rowMax + 1) * (colMax + 1);
        List<Integer> res = new ArrayList<>(total);
        while(res.size() < total) {
            for(int i = colMin; i <= colMax; i++) res.add(matrix[rowMin][i]);
            rowMin++;
            for(int i = rowMin; i <= rowMax; i++) res.add(matrix[i][colMax]);
            colMax--;
            if(res.size() == total) break; // Avoid duplicates
            for(int i = colMax; i >= colMin; i--) res.add(matrix[rowMax][i]);
            rowMax--;
            for(int i = rowMax; i >= rowMin; i--) res.add(matrix[i][colMin]);
            colMin++;
        }
        return res;
    }
	/* 1ms
		Use direction arrays.
		Only need to consider some corner cases.
	
	*/
    public List<Integer> spiralOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return new ArrayList<>();
            //throw new IllegalArgumentException("Null or not a matrix.");
        int m = matrix.length, n = matrix[0].length;
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int dir = 0, total = n * m;
        int[] pos = new int[]{0, -1};  // start from [0, -1]
        List<Integer> res = new ArrayList<>(total);
        while(res.size() < total) {
            for(int i = 0; i < n; i++) {  // start from column
                pos[0] += dirs[dir][0];
                pos[1] += dirs[dir][1];
                res.add(matrix[pos[0]][pos[1]]);
            }
            dir = (dir + 1) % 4;
            //swap n m
            int tmp = n; n = m; m = tmp;
            n--; // decrement n starting from second iteration
        }
        return res;
    }
}