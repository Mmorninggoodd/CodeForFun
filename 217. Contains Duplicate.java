/*
Given an array of integers, find if the array contains any duplicates. Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
*/
public class Solution {
	/*
		Very simple one. Use a hashset to record existing numbers.
		Time　O(n) space O(n)
	
	*/
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int num : nums) {
            if(!set.add(num)) return true;
        }
        return false;
    }
	/*
		Time　O(nlgn) space O(1)
		First sort, then find duplicates.
	
	*/
	public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for(int i = 1; i < nums.length; i++) {
            if(nums[i-1] == nums[i]) return true;
        }
        return false;
    }
}