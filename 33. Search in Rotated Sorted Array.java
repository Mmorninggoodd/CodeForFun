/*
MORE
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

*/
public class Solution {
	
	/*
		One while loop version.
		
		There are two cases [1,2,3,4] and [3,4,1,2]
		
		When nums[mid] >= nums[left], we only know [left, mid] is sorted, if target is inside this range, we can just narrow the search range to it. If target is not inside this range, then target must on the right part of search range (now target can smaller than nums[mid] when target on the right part of rotation array, it can also larger, e.g. [4,5,6,1,2] target can be 6 or 1, when left is 4, mid is 5) 
		
		Similarly, we can analysis case that nums[mid] <= nums[right].
	
	*/
	public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        while(left <= right) {  // safely use =, since all shift latter are all have +1 or -1
            int mid = (left + right) >>> 1;  // A safer and faster version
            if(nums[mid] == target) return mid;
            if(nums[mid] >= nums[left]) { // two cases: 1,2,3,4,5 or 3,4,5,1,2 [left, mid] must be sorted
                if(target >= nums[left] && target < nums[mid]) right = mid - 1;  
                else left = mid + 1;
            }
            else if(nums[mid] <= nums[right]) {  // [mid, right] must be sorted
                if(target > nums[mid] && target <= nums[right]) left = mid + 1;
                else right = mid - 1;
            }
        }
        return -1;
    }
	
	
	/*
		Time O(lgn) space O(1)
		Two Binary Searchs
		First find pivot element,
		then use rotation for mapping to combine two cases: [1,2,3,4] and [3,4,1,2]
		to find target element
	*/
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return -1;
        }
        // Find pivot
        int left = 0, right = nums.length -1;
        while(left < right) {  // if use left <= right, case [1] would go into infinite loop (because of using right = mid)
            int mid = (left + right) >>> 1;  // left round -> must use left = mid + 1 to avoid infinite loop
            if(nums[mid] > nums[right]) left = mid + 1; // if use nums[mid] > or >= nums[left], case [1,2] fails.
            else right = mid;    // if use right = mid - 1, would probably miss pivot, since we are using nums[right] to compare, not true pivot
        }
        int rotation = left;
        // Now nums[rotation] is the smallest one
        left = 0; 
        right = nums.length - 1;
        // Use rotation to avoid handling seperately two conditions: [1,2,3,4] and [3,4,1,2]
        while(left <= right) {     // if use left < right, case [1] target 1 fails. (Not afraid of infinite loop here since we don't have right = mid or left = mid no matter which direction to round)
            int mid = (left + right) / 2;  // left round -> left = mid + 1
            int realmid = (mid + rotation) % nums.length;
            if(nums[realmid] == target) return realmid; // make sure to return real index
            if(nums[realmid] > target) right = mid - 1;  // here we have actual target value to compare, so we can use mid - 1
            else left = mid + 1;
        }
        return -1;
    }

}