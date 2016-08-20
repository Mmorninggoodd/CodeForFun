/*

Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 6.

*/

public class Solution {
	/*
		Method 1: Just use solution of #84 Largest Rectangle in Histogram
		Time O(N^2) Space O(N)
	
	*/
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int max = 0;
        int[] heights = new int[matrix[0].length];
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == '1') {
                    heights[j] = (i == 0 ? 0 : heights[j]) + 1;
                }
                else heights[j] = 0;
            }
            max = Math.max(max, maximalRectangle(heights));
        }
        return max;
    }
    private int maximalRectangle(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int max = 0;
        for(int right = 0; right <= heights.length;) {
            int rightHeight = (right == heights.length ? 0 : heights[right]);
            if(stack.isEmpty() || heights[stack.peek()] < rightHeight) stack.push(right++);
            else {
                int curHeight = heights[stack.pop()];
                int left = stack.isEmpty() ? -1 : stack.peek();
                max = Math.max(max, curHeight * (right - left - 1));
            }
        }
        return max;
    }
	
	/*
		Method 2: DP O(n^2) space O(n) 4-pass
		Use three dp array:
		left[i,j]: leftmost index of current rectangle. (inclusive)
		right[i,j]: rightmost index (exclusive)
		height[i,j]: height starting from [i,j]
		
		Each time we analysis rectangle area that based on [i,j]. 
		For example,
		0 1 1 0
		1 1 1 0
		1 1 0 1
		rectangle based on [i,j] is that rectangle use all 1's above and including [i,j]
		so rectangle based on [2,0] is a 2*2 square, while rectangle based on [2,1] is a 3*1 rectangle.
		
		Another example：
		0 0 0 1 0 0 0 
		0 0 1 1 1 0 0 
		0 1 1 1 1 1 0
		
		
		0th row: 0 0 0 1 0 0 0
		height: 0 0 0 1 0 0 0
		left: 0 0 0 3 0 0 0
		right 7 7 7 4 7 7 7

		1st row: 0 0 1 1 1 0 0
		height: 0 0 1 2 1 0 0 
		left: 0 0 2 3 2 0 0
		right: 7 7 5 4 5 7 7

		2nd row: 0 1 1 1 1 1 0
		height: 0 1 2 3 2 1 0
		left: 0 1 2 3 2 1 0
		right: 7 6 5 4 5 6 7
		
	*/
	public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int max = 0, m = matrix[0].length;
        int[] height = new int[matrix[0].length], left = new int[matrix[0].length], right = new int[matrix[0].length];
        for(int i = 0; i < matrix.length; i++) {
            int curLeft = 0, curRight = m;
            for(int j = 0; j < m; j++) {  // update left and height
                if(matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], curLeft);
                    height[j] += 1; 
                }
                else {
                    left[j] = 0;
                    curLeft = j + 1;
                    height[j] = 0;
                }
            }
            for(int j = m - 1; j >= 0; j--) {  // update right and area
                if(matrix[i][j] == '1') {
                    right[j] = Math.min(i == 0 ? m : right[j], curRight);
                    max = Math.max(max, (right[j] - left[j]) * height[j]);
                }
                else {
                    curRight = j;
                    right[j] = m;
                }
            }
        }
        return max;
    }
	
}