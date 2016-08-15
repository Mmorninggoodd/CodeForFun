/*
Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container.
*/

public class Solution {
	/*
		3ms 87%
		Time O(n) Space O(1)
		Two pointers, record current lower height and maximum area.
	*/
    public int maxArea(int[] height) {
        if(height == null || height.length <= 1) return 0;
        int left = 0, right = height.length - 1;
        int maxA = 0, curH = 0;
        while(left < right){
            curH = Math.min(height[left], height[right]);
            maxA = Math.max(maxA, curH * (right - left));
            while(left < right && curH >= height[left]) left++;
            while(left < right && curH >= height[right]) right--;
        }
        return maxA;
    }
}