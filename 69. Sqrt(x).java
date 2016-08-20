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
        int left = 1, right = x/2;
        while(left < right){
            int mid = left + (1 + right - left)/2;
            if(x/mid >= mid) left = mid; // mid*mid <= x 
            else right = mid - 1;
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
	
	/*
		Avoid Long version
	*/
	public int mySqrt(int x) {
        if(x <= 1) return x;  // avoid divide by 0 latter
        int left = 1, right = x/2;
        while(left <= right) { // there must exists a solution
            int mid = (left + right) / 2;
            if(mid <= x / mid) {
                if(mid + 1 > x / (mid + 1)) return mid;
                left = mid + 1;
            }
            else right = mid -1;
        }
        // No case should arrive here
        return -1;
    }
}