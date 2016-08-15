/*

Given a collection of distinct numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

*/

public class Solution {
	/*
		2ms 93%
		A typical problem can be solved by DFS/backtracking
		
		Time and space exponential complexity.
		
		This method won't cost a lot of space, it won't create any list until it want to add it into result.
		This avoid a lot of expensive list operations.
		
		Main idea: each time choose a number for one index from remaining numbers.
	
	*/
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        permute(nums, res, 0);
        return res;
    }
    private void permute(int[] nums, List<List<Integer>> res, int index) {
        if(index == nums.length) { // reach the end
            List<Integer> list = new ArrayList<>();
            for(int num : nums) list.add(num);
            res.add(list);
            return;
        }
        permute(nums, res, index + 1); // keep current number
        for(int i = index + 1; i < nums.length; i++) { // Choose from remaining numbers
            swap(nums, i, index);
            permute(nums, res, index + 1);
            swap(nums, i, index);
        }
    }
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
	
	/*
		4ms 40%
		Iterative method
		
		Main idea: each time insert one certain number into all current lists.
		For each current list, insert into all possible indexes and create new lists.
		
	*/
	public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>()); 
        for(int num : nums) {  	// Each time only insert this number
            List<List<Integer>> newRes = new ArrayList<>();
            for(List<Integer> list : res) {  			 // Insert into every current lists
                for(int i = 0; i <= list.size(); i++) {  // Insert into all possible indexes for this list
                    List<Integer> newList = new ArrayList<>(list);
                    newList.add(i, num);  // This list operation takes O(n)
                    newRes.add(newList);
                }
            }
            res = newRes;
        }
        return res;
    }
}