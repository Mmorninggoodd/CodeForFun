/*
Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.

*/


public class Solution {
	
	/*
		The key here is: Use bit shift to find the muliples of divisor in O(1) time.
		Some cases needs to consider: 
			1. When finding muliples (shift) of divisor, it is easy to overflow
			2. Integer.MIN_VALUE: when become positive, it will overflow
			3. Divide with 0 -> overflow
			4. Signs of dividend and divisor
		
		Two ways to handle these cases:
			1. Use long for each operations
			2. Transform them into negative, then be careful about overflow when finding muliples
	*/
	
	/*
		Method 1: Use long.
	*/
	public int divide(int dividend, int divisor) {
        if(divisor == 0|| (dividend == Integer.MIN_VALUE && divisor == -1)) return Integer.MAX_VALUE;
        boolean neg = (dividend < 0) ^ (divisor < 0);
        int res = 0;
        long ldividend = (long) dividend;
        long ldivisor = (long) divisor;
        ldividend = ldividend < 0 ? -ldividend : ldividend;
        ldivisor = ldivisor < 0 ? -ldivisor : ldivisor;
        if(ldivisor == 1) return neg? (int) -ldividend : (int)ldividend;
        while(ldividend >= ldivisor){
            int shift = 0;
            while(ldividend >= (ldivisor << shift + 1)){
                shift++;
            }
            ldividend -= (ldivisor << shift);
            res += (1 << shift);
        }
        return neg ? -res : res;
    }
	
	/*
		Method 2: Avoid using long.
	*/
    public int divide(int dividend, int divisor) {
        if(divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) return Integer.MAX_VALUE;
        if(divisor == 1) return dividend;
        boolean neg = (dividend < 0) ^ (divisor < 0);
        // Turn everything to negative to avoid overflow when abs(MIN_VALUE) and avoid long
        dividend = dividend < 0 ? dividend : -dividend;
        divisor = divisor < 0 ? divisor : -divisor;
        int res = 0;
        while(dividend <= divisor) {
            int shift = 0;
            while(dividend <= (divisor << (shift + 1)) && shift < 31 && divisor << (shift + 1) < 0) {
                shift++;
            }
            dividend -= (divisor << shift);
            res += (1 << shift);
        }
        return neg ? -res : res;
    }
	
	/*
		Use binary search
	*/
	public static int divide(int dividend, int divisor) {
        if(divisor == 0) return Integer.MAX_VALUE;
        long lDividend = Math.abs((long) dividend), lDivisor = Math.abs((long) divisor);
        if(lDividend < lDivisor) return 0;
        long left = 1, right = lDividend;
        while(left < right) {
            long mid = (left + right + 1) >>> 1;
            if(mid * lDivisor > lDividend) right = mid - 1;
            else left = mid;
        }
        left = (((dividend < 0) ^ (divisor < 0)) ? -1 : 1) * left;  // must assign sign first, otherwise the following overflow task cannot detect Integer.MIN_VALUE / -1
        if( (long) ((int) left) != left) return Integer.MAX_VALUE;
        return (int) left;
    }
}