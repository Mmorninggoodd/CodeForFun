/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

Note:
You can assume that you can always reach the last index.
*/

public class Solution {
	/*
		Simple problem. O(n) time O(1) space
		Before each jump, we search maximum reachable index, then we jump farthest step.
	*/
    public int jump(int[] nums) {
        int count = 0;
        for(int i = 0, reach = 0; reach < nums.length - 1; count++) {
            int curReach = 0;
            for(; i <= reach; i++) { 	// search farthest index can reach from current range (<= reach)
                if(i + nums[i] > curReach) curReach = i + nums[i];
            }
            reach = curReach;
        }
        return count;
    }
}