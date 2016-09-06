/*
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. The algorithm should run in linear time and in O(1) space.
*/

public class Solution {
	/*
		Boyer-Moore Majority Vote algorithm
		Generalized to k cases
		Space O(k) Time O(n)
	*/
    public static List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int k = 3;
        int[] count = new int[k];
        int[] major = new int[k];
        for(int num : nums) {
            boolean exists = false;
            for(int i = 0; i < k; i++) {
                if(major[i] == num) {
                    count[i]++;
                    exists = true;  // already found
                    break;
                }
				if(count[i] == 0) {
                    count[i] = 1;
                    major[i] = num;
                    exists = true;   // add to found majors
                    break;
                }
            }
            if(exists) continue;
            for(int i = 0; i < k; i++) count[i]--;  // reduce counts for all majors
        }
        Arrays.fill(count, 0);   // recount again
        for(int num : nums) {
            for(int i = 0; i < k; i++) {
                if(major[i] == num) {
                    count[i]++;
                    break;
                }
            }
        }
        for(int i = 0; i < k; i++) {
            if(count[i] > nums.length / k) res.add(major[i]);
        }
        return res;
    }
}