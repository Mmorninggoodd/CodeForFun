/*

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Therefore, return the max sliding window as [3,3,5,5,6,7].

Note: 
You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time?

Hint:

How about using a data structure such as deque (double-ended queue)?
The queue size need not be the same as the window’s size.
Remove redundant elements and the queue should store only elements that need to be considered.

*/

public class Solution {
    /* 24ms 88.33%
        Maintain a indexes of descending values in current window 
    */
    public int[] maxSlidingWindow(int[] a, int k) {
        if(k == 0) return new int[0];
        Deque<Integer> q = new ArrayDeque<>(); // keep index of max value in current window in the front
        int[] res = new int[a.length - k + 1];
        for(int i = 0; i < a.length; i++) {
            if(!q.isEmpty() && q.peekFirst() <= i - k) q.pollFirst();  // poll out index out of window
            if(!q.isEmpty() && a[q.peekFirst()] < a[i]) q = new ArrayDeque<>();  // poll out all elements when front element is smaller than current element (Since front element is the largest one) 
            while(!q.isEmpty() && a[q.peekLast()] < a[i]) q.pollLast(); // poll out all smaller elements that in the back of queue
            q.offer(i);  // add this element
            if(i >= k - 1) res[i - k + 1] = a[q.peekFirst()];
        }
        return res;
    }
}