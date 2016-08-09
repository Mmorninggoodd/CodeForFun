/*
Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

*/

public class Solution {
	// Time O(n^2), space O(1)
	// Similar with 3Sum
    public int threeSumClosest(int[] nums, int target) {
        if(nums.length < 3) throw new RuntimeException();
        Arrays.sort(nums);
        int sum = nums[0] + nums[1] + nums[2];
        for(int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            while(left < right) {
                while(left < right && nums[left + 1] == nums[left]) left++;
                while(left < right && nums[right - 1] == nums[right]) right--;
                int curSum = nums[i] + nums[left] + nums[right];
                if(curSum == target) return target;
                if(curSum > target) right--;
                else left++;
                if(Math.abs(curSum - target) < Math.abs(sum - target)) sum = curSum;
            }
            while(i < nums.length - 1 && nums[i + 1] == nums[i]) i++;
        }
        return sum;
    }
	
	// A similar version but faster
	public int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int len = nums.length;
		if(nums[len - 3] + nums[len - 2] + nums[len - 1] <= target) 
			return nums[len - 3] + nums[len - 2] + nums[len - 1];
		if(nums[0] + nums[1] + nums[2] >= target) 
			return nums[0] + nums[1] + nums[2];
		int sum = nums[0] + nums[1] + nums[2];
        for(int i = 0; i < len - 2; i++) {
			if(nums[i] + nums[len - 1] + nums[len - 2] < target && i < len - 2) {//short cut
				int curSum = nums[i] + nums[len - 1] + nums[len - 2];
				if(Math.abs(curSum - target) < Math.abs(sum - target)) sum = curSum;
				continue;
			}
			if(nums[i] + nums[i + 1] + nums[i + 2] > target) {//short cut
				int curSum = nums[i] + nums[i + 1] + nums[i + 2];
				if(Math.abs(curSum - target) < Math.abs(sum - target)) sum = curSum;
				break;
			}
			
			int left = i + 1, right = nums.length - 1;
            while(left < right) {
                while(left < right && nums[left + 1] == nums[left]) left++;
                while(left < right && nums[right - 1] == nums[right]) right--;
                int curSum = nums[i] + nums[left] + nums[right];
                if(curSum == target) return target;
                if(curSum > target) right--;
                else left++;
                if(Math.abs(curSum - target) < Math.abs(sum - target)) sum = curSum;
            }
            while(i < len - 1 && nums[i + 1] == nums[i]) i++;
		}
		return sum;
	}
}