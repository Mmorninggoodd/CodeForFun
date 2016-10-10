/*
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note: The solution set must not contain duplicate triplets.

For example, given array S = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
*/

public class Solution {
	
	// time O(n^2), space O(n)
	// First select one number nums[i], then run two sum for remaining two numbers
	// finding remaining sum = -nums[i]
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 2; i++) {
            int target = -nums[i];
            int left = i + 1, right = nums.length - 1;
            while(left < right) {
                if(nums[left] + nums[right] == target) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while(right > left && nums[right] == nums[right - 1]) right--;
                    while(right > left && nums[left] == nums[left + 1]) left++;
                    right--; left++;
                }
                else if(nums[left] + nums[right] > target) right--;
                else left++;
            }
            while(i < nums.length - 1 && nums[i] == nums[i + 1]) i++;
        }
        return res;
    }
	
	/*
		Follow up:
		Only find one triple, but only return the triple that the last number's index is the smallest among all valid solutions.
		
		For example, [2,4,5,6,2,5,3,1], 10
		return [2,6,2]  but [5, 5] or other solutions
		
		Cannot sort here. Then just use HashMap.
		We can put numbers into map while we traverse.
		find triple [i,j,k]
	*/
	public static List<Integer> threeSum(int[] nums, int target) {
		Map<Integer, Integer> counts = new HashMap<>();
		for(int k = 1; k < nums.length; k++) {  // traverse from left to right
			counts.put(nums[k], counts.getOrDefault(nums[k], 0) + 1);
			for(int i = 0; i < k - 1; i++) {
				int newTarget = target - nums[i] - nums[k];
				int count = counts.getOrDefault(newTarget, 0);
				if((newTarget == nums[i] && count >= 2) || (newTarget != nums[i] && count > 0)) {
					return Arrays.asList(nums[i], newTarget, nums[k]);
				}
			}
		}
		return new ArrayList<>();
	}
}