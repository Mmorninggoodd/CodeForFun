/*
Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [2, 3, 6, 7] and target 7, 
A solution set is: 
[
  [7],
  [2, 2, 3]
]
*/
public class Solution {

	/*
		This question can be solved by backtracking or DP.
		DP would use a lot of space and is not faster than backtracking here.
		
		Here given two version of backtracking.
		Time/space: exponential
		
		Backtracking version 1: (5ms 74%)
		Consider start index of solution each time.
	*/
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSum(candidates, res, new ArrayList<>(), target, 0);
        return res;
    }
    private static void combinationSum(int[] candidates, List<List<Integer>> res, List<Integer> curList, int target, int start) {
        if(target == 0) res.add(new ArrayList<>(curList));
        //else if(target < 0) return;
        else {
			// Use target >= candidates[i] to prune branches
            for(int i = start; i < candidates.length && target >= candidates[i]; i++) {
                curList.add(candidates[i]);
                combinationSum(candidates, res, curList, target - candidates[i], i); // Use i to allow duplicates
                curList.remove(curList.size() - 1);
            }
        }
    }
	
	/*
		Backtracking version 2:  6ms (63%)
		Consider each element as chosen or not chosen each time.
	*/
	private static void combinationSum(int[] candidates, List<List<Integer>> res, List<Integer> curList, int target, int index) {
        if(target == 0) res.add(new ArrayList<>(curList));
        else if(index > candidates.length - 1 || target < candidates[index]) return;
        else {
			combinationSum(candidates, res, curList, target, index + 1); // skip current element
			int i = 1;
			for(; i * candidates[index] <= target; i++) { // choose current element, in order to avoid duplicate solution, we have to iterate all possible multiples of this element here.
				curList.add(candidates[index]);
				combinationSum(candidates, res, curList, target - i * candidates[index], index + 1);
			}
			for(; i > 1; i--) curList.remove(curList.size() - 1);
		}
    }
}