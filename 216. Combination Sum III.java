/*

Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.


Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]

Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]

*/
public class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        combinationSum3(n, k, 1, res, new ArrayList<>());
        return res;
    }
    private void combinationSum3(int target, int k, int start, List<List<Integer>> res, List<Integer> curList) {
        if(target == 0 && k == 0) {
            res.add(new ArrayList<>(curList));
            return;
        }
        if(k == 0) return;
        for(int i = start; i <= 9 - k + 1; i++) {
            int newTarget = target - i;
			if(newTarget < 0 || newTarget < (i+1 + i+1+k-2) * (k-1) / 2) break; // new target is too small
			if(newTarget > (9 + 9-k+2) * (k-1) / 2) continue; // too large that it's impossible for remaining to sum up to target
            curList.add(i);
            combinationSum3(newTarget, k - 1, i + 1, res, curList);
            curList.remove(curList.size() - 1);
        }
    }
}