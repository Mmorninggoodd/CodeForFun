/*

Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.
Note: 
You may assume k is always valid, 1 ≤ k ≤ n2.

*/


/* 2 ms

	Similar with quick select but utilizing sorted rows and columns. 
	Each time try one number that can partition matrix into two parts
	where the first part only has k elements.
	
	Each iteration takes O(n) time, it can at most takes O(lgn) iterations.
	
	Time O(nlgn) Space O(1)
	Can be combined with median of medians to get guaranteed O(n) solution
*/
public int kthSmallest(int[][] matrix, int k) {
	int n = matrix.length;
	int lo = matrix[0][0], hi = matrix[n-1][n-1];
	while (lo < hi) {  // each iteration only takes O(n) since j can only decrease
		int mid = (lo + hi) / 2, count = 0, j = n, curMid = Integer.MIN_VALUE;  // curMid: largest element that <= mid in matrix
		for (int[] row : matrix) {
			while (j >= 1 && row[j-1] > mid) j--;  // j: number of elements <= mid in current row
			if(j == 0) break;  // no need to proceed to remaining rows
			if(row[j - 1] > curMid) curMid = row[j - 1];
			count += j;
		}
		if (count == k) return curMid; // count: number of elements <= mid in whole matrix
		else if (count < k) lo = mid + 1;
		else hi = mid;   // cannot use hi = mid -1. Otherwise fails in cases of duplicates
	}
	return lo;
}

/* 29 ms
	Use a maxheap. Always keep k smallest elements.
	(But it doesn't utilize property of sorting in each row or column)
	
	Time O(klgk) Space O(k)

*/
public int kthSmallest(int[][] matrix, int k) {
	PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());
	for(int i = 0; i < matrix.length; i++) {
		for(int j = 0; j < matrix[0].length; j++) {
			if(maxHeap.size() < k) maxHeap.offer(matrix[i][j]);
			else if (maxHeap.peek() > matrix[i][j]) {
				maxHeap.poll();
				maxHeap.offer(matrix[i][j]);
			}
		}
	}
	return maxHeap.poll();
}

/* 	25 ms
	Use a minheap.
	First store first whole column (or k elements which is smaller), and poll k - 1 elements. Each time offer its next (column) element into min heap.
	(But it doesn't utilize property of sorting in each row)
	
	Time O(klgk) Space O(k)
*/

class Tuple implements Comparable<Tuple>{
    int value, x, y;
    public Tuple(int value, int x, int y) {
        this.value = value;
        this.x = x;
        this.y = y;
    }
    public int compareTo(Tuple o) {
        return Integer.compare(this.value, o.value);
    }
}
public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Tuple> minHeap = new PriorityQueue<>();
		int bound = Math.min(matrix.length, k);
        for(int i = 0; i < bound; i++) minHeap.offer(new Tuple(matrix[i][0], i, 0));
        for(int i = 0; i < k - 1; i++) {
            Tuple cur = minHeap.poll();
            if(cur.y + 1 < matrix[0].length) minHeap.offer(new Tuple(matrix[cur.x][cur.y + 1], cur.x, cur.y + 1));
        }
        return minHeap.poll().value;
    }
}