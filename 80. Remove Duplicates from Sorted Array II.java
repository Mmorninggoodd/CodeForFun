/*MORE

Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.

*/

public class Solution {
	/* 1ms
		A bit tricky.
		2 can be changed to any number on the thrid line so that it can work for any variations of this problem
	*/
    public int removeDuplicates(int[] nums) {
        int len = 0;
        for(int num : nums) {
            if(len < 2 || nums[len - 2] != num) nums[len++] = num;
        }
        return len;
    }
}