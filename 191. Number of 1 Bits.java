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
		A better way if 1 is sparse 
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
	
	/*
		A better way if 1 is dense.
		Divide and conquer
	*/
	public static int hammingWeight(int n) {
        n = ((n & 0xAAAAAAAA) >>> 1) + (n & 0x55555555); //put count of each  2 bits into those  2 bits 
        n = ((n & 0xCCCCCCCC) >>> 2) + (n & 0x33333333); //put count of each  4 bits into those  4 bits 
        n = ((n & 0xF0F0F0F0) >>> 4) + (n & 0x0F0F0F0F); //put count of each  8 bits into those  8 bits 
        n = ((n & 0xFF00FF00) >>> 8) + (n & 0x00FF00FF); //put count of each  16 bits into those  16 bits 
        n = ((n & 0xFFFF0000) >>> 16) + (n & 0x0000FFFF); //put count of each  32 bits into those  32 bits 
        return n;
    }
}