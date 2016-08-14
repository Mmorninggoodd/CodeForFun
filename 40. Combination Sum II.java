/*

Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
A solution set is: 
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]

*/

public class Solution {
	/*
		Similar with #39.
		Backtracking. Exponential time/space.
		
		The key here is to avoid duplicates.

		
		Example: (without i != start && candidates[i - 1] == candidates[i])
			candidates: 1 1 1 3
			target: 5
			After first recursion, we get three branches: [remaning candidates (target)] (only show succeeded branches)
			-> [1 1 3 (4)] -> [1 3 (4)] -> [3 (3)] -> [(0)](duplicate solutions)
						   -> [3 (3)]   -> [(0)] (duplicate solutions, can be removed)
			-> [1 3 (4)]   -> [3 (3)]   -> [(0)] (duplicate solutions, can be removed from [1 3 (4)])
			-> [3 (4)]     -> [(1)] (failed)
			
			When to skip an element? Satisfy two conditions: (1) Its value is the same as previous element (2) Previous element was not added into current list.
			Why we need condition (2)? For the example below, we only want the first case to succeed, i.e.,
			1 1 1 2 3 target: 4
			For [1 3], we only want to choose the first '1' here.
			For [1 1 2], we only want to choose the first two '1' here.
			Then if we let every solution only consists of 'first several' ties, we can make sure there is only one unique combination for each solution. (i.e. 'first '1'' or 'first two '1'' or 'first three '1'' are unique)
			
			And i != start means candidates[i - 1] is not added to the current list.
	*/
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSum2(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }
    private void combinationSum2(List<List<Integer>> res, List<Integer> list, int[] candidates,  int target, int start) {
        if(target == 0) res.add(new ArrayList<>(list));
        else {
            for(int i = start; i < candidates.length && target >= candidates[i]; i++) {
                if(i != start && candidates[i - 1] == candidates[i]) continue; // must use this to avoid duplicates
                list.add(candidates[i]);
                combinationSum2(res, list, candidates, target - candidates[i], i + 1);
                list.remove(list.size() - 1);
            }
        }
    }
}