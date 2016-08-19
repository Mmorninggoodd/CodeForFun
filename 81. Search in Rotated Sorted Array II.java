/*

Follow up for "Search in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?

Write a function to determine if a given target is in the array.

*/

/*
	Duplicates would affect time complexity.
	A small example:
	[1,1,1,1,1,2,1,1,1]  looking for 2 or any number other than 1
	For first iteration, nums[left] == nums[mid] == nums[right] == 1, you won't know which direction to go.
	
	So what you can do is only to narrow the range by one step, i.e. left++ or right--.
	So time complexity is O(n)
*/

public class Solution {
	/*
		Almost the same as first solution of #33 Search in Rotated Sorted Array
		except we only compare nums[right] here and consider nums[mid] == nums[right] case
	
	*/
    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while(left <= right) {
            int mid = (left + right) >>> 1;
            if(target == nums[mid]) return true;
            if(nums[mid] > nums[right]) {  // need to use nums[right] instead of nums[mid] > nums[left] otherwise [2,1,1] (target: 2 when left++) or [2,2,1] (1 when right--) fails
                if(target >= nums[left] && target < nums[mid]) right = mid - 1;
                else left = mid + 1;
            }
            else if(nums[mid] < nums[right]) {
                if(target > nums[mid] && target <= nums[right]) left = mid + 1;
                else right = mid - 1;
            }
			// nums[mid] == nums[right]
            else right--; // cannot use left++ since we use nums[right] before, otherwise [2,1,1] (2) fails
        }
        return false;
    }
}