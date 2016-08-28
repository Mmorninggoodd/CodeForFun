/*

Given an array of integers, every element appears twice except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

*/

public class Solution {

	/*
		A simple solution would be using a hashset, but costs O(n) space
		
		Another easy solution would be Bit operation.
		Utilize the equations:
			1 ^ 1 = 0
			1 ^ 0 = 1
		Also, XOR is commutative.
		So if we do this for each number, then those number exists twice would eliminate itself.
	*/
    public int singleNumber(int[] nums) {
        int single = 0;
        for(int num : nums) {
            single ^= num;
        }
        return single;
    }
}