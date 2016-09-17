/*
median of two sorted arrays

There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

Example 1:
nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:
nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5

*/

public class Solution {
    /* 
        Divide nums1 and nums2 into left part (nums1[0:i-1] and nums2[0:j-1]) and right part (nums1[i:n] and nums2[j:m])
        So valid range of i is [0,m], where 0 means all elements in nums1 are in the right part, m means all in left part.
        
        In order to find median, it needs to satisfy two conditions:
        (1) len(left) = len(right), i.e., i + j = n + m - i - j (even), or i + j = n + m -i - j + 1 (odd)
        (2) max(left) <= min(right)
		
		Then median = (max(left) + min(right)) / 2.0 (even) or max(left) (odd)
        
        So we only need to change i, and j will change accordingly: j = (n + m + 1) /2 - i (from condition (1))
        Then we only consider five cases: i = 0 or i = n or j = 0 or j = m or 0 < i < n.
        For 0 < i < n: condition (2) becomes (a) nums1[i - 1] <= nums2[j] and (b) nums2[j - 1] <= nums1[i]
        For other cases, we can get answer directly.
        Also, we need to consider odd and even cases.
    */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        if(n > m) return findMedianSortedArrays(nums2, nums1);
        // make sure num1 is the shorter one so that j = (n + m + 1) /2 - i won't be negative
        
        for(int i_min = 0, i_max = n, half_len = (n + m + 1) /2; i_min <= i_max;) {
            int i = (i_min + i_max) / 2;
            int j = half_len - i;
            if(i > 0 && j < m && nums1[i - 1] > nums2[j]) i_max = i - 1;  // Check condition (2.a)
            else if(j > 0 && i < n && nums2[j - 1] > nums1[i]) i_min = i + 1;  // Check condition (2.b)
            else {
                int max_left = Math.max(i > 0 ? nums1[i - 1] : Integer.MIN_VALUE, j > 0 ? nums2[j - 1] : Integer.MIN_VALUE);
                int min_right = Math.min(i < n ? nums1[i] : Integer.MAX_VALUE, j < m ? nums2[j] : Integer.MAX_VALUE);
                if((n + m) % 2 == 1) return max_left; // odd case. Left part contains one more element because (n + m + 1) / 2
                else return (max_left + min_right) / 2.0;  // even case
            }
        }
        return 0.0; // In order to pass compiler
    }
}