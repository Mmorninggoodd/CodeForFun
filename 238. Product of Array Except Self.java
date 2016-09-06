/*

Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Solve it without division and in O(n).

For example, given [1,2,3,4], return [24,12,8,6].

Follow up:
Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)

*/

public class Solution {
	/*
		O(1) space O(n) time
		
		Fist loop:
		product:    1         a1    a1*a2    a1*a2*a3
		
		Second loop:
		product: a2*a3*a4   a3*a4   a4          1
		
		Multiple them:
				a2*a3*a4  a1*a3*a4  a1*a2*a4  a1*a2*a3
	*/
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int product = 1;
        for(int i = 0; i < nums.length; i++) {
            res[i] = product;
            product *= nums[i];
        }
        product = 1;
        for(int i = nums.length - 1; i >= 0; i--) {
            res[i] *= product;
            product *= nums[i];
        }
        return res;
    }
}