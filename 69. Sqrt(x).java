/*

Implement int sqrt(int x).

Compute and return the square root of x.
*/

public class Solution {
	/*
		Just Binary Search. O(lgn)
	
	*/
	/*
		Short version
	
	*/
    public int mySqrt(int x) {
        if(x <= 1) return x;
        int left = 1, right = x / 2;
        while(left < right) {
            int mid = (left + right + 1) >>> 1;
            if(mid > x / mid) right = mid - 1;  // mid * mid > x
            else left = mid;
        }
        return left;
    }
	/*
		Long version
	
	*/
    public int mySqrt(int x) {
        int left = 0, right = x/2;
        while(left <= right) {
            int mid = (left + right) / 2;
            long mul1 = (long) mid * mid;
            long mul2 = ((long) mid+1) * (mid+1);
            if(mul1 <= (long) x && (mul2 > (long) x)) return mid;
            if(mul1 < (long) x) left = mid + 1;
            else right = mid -1;
        }
        return x;
    }
}