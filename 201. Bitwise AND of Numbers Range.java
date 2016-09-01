/*

Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

For example, given the range [5, 7], you should return 4.

*/
public class Solution {
	/*
		Time O(32) Space O(1)
		When m != n, it means there are at least one odd and one even number. The bitwise AND of them must has 0 in the last bit.
		
		So we can analyze one bit each time until they become the same.
	*/
    public int rangeBitwiseAnd(int m, int n) {
        int shift = 0;
        while(m != n) {
            m >>= 1;
            n >>= 1;
            shift++;
        }
        return m << shift;
    }
}