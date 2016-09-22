/*
Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.
*/

public class Solution {
	/*
		Time O(n) space O(1)
		
		First traverse try to put every element to their index 
		The second traverse just find which element is not in their index
		
		Some cases needed to consider:
		(1) 5 1 2 3 4 -> return 6 (it can hide in nums[0]) 
		(2) 4 1 2 3 4 -> return 5
		(3) empty -> return 1
	
	*/
    public int firstMissingPositive(int[] nums) {
        if(nums.length == 0) return 1;
        for(int i = 0; i < nums.length; i++) {
            while(nums[i] > 0 && nums[i] < nums.length && nums[nums[i]] != nums[i]) swap(nums, nums[i], i);
        }
        int index = 1;
        while(index < nums.length && nums[index] == index) index++;
        return index != nums.length || nums[0] != index ? index : index + 1; 
    }
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}