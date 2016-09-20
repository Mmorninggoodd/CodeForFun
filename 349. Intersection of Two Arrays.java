/*

Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

Note:
Each element in the result must be unique.
The result can be in any order.

*/

public class Solution {
	/*
		Two HashSets.
		O(n) space & time.
	*/
    public int[] intersection(int[] nums1, int[] nums2) {
         Set<Integer> exist = new HashSet<>(), result = new HashSet<>();
         for(int num : nums1) exist.add(num);
         for(int num : nums2) {
             if(exist.contains(num)) result.add(num);
         }
         int[] res = new int[result.size()];
         int index = 0;
         for(int num : result) {
             res[index++] = num;
         }
         return res;
    }
}