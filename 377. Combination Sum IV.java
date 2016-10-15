/*
Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.
Follow up:
What if negative numbers are allowed in the given array?
How does it change the problem?
What limitation we need to add to the question to allow negative numbers?
*/

/*
	If negative or zero are allowed, then the result would be infinite.
	For example, [-1, 1] target 1
				 we can have [1], [-1, 1, 1], [-1, -1, 1, 1, 1], [-1, -1, -1, 1, 1, 1, 1]...
	(1) We can have a bound for the size of the solution, i.e. the depth.
	(2) We can assume numbers can only be used once.
*/

/*
	Method 1: Bruce Force (TLE)

*/
public int combinationSum4(int[] nums, int target) {
	Arrays.sort(nums);
	return dfs(nums, target);
}
private int dfs(int[] nums, int target) {
	if(target == 0) return 1;
	int count = 0;
	for(int index = 0; index < nums.length && target - nums[index] >= 0; index++) {
		count += dfs(nums, target - nums[index]);
	}
	return count;
}

/*
	Method 2: DFS with memorization.
	
	Space O(target) Time O(max(target * n, nlgn)) 
	
	We can find that there are a lot of duplicate calculations.
	Can be reduced by memorization.
	
	Note that we can also not to sort the array, if n is small.

*/
public int combinationSum4(int[] nums, int target) {
	Arrays.sort(nums);
	return combinationSum4(nums, target, new HashMap<Integer, Integer>());
}
private int combinationSum4(int[] nums, int target, HashMap<Integer, Integer> counts) {
	if(target == 0) return 1;
	if(counts.containsKey(target)) return counts.get(target);
	int count = 0;
	for(int index = 0; index < nums.length && target - nums[index] >= 0; index++) {
		count += combinationSum4(nums, target - nums[index], counts);
	}
	counts.put(target, count);
	return count;
}

/*
	Method 3: DP
	
	Space O(target) Time O(max(target * n, nlgn))
	
	Actually it is the idea of DFS with memorization, but in iterative form.
	However, actually this DP has worse best time/space complexity compared with method 2.
	
	dp[target] = the number of all possible combinations for target
	
	dp[target] = sum(dp[target - x]) for all possible value x in nums array.

	Boundary: 
		dp[i] = 0 for i < 0
		dp[0] = 1
*/
public int combinationSum4(int[] nums, int target) {
	Arrays.sort(nums);
	int[] counts = new int[target + 1];
	counts[0] = 1;
	for(int curTarget = 1; curTarget <= target; curTarget++) {
		for(int num : nums) {
			if(curTarget - num < 0) break;
			counts[curTarget] += counts[curTarget - num];
		}
	}
	return counts[target];
}