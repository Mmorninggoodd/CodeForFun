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
		
		Idea of DP:
		dp[i] = all possible combinations that sums up to i
		dp[i] = all combinations from (dp[i-j] * dp[j]) for all 1 <= j <= i / 2  // cannot prevent duplicates
		
		Issues:
		1. This would be costly when target is very large. 
		2. Might contains duplicates. For example, {2,3,6,7}, target = 7, will generate {2,2,3} from (4,3) and {3,2,2} from (5,2) and {7}
		3. Need two loop that iterate O(target^2) times.
		
		Solution:
		1. Use HashMap<Integer, List<Integer>> to save memory
		2. dp[i] = (dp[i - candidates[j]] * candidates[j]) only for j such that candidates[j] < i
					and candidates[j] if candidates[j] == i
		so we don't only need to iterate O(target * number of candidates) times
		3. Only add list of dp[i - candidates[j]] when the first element of list >= candidates[j]
		this will prevent those duplicate cases (make sure they are sorted in ascending, can remove (3,2,2) cases)
		
	*/
	public static List<List<Integer>> combinationSum(int[] cands, int target) {
        Arrays.sort(cands);
        Map<Integer, List<List<Integer>>> dp = new HashMap<>();
        for (int i = 1; i <= target; i++) { // run through all targets from 1 to target
            List<List<Integer>> newList = new ArrayList<>(); // combs for curr i
            // run through all candidates <= i
            for (int j = 0; j < cands.length && cands[j] <= i; j++) {
                // special case when curr target is equal to curr candidate
                if (i == cands[j]) newList.add(Collections.singletonList(cands[j]));
                    // if current candidate is less than the target use prev results
                else if(dp.containsKey(i - cands[j])) {
                    for (List<Integer> l : dp.get(i - cands[j])) {
                        if (cands[j] <= l.get(0)) {
                            List<Integer> cl = new ArrayList<>();
                            cl.add(cands[j]); cl.addAll(l);
                            newList.add(cl);
                        }
                    }
                }
            }
            dp.put(i, newList);
        }
        return dp.get(target);
    }
	/*
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