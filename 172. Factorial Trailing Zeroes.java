/*

Given an integer n, return the number of trailing zeroes in n!.

Note: Your solution should be in logarithmic time complexity.
*/

public class Solution {
	/*
		only consider 2*5 pairs.
		There are plenty of multiples of 2, so we only focus on multiples of 5.
		each multiple of 5 can create a 0.
		each multiple of 5^2 can create two 0s.
		each multiple of 5^3 can create three 0s...
	*/
    public int trailingZeroes(int n) {
        int sum = 0;
        while(n != 0) {
            n /= 5;
            sum += n;
        }
        return sum;
    }
}