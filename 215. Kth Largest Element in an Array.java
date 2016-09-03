/*

Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.

*/

public class Solution {
    /*
        There are a lot of methods to solve this problem:
        1. Fist sort and select the kth element. O(nlgn) time O(1) space (O(n) space if cannot modify original array)
        2. Randomized Quick select. Worst time complexity O(n^2) but usually it takes O(n) time when randomized. O(1) space.
        3. minheap. Use a min-heap of size k to maintain k largest elements, each time compare with peek of heap, if larger than top,
        then poll it out and offer new number. At the end, the peek of heap is the kth largest element. O(nlgk) time O(k) space
        But this method is not efficient when k is very large, when k > 2/n, it is equivilent to find (n - k)th smallest element, 
        we can use a max-heap of size (n-k) and do the similar things.
        4. minheap2. Build a minheap of size n, and poll k times. Time O(n) to build heap, O(klgn) to extract element. O(n) space.
    */
    /*
		Method 2. Randomized Quick select
	
	*/
	public int findKthLargest(int[] nums, int k) {
        int left = 0, right = nums.length - 1, target = nums.length - k;
        Random random = new Random();
        while(left <= right) {
            int index = partition(nums, left, right, random.nextInt(right - left + 1) + left);
            if(index == target) return nums[index]; // found
            if(index > target) right = index - 1;
            else left = index + 1;
        }
        return -1; // k is not valid
    }
    private int partition(int[] nums, int start, int end, int pivot) {
        swap(nums, pivot, end);
        pivot = end--;
        while(start <= end) {
            if(nums[start] < nums[pivot]) start++;
            else swap(nums, start, end--);
        }
        swap(nums, start, pivot);
        return start;
    }
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    /*
        Method 3: minheap
    */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minheap = new PriorityQueue<>();
        for(int num : nums) {
            if(minheap.size() < k) minheap.offer(num);
            else if(minheap.peek() < num) {
                minheap.poll();
                minheap.offer(num);
            }
        }
        return minheap.poll();
    }
}