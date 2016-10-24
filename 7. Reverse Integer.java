/*

Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321

click to show spoilers.

Have you thought about this?
Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!

If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.

Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows. How should you handle such cases?

For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.

*/



public class Solution {
	/*
		A simple way. Use long to avoid overflow. (abs(Integer.MIN_VALUE) overflows)
	
	*/
    public int reverse(int x) {
        long sign = x > 0 ? 1 : -1;
        long res = 0;
        long x_l = (long) x * sign;
        while(x_l != 0) {
            res = res * 10 + x_l % 10;
            x_l /= 10;
        }
        res = res * sign;
        return res == (int) res ? (int) res : 0;  // detect whether overflow
    }
	
	/*
		A better way to detect overflow.
		Detect it on the fly.
	*/
	public int reverse(int x) {
        int res = 0;
        while(x != 0) {
            int tmp = res;
            res = res * 10 + (x % 10);
            if(res / 10 != tmp) return 0;  // try to reverse the process, if fail then overflowed 
            x /= 10;
        }
        return res;
    }
}