/*

Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.

*/

public class Solution {
	/*
		Pigeonhole Principle
		
		Each time divide search range (value range, not index range) into two part.
		[left, mid] and [mid, right]
		
		If count of left part > mid (1 based), then there must exists one duplicate number in that range.
		
		Time O(nlgn) Space O(1)
	*/
    public int findDuplicate(int[] nums) {
        int left = 1, right = nums.length - 1;
        while(left < right) {
            int mid = (left + right) >>> 1;
            int count = 0;
            for(int num : nums) 
                if(num <= mid) count++;    // count number of <= mid
            if(count <= mid) left = mid + 1; // compare with mid so that you don't need to consider odd case
            else right = mid;
        }
        return left;
    }
	
	/*
		Find cycle in graph
		https://discuss.leetcode.com/topic/25685/java-o-n-time-and-o-1-space-solution-similar-to-find-loop-in-linkedlist
		
		Thought of index -> (value - 1) as a edge. (value is 1 based)
		So duplicate value will have degree of more than one, which forms a cycle.
		
		Note that we must start from the last element, because the largest value is n - 1.
		
		Time Complexity:
		
		To calculate the Time Complexity the shape of the cycle doesn't matter. It can have a long tail, and a loop towards the end or just a loop from the beginning to the end without a tail. Irrespective of the shape of the cycle, one thing is clear - that the Tortoise can never catch up with the Hare. If the two has to meet, the Hare has to catch up with the Tortoise from behind.

		With that established, consider the two possibilities
		Hare is one step behind Tortoise
		Hare is two step behind Tortoise
		All greater distances will reduce to One or Two. Let us assume always Tortoise moves first  (it could be even other way).

		In the first case were the distance between Hare and Tortoise is one step. Tortoise moves one step forward and the distance between Hare and Tortoise becomes 2. Now Hare moves 2 steps forward meeting up with Tortoise.

		In the second case were the distance between Hare and Tortoise is two steps. Tortoise moves one step forward and the distance between Hare and Tortoise becomes 3. Now Hare moved 2 steps forward which makes the distance between Hare and Tortoise as 1. It is similar to first case which we already proved that both Hare and Tortoise will meet up in next step.

		Let the length of the loop be 'n' and there are 'p' variables before the loop. Hare traverses the loop twice in 'n' moves, they are guaranteed to meet in O(n).

			
		Time O(n) Space O(1)
	*/
	public int findDuplicate(int[] nums) {
        int n = nums.length;
        int slow = n, fast = n;
        do{
            slow = nums[slow - 1];
            fast = nums[nums[fast - 1] - 1];
        } while (slow != fast);  // two pointers meet
        slow = n;
        while(slow != fast) {  // find start point of cycle
            slow = nums[slow - 1];
            fast = nums[fast - 1];
        }
        return slow;
    }
	
}