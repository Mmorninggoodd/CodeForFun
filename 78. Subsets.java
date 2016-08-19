/*

Given a set of distinct integers, nums, return all possible subsets.

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

*/

public class Solution {
	/* 2ms
		A simple iterative solution.
		
		Basic idea: 
		[1,2,3]
		Start from a empty list []
		Then copy previous results and first add first element [1]  ->  [] [1]
		Then second: [] [1]  -> [] [1] [2] [1,2]
		Then third: [] [1] [2] [1,2] -> [] [1] [2] [1,2] [3] [1, 3] [2, 3] [1, 2, 3]
	
	*/
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<Integer>());
        for(int num : nums) {  // Each time add one number
            int size = res.size();  // Record size of previous results
            for(int i = 0; i < size; i++) {
                List<Integer> list = new ArrayList<>(res.get(i));
                list.add(num);
                res.add(list);
            }
        }
        return res;
    }
}