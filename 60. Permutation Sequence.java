/*MORE
The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.
*/


public class Solution {
	/* 3ms
		Time O(n^2) (Although many people claimed this is O(n))
		Space O(n)
		
		Just an observations:
		
		1 + (2 3) / (3 2)
		2 + (1 3) / (3 1)
		3 + (1 2) / (2 1)
		
		So here we have n! = 3! = 6 permutations, those starting from 1 are indexed in the range [0, 1] which can be obtained by n!/n = 6/3 = 2, so first two permutations are starting from 1.
		
		Similar idea, we can go to second number by k % (n - index)!  (index starts from 0)
	*/
    public String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>(n);
        StringBuilder sb = new StringBuilder(n);
        int perm = 1;
        for(int i = 1; i <= n; i++) {
            nums.add(i);
            perm *= i;   // permutation n!
        }
        k--;  // decrement first
        for(int i = n; i > 0; i--) {  // add each number
            perm /= i;
            int choice = k / perm;
            k %= perm;
            sb.append(nums.remove(choice));  // remove chosen one
        }
        return sb.toString();
    }
}