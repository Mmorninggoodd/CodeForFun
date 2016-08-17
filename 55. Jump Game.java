/*

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.

*/

public class Solution {
	/* 2ms 73%
		O(n) time O(1) space
		Just record current reachable index.
		Only increment inside reachable range.
	*/
    public boolean canJump(int[] nums) {
        for(int i = 0, reach = 0; i <= reach; i++) {
            if(nums[i] + i > reach) reach = nums[i] + i;
            if(reach >= nums.length - 1) return true;
        }
        return false;
    }
	
	/* 1ms 96%
		Similar idea, just jump from back to front
		Actually method 1 should be better, since expected time complexity of both methods are identical, but method 1 is easier to code. Probably many test cases fails in latter part of the array.
	*/
	public boolean canJump(int[] nums) {
       if(nums.length < 2) return true;
       
       for(int curr = nums.length-2; curr>=0;curr--){
           if(nums[curr] == 0){
               int neededJumps = 1;
               while(neededJumps > nums[curr]){
                   neededJumps++;
                   curr--;
                   if(curr < 0) return false;
               }
           }
       }
       return true;
    }
}