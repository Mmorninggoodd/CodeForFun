/*

Given a collection of integers that might contain duplicates, nums, return all possible subsets.

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]

*/

public class Solution {
	/*
		Use solution of #78 Subsets (although can use backtracking/dfs as well)
		Change start to avoid duplicates here
		
		example [1,2,2]
		
		1st iteration: []
		2nd (add 1): [] [1]
		3rd (add 2): [] [1] [2] [1,2] 
		4nd (add 2): [] [1] [2] [1,2] [2] [1,2] [2,2] [1,2,2] 
		
		We can find that duplicates come from that [] [1] was added 2 twice in 3rd and 4nd iteration respectively, so we can record the size before last iteration, if next number is the same as previous one, we don't add this number twice.
		
	
	*/
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);  // need sort
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(new ArrayList<Integer>());
        for(int i = 0, start = 0; i < nums.length; i++) {
            int size = res.size();
            if(i == 0 || nums[i] != nums[i-1]) start = 0;  // If not duplicate, then start from 0
            for(int j = start; j < size; j++) {
                List<Integer> list = new ArrayList<>(res.get(j));
                list.add(nums[i]);
                res.add(list);
            }
            start = size;  // record size before this iteration
        }
        return res;
    }
}