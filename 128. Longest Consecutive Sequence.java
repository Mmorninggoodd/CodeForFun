/*

Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.

*/

public class Solution {
	/*
		First store all numbers in a hashset. (time & space O(n))
		Then iterate all numbers again, try to search longest consecutive range expanding from current number.
		
		A trick here is to remove that number in the set when found so that each number can only be added and removed once. So overall complexity is O(N).
	*/
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int len = 0;
        for(int num : nums) set.add(num);
        for(int num : nums) len = Math.max(len, longestConsecutive(set, num));
        return len;
    }
    private int longestConsecutive(Set<Integer> set, int num) {
        int left = num, right = num;
        while(set.remove(left - 1)) left--;
        while(set.remove(right + 1)) right++;
        return right - left + 1;
    }
}