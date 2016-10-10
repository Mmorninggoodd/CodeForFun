/*

Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

Note that 1 is typically treated as an ugly number.

Hint:

The naive approach is to call isUgly for every number until you reach the nth one. Most numbers are not ugly. Try to focus your effort on generating only the ugly ones.
An ugly number must be multiplied by either 2, 3, or 5 from a smaller ugly number.
The key is how to maintain the order of the ugly numbers. Try a similar approach of merging from three sorted lists: L1, L2, and L3.
Assume you have Uk, the kth ugly number. Then Uk+1 must be Min(L1 * 2, L2 * 3, L3 * 5).

*/

public class Solution {
	/*
		Generate n ugly numbers.
		Need to consider 2*3 3*2 to remove duplicates.
		Time & Space O(n)
	*/
    public int nthUglyNumber(int n) {
        int[] uglyNumbers = new int[n];
        uglyNumbers[0] = 1;
        int index2 = 0, index3 = 0, index5 = 0;
        for(int i = 1; i < n; i++) {
            int next = Math.min(uglyNumbers[index2] * 2, Math.min(uglyNumbers[index3] * 3, uglyNumbers[index5] * 5));
            uglyNumbers[i] = next;
            if(next == uglyNumbers[index2] * 2) index2++;
            if(next == uglyNumbers[index3] * 3) index3++;
            if(next == uglyNumbers[index5] * 5) index5++;
        }
        return uglyNumbers[n - 1];
    }
}