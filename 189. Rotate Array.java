/*

Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.

[show hint]

Hint:
Could you do it in-place with O(1) extra space?
Related problem: Reverse Words in a String II

*/

public class Solution {
	/*
		Method 1: Similar with 186. Reverse Words in a String II
		First reverse whole array,
		then reverse [0:k-1] and [k:end] separately
		O(1) space O(n) time
	*/
    public void rotate(int[] nums, int k) {
        k %= nums.length;   // avoid k >= nums.length
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    private void reverse(int[] nums, int start, int end) {
        for(; start < end; start++, end--) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
        }
    }
	
	/*
		Method 2: Directly move them into correct index, then move replaced number to correct place.
		O(1) space O(n) time
	*/
	public void Rotate(int[] nums, int k) {
		if(nums.Length == 0 || k % nums.Length == 0) return;
		int start = 0, i = start, curNum = nums[i], count = 0;
		while(count < nums.Length){
			i = (i + k) % nums.Length;  // correct index
			int tmp = nums[i];
			nums[i] = curNum;  // copy to correct place
			if(i == start){    // complete a loop, need to advance
				start++;
				i = start;
				curNum = nums[i];
			}
			else curNum = tmp;
			count++;
		}
	}
	
	/*
		Other methods need temporal array which takes O(n) space 
	*/
}