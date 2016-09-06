/*

Given a sorted integer array without duplicates, return the summary of its ranges.

For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].

*/

public class Solution {
	/* 0ms 95.10%
		A straight forward solution.
	*/
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if(nums.length == 0) return res;
        int start = 0, end = 0;
        while(end < nums.length) {
            if(end != nums.length - 1 && nums[end + 1] == nums[end] + 1) end++;
            else {
                res.add(toStringRange(nums[start], nums[end]));
                start = end + 1;
                end = start;
            }
        }
        return res;
    }
    private String toStringRange(int start, int end) {
        if(start == end) return String.valueOf(start);
        return String.valueOf(start) + "->" + String.valueOf(end);
    }
}