/*

Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.

*/

public class Solution {
	/* 1ms 98%
	
		2 pass scans.
		Image whole array can be divided into different areas.
		0 just like a delimiter
		One area can be potentially large when it has even number of negative numbers.
		So scan from two ends, we can get all possible maximum product of subarray.
	*/
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, product = 1;
        for(int i = 0; i < nums.length; i++) {  // from left to right
            product *= nums[i];
            if(product > max) max = product;
            if(product == 0) product = 1;
        }
        product = 1;
        for(int i = nums.length - 1; i >= 0 ; i--) {  // from right to left
            product *= nums[i];
            if(product > max) max = product;
            if(product == 0) product = 1;
        }
        return max;
    }
}