/*

Given an integer, write a function to determine if it is a power of two.
*/

public class Solution {
	/*
		n & -n get last set bit
	*/
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & -n) == n;
    }
	/*
		n & (n - 1) unset last set bit
	*/
	public boolean isPowerOfTwo(int n) {
		return n > 0 && (n & (n - 1)) == 0;
	}
}