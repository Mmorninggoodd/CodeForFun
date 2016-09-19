/*

Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

Note:
The array size can be very large. Solution that uses too much extra space will not pass the judge.

Example:

int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);

// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(3);

// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(1);

*/

/*
	O(n) construction O(1) pick
	Use a HashMap to store (value, list of indexes) pairs.
*/
public class Solution {
    Map<Integer, ArrayList<Integer>> indexes;
    Random random;
    public Solution(int[] nums) {
        indexes = new HashMap<>();
        random = new Random();
        for(int i = 0; i < nums.length; i++) {
            if(!indexes.containsKey(nums[i])) indexes.put(nums[i], new ArrayList<>());
            indexes.get(nums[i]).add(i);
        }
    }
    
    public int pick(int target) {
        ArrayList<Integer> list = indexes.get(target);
        return list.get(random.nextInt(list.size()));
    }
}

/*
	O(1) Construction O(n) pick
	Use Reservoir Sampling
*/

public class Solution {
    int[] nums;
    Random random;
    public Solution(int[] nums) {
        this.nums = nums;
        random = new Random();
    }
    
    public int pick(int target) {
        int index = 0, count = 1;
        for(int i = 0; i < this.nums.length; i++) {
            if(this.nums[i] == target && this.random.nextInt(count++) == 0) index = i;
        }
        return index;
    }
}