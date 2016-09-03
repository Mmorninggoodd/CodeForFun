/*

Given an array of integers, every element appears three times except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

*/

public class Solution {
    /* 1ms 87%
    Need a coding system that:
    0 -> 00
    1 -> 01
    2 -> 10
    3 -> 00
         ab
    
    So a complete transaction relationship:
    
    num  a b  ->  a b
     1   0 0      0 1
     1   0 1      1 0
     1   1 0      0 0
     (When num is 0, nothing change)
     
     So a = num & b (when num is 1) + ~num & a (when num is 0)
        b = num & ~a & ~b (when num is 1) + ~num & b (when num is 0)
    */
    public int singleNumber(int[] nums) {
        int a = 0, b = 0;
        for(int num : nums) {
            int tmp = a;
            a = (num & b) + (~num & a);
            b = (num & ~tmp & ~b) + (~num & b);
        }
        return a | b;  // exits once or twice
    }
	
	/*
		Another method
	*/
	public int singleNumber(int[] nums) {
        int a = 0, b = 0;
        for(int num : nums){
            a = (num ^ a) & b;
            b = (num ^ b) | a;
        }
        return b;
    }
}