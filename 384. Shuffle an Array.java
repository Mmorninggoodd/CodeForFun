/*

Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();

*/
/*

	Each time choose one element from remaining elements.
	Be careful about the range you choose from. (don't choose from chosen element;
	make sure to include the index you are going to put)
	
*/
public class Solution {
    int[] arr;
    Random random;
    public Solution(int[] nums) {
        this.arr = nums;
        //this.arr = Arrays.copyOf(nums, nums.length);
        this.random = new Random();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return this.arr;
        //return Arrays.copyOf(arr, arr.length);
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] output = Arrays.copyOf(arr, arr.length);
        for(int i = 0; i < arr.length - 1; i++) {
            int j = i + random.nextInt(arr.length - i);
            int tmp = output[i];
            output[i] = output[j];
            output[j] = tmp;
        }
        return output;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */