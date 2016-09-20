/*

Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

Note:
Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
Follow up:
What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?

*/

public class Solution {
	/*
		Unsorted
		O(n) time & space
	*/
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> counts1 = new HashMap<>(), counts2 = new HashMap<>();
        for(int num : nums1) {
            counts1.put(num, counts1.getOrDefault(num, 0) + 1);
        }
        int size = 0;
        for(int num : nums2) {
            if(counts1.containsKey(num)) {
                int count = counts1.get(num);
                if(count == 1) counts1.remove(num);
                else counts1.put(num, count - 1);
                counts2.put(num, counts2.getOrDefault(num, 0) + 1);
                size++;
            }
        }
        int[] res = new int[size];
        for(Map.Entry<Integer, Integer> entry : counts2.entrySet()) {
            for(int i = 0; i < entry.getValue(); i++) {
                res[--size] = entry.getKey();
            }
        }
        return res;
    }
	
	/*
		Follow up: If sorted before.
		Then just two pointers, like merge sort.
		O(n+m) time
	*/
	public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i1 = 0, i2 = 0;
        ArrayList<Integer> list = new ArrayList<>();
        while(i1 < nums1.length && i2 < nums2.length) {
            if(nums1[i1] < nums2[i2]) i1++;
            else if(nums1[i1] > nums2[i2]) i2++;
            else {
                list.add(nums1[i1]);
                i1++;
                i2++;
            }
        }
        int[] res = new int[list.size()];
        int i = 0;
        for(int num : list) res[i++] = num;
        return res;
    }
	
	/*
		What if nums1's size is small compared to nums2's size? Which algorithm is better?
		If nums1's size is very small, then we can use binary search on nums2.
		Time O(nlgm) where n and m are sizes of nums1 and nums2, respectively.		
	*/
	
	public static int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        ArrayList<Integer> list = new ArrayList<>();
        int left = 0, right = nums2.length - 1;
        for(int num : nums1) {
            if(left > right) break;
            left = binarySearch(nums2, num, left, right);  // can narrow search range next time
            if(left < 0) left = - (left + 1);
            else {
                left++;
                list.add(num);
            }
        }
        int[] res = new int[list.size()];
        int i = 0;
        for(int num : list) res[i++] = num;
        return res;
    }
    private static int binarySearch(int[] num, int target, int left, int right) {
        while(left < right) {
            int mid = (left + right) >>> 1;
            if(num[mid] >= target) right = mid;
            else left = mid + 1;
        }
        if(num[left] != target) return - left - 1;
        return left;
    }
	
	/*
	What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
	
	Use external merge sort.
	*/
}