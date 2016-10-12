/*
Basically it is using idea of sliding window. In order to obtain earliest time to arrive at the other bank of the river, we can maintain a sliding window of size D. In this window, we can maintains indexes of increasing values in current window. Then the result we want is the maximum of minimum of every windows.


For example, for test case:
[1, -1, 0, 2, 3, 5], 3

we first have a indexes window [0, 1, 2] (actual point to values -> [1, -1, 0])
But since the first stone will never be used, and the second stone doesn't exist.
So actually, we only maintain index window [2] (-> value [0]).

Then we can move the window by 1, and look at index 3 (-> value 2)
Although value 2 is larger than value 0 which is the only value in current window, we still need to add it into our window. Because we cannot guarantee that index 3 (value 2) will be never used when index 2 (value 0) is polled out latter.

So current window will become [2, 3] (-> value [0, 2])

Following same idea, window will become latter:
[2, 3, 4] (-> value [0, 2, 3])
[3, 4, 5] (-> value [2, 3, 5])  (index 2 was polled out, because it is out of window range)

So final result is max(min of each windows) = max(0, 0, 0, 2) = 2

Complexity analysis:
Each stone can only be added or polled once at most in the queue (which maintains indexes of increasing values). To obtain minimum value of each window, we just obtain it from first index of window. So total time complexity is O(N).
For space complexity, it is O(min(N, D)). Since it will return 0, when D > N.

*/


class Solution {
    public int solution(int[] A, int D) {
        if(D > A.length) return 0;
        Deque<Integer> q = new ArrayDeque<>();
        int res = -1;
        for(int i = 0; i < A.length; i++) {
            if(!q.isEmpty() && q.peekFirst() <= i - D) q.pollFirst();   // poll out index out of window
            if(!q.isEmpty() && A[i] != -1 && A[q.peekFirst()] > A[i]) q.clear();
            while(!q.isEmpty() && A[i] != -1 && A[q.peekLast()] > A[i]) q.pollLast();  // two lines above poll out values that are larger than new value
            if(A[i] != -1) q.offer(i);  					// offer new value
            if(i >= D - 1 && q.isEmpty()) return -1;   		// empty means no way to pass
            if(i >= D - 1 && res < A[q.peek()]) res = A[q.peek()];   // get max(min of window)
        }
        return res;
    }
}