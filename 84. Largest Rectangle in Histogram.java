/*
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.


Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].


The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,
Given heights = [2,1,5,6,2,3],
return 10.
*/

/*
	Idea 1:
	Divide and Conquer using segment tree search. Time & space O(nlgn)
	http://www.geeksforgeeks.org/largest-rectangular-area-in-a-histogram-set-1/
	
	
	Idea 2:
	For each bar, we calculate maximum area with current bar as the smallest one in the rectangle. If we want to maximize the area of this rectangle, the two sides' bars right next to this rectangle (but not belonging to this rectangle) are smaller than current bar. 
	
	If we do this for every bar, and record the maximum, then this maximum is the result we want. (Since height of rectangle is determined by smallest bar in this rectangle, and width is determined by number of bars in this rectangle. Now we calculate all possible maximum rectangles whose height is one of the bars with largest possible width.)
	
	How can we get left smaller bar and right smaller bar of each bar?
	(1) Brute force way: Traverse all bars, and use linear search to find. O(n^2) time O(1) space
	
	(2) Use two arrays to record known left, right bars of each bar. When we search for next bar, we might use this result to skip some bars. It is time O(n) and space O(n).
	https://discuss.leetcode.com/topic/48127/java-o-n-left-right-arrays-solution-4ms-beats-96
	
	(3) Use a Stack to store indexes of all pending bars. What does 'pending' means here? It means we cannot find corresponding right bar for it. A valid corresponding right bar is defined as the first bar the smaller than this bar to the right.
	So when we meet a higher bar than current top of stack, we push it. Otherwise, we pop out the top of current stack, because we find a valid right bar for this popped out bar. Then we look for its left bar which just located on the next layer of this stack. Now we have left bar and right bar of this popped out bar, we can calculate its maximum rectangle's area.
	
	So stack always maintains indexes of ascending ordered bars.
	
	After we traverse all bars, if stack is not empty. Then it means there is no right bar for these remaining bars (an extreme example is increasing ordered bars), so we can just use heights.length as the index of right bar for them, and popped them out one by one.
	
	This method time complexity is O(n) space O(n)
	
	
	Some corner cases during execution:
		1. Stack is empty when try to compare top of stack: Just push current bar.
		2. No left bar found after current bar is popped out: case like [3,1]. use -1 if stack is empty when looking for left bar.
		
	A small trick:
		When rightIndex == heights.length, use 0 as new bar's height so that we can pop out all remaining bars in the stack. (Since no bar's height will be smaller than 0. We won't need another while loop to handle it.)

*/

public class Solution {
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int max = 0, rightIndex = 0;
        while(rightIndex <= heights.length) {
            int rightHeight = (rightIndex == heights.length ? 0 : heights[rightIndex]); // All new bars are seen as potential right bar
            if(stack.isEmpty() || heights[stack.peek()] < rightHeight) stack.push(rightIndex++); // Only increment rightIndex, when pushed
            else {
                int curHeight = heights[stack.pop()];  // Be careful that stack only store index, we still need heights[index] to get height
                int leftIndex = (stack.isEmpty() ? -1 : stack.peek());  // Be careful of case of no left bar
                max = Math.max(max, (rightIndex - leftIndex - 1) * curHeight);
            }
        }
        return max;
    }
	
	
	
	/*
		Without trick version
	*/
	public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int max = 0, rightIndex = 0;
        while(rightIndex < heights.length) {
            int rightHeight = heights[rightIndex]; // All new bars are seen as potential right bar
            if(stack.isEmpty() || heights[stack.peek()] < rightHeight) stack.push(rightIndex++); // Only increment rightIndex, when pushed
            else {
                int curHeight = heights[stack.pop()];  // Be careful that stack only store index, we still need heights[index] to get height
                int leftIndex = (stack.isEmpty() ? -1 : stack.peek());  // Be careful of case of no left bar
                max = Math.max(max, (rightIndex - leftIndex - 1) * curHeight);
            }
        }
        while(!stack.isEmpty()) {
            int curHeight = heights[stack.pop()]; 
            int leftIndex = (stack.isEmpty() ? -1 : stack.peek()); 
            max = Math.max(max, (rightIndex - leftIndex - 1) * curHeight); // here rightIndex == heights.length
        }
        return max;
    }
}