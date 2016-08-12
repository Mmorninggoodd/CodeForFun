/* MORE
Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
*/

public class Solution {
    /*
        Consider several ascpects: 
        1. when nums[mid] == target, make sure bounds won't pass it (i.e. left = mid or right = mid).
            When finding left bound, we want to use right = mid, so that left bound won't pass leftmost ties (now right can move to middle of the range). For the same reason, when finding right bound, we want to use left = mid.
        2. Left round or right round issue:
            mid = (left + right) / 2 -> must use left = mid + 1. Otherwise it will go into infinite loop.
            mid = (left + right + 1) / 2 -> right = mid - 1
        3. while(left < right) or while(left <= right):
            If use left <= right, their value might go out of range, and case with only one elment will go into infinite loop when you has left = mid or right = mid.
			If use left < right, both left and right will converge into same value (if not starting from left > right). But be careful about case of only one elment which will not enter the loop.
			So we can only use left < right here.
        Combining (1) and (2), we only have one option to find their left and right bound.
    */
    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
		// Find left bound
        while(left < right) {
            int mid = (left + right) / 2;
            if(nums[mid] < target) left = mid + 1;
            else right = mid;
        }
		// here left == right
        if(nums[left] != target) return new int[]{-1,-1};
        int leftBound = left;
        right = nums.length - 1;
		// Finding right bound
        while(left < right) { 
            int mid = (left + right + 1) / 2;
            if(nums[mid] > target) right = mid - 1;
            else left = mid;
        }
		// here left == right
        return new int[]{leftBound, left};
    }
}