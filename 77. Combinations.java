/*

Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]

*/

public class Solution {
	/* 4ms 91%
		A typical DFS/backtracking problem. Exponential time and space.
		
		Here we can directly use curList.
	*/
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        combine(1, n, k, res, new ArrayList<Integer>());
        return res;
    }
    private void combine(int start, int n, int remaining, List<List<Integer>> res, List<Integer> curList) {
        if(remaining == 0) {  // already have k elements
            res.add(new ArrayList<Integer>(curList));
            return;
        }
        for(int i = start; i <= n - remaining + 1; i++) { // n - remaining + 1 to early stop
            curList.add(i);
            combine(i + 1, n, remaining - 1,  res, curList);
            curList.remove(curList.size() - 1);
        }
    }
	
	
	/* 19ms 72%
		Another version, similar with solution of permutations
		But here need a start to avoid duplicate [1,2] and [2,1]
	
	*/
    public List<List<Integer>> combine(int n, int k) {
        int[] nums = new int[n];
        for(int i = 1; i <= n; i++) nums[i-1] = i;
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        combine(nums, 0, 0, k, res);
        return res;
    }
    private void combine(int[] nums, int added, int start, int k, List<List<Integer>> res) {
        if(added == k) {  // already added k elements
            List<Integer> list = new ArrayList<>(k);
            for(int i = 0; i < k; i++) list.add(nums[i]);
            res.add(list);
            return;
        }
        for(int i = start; i < nums.length; i++) {  
            swap(nums, added, i);
            combine(nums, added + 1, i + 1, k, res); // start from added + 1 to avoid duplicates
            swap(nums, added, i);
        }
    }
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i]; nums[i] = nums[j]; nums[j] = tmp;
    }
}