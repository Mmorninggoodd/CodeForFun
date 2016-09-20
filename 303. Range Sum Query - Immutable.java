/*

Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
Note:
You may assume that the array does not change.
There are many calls to sumRange function.

*/

/*
	Use prefixSum array.
	O(n) construction, O(1) call sumRange
*/
public class NumArray {
    int[] prefixSum;
    public NumArray(int[] nums) {
        this.prefixSum = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            if(i == 0) prefixSum[i] = nums[i];
            else prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        if(i == 0) return prefixSum[j];
        return prefixSum[j] - prefixSum[i-1];
    }
}


// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.sumRange(1, 2);