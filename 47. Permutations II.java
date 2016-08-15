/*

Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]

*/

public class Solution {
	/*
		Similar with #46 Permutations
		
		Each time we choose one number from remaining choices for current positioin.
		
		
		Only need to try to avoid duplicates:
		There are two ways to achieve this.
		(1) Use a HashSet to record used number for current position. (8ms 33%)
		(2) Just go through nums array from current position to used positioins (3ms 95%)
		
		In theory, only consider the part of avoiding duplicates, method 1 takes O(n*n) time O(n^2) space, because this backtracking (dfs) algorithm at most has n layers of call stack at the same time, and each HashSet takes O(n) space, so total space is O(n^2). For time complexity, we need n^n HashSets (each possibility of each positioin need one), for each hashset, we use at most O(n) add and contains operations. So totoal time complexity is exponential O(n^(n+1)).
		
		While the second method doesn't need any extra space to check duplicates, but it requires O(n) time for each check, so each possibility of each positioin needs at most O(n^2) time. So total time is O(n^(n+2))
	
	*/
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        permuteUnique(nums, res, 0);
        return res;
    }
    public void permuteUnique(int[] nums, List<List<Integer>> res, int index) {
        if(index == nums.length) {
            List<Integer> list = new ArrayList<>();
            for(int num : nums) list.add(num);
            res.add(list);
            return;
        }
        //HashSet<Integer> used = new HashSet<>();  // Commented-out lines are HashSet method 
        for(int i = index; i < nums.length; i++) {
            //if(used.contains(nums[i])) continue;
            if(isDuplicate(nums, index, i)) continue;  // Method 2
            swap(nums, i, index);
            permuteUnique(nums, res, index + 1);
            swap(nums, i, index);
            //used.add(nums[i]);
        }
    }
    private boolean isDuplicate(int[] nums, int start, int end) {
        while(start < end) if(nums[start++] == nums[end]) return true;
        return false;
    }
        
    private void swap(int[] nums, int i, int j){
        if(i == j) return;
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}