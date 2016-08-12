/*

Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

*/

public class Solution {
	/*
	     5 2 6 5 4 3 1 -> 5 3 1 2 4 5 6
		   ^       ^
	   First find the first descending element (2) from right to left,
	   then find the smallest element (3) on the right that still is larger than this element (2)
	   Swap them, then reverse right part such that they are sorted in increasing order.
	   
	   O(n) time, O(1) space in place
	   
	   Be careful about cornel cases:
	   [1], []: Only one or none element
	   [2, 2], [1, 2, 3]: Cannot find first descending element.
	
	*/
    public static void nextPermutation(int[] nums) {
        if(nums == null || nums.length < 2) return;
        int i = nums.length - 2, j = 0;
        for(; i >= 0; i--) { // finding first descending element
            if(nums[i] < nums[i + 1]) break;
        }
        if(i >= 0) {  // Found
            for(j = i + 1; j < nums.length && nums[j] > nums[i]; j++);  // find insertion index
            swap(nums, j - 1, i);
        }
        // Reverse remaining part
        reverse(nums, i + 1, nums.length - 1);
    }
    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    private static void reverse(int[] nums, int i, int j) {
        while(i < j) swap(nums,i++, j--);
    }
}