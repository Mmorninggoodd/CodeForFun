/*

Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note: The solution set must not contain duplicate quadruplets.

For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]

*/

public class Solution {
	
	/*
		Time O(n^3)
		Tricks here is avoid duplicates and skip too small or too large
	*/
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < nums.length - 3; i++) {
            if(nums[i] * 4 > target) break;
            for(int j = i + 1; j < nums.length - 2; j++) {
                if(nums[i] + nums[j] + nums[nums.length - 2] + nums[nums.length - 1] < target) continue;
                if(nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                int left = j + 1, right = nums.length - 1;
                while(left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if(sum == target) {
                        while(left < right && nums[left] == nums[left + 1]) left++;
                        while(left < right && nums[right] == nums[right - 1]) right--;
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++; right--;
                    } 
                    else if(sum > target) right--;
                    else left++;
                }
                while(j < nums.length - 3 && nums[j] == nums[j + 1]) j++;
            }
            while(i < nums.length - 4 && nums[i] == nums[i + 1]) i++;
        }
        return res;
    }
}