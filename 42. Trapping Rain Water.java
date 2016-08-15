/*

Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

*/

public class Solution {
	/*
		Time O(n) space O(1)
		Two pointers.
	*/
    public int trap(int[] height) {
        int water = 0, curHeight = 0, left = 0, right = height.length - 1;
        while (left < right) {
            int lower = height[height[left] < height[right] ? left++ : right--];
            curHeight = Math.max(curHeight, lower);
            water += curHeight - lower;
        }
        return water;
    }
	/*
		Another version. Easier to understand.
		Only add to water, when it lower than curHeight.
	*/
	public int trap(int[] height) {
        int water = 0, curHeight = 0, left = 0, right = height.length - 1;
        while(left < right) {
            curHeight = Math.min(height[left], height[right]);
            while(left < right && curHeight >= height[left]) {
                if(height[++left] < curHeight) water += curHeight - height[left];
            }
            while(left < right && curHeight >= height[right]) {
                if(height[--right] < curHeight) water += curHeight - height[right];
            }
        }
        return water;
    }
}