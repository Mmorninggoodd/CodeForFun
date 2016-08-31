/*

Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).

For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.

*/

public class Solution {
    /*
		Just count ones, and unsigned right shift one bit.
	*/
    public int hammingWeight(int n) {
        int sum = 0;
        while(n != 0) {
            sum += n & 1;
            n >>>= 1;
        }
        return sum;
    }
	
	/*
		use n &= (n - 1) to clear one one each time.
	*/
	public int hammingWeight(int n) {
        int sum = 0;
        while(n != 0) {
            sum++;
            n &= (n - 1);
        }
        return sum;
    }
}