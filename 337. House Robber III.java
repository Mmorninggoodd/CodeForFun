/* 337. House Robber III

The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:
     3
    / \
   2   3
    \   \ 
     3   1
Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:
     3
    / \
   4   5
  / \   \ 
 1   3   1
Maximum amount of money the thief can rob = 4 + 5 = 9.

*/


/*
	Improved from memorization version.
	
	Decouple subproblems. Return values of two states instead of one.
	
	Space O(lgn) Time O(n)
*/
public class Solution {
    public int rob(TreeNode root) {
        int[] res = robSub(root);
        return Math.max(res[0], res[1]);
    }
    private int[] robSub(TreeNode root) {
        if(root == null) return new int[2];
        int[] left = robSub(root.left), right = robSub(root.right);
        return new int[]{root.val + left[1] + right[1], Math.max(left[0], left[1]) + Math.max(right[0], right[1])};
    }
}


/*
	Memorization version.

	Each time choose max(chosen current node + sum(memo[grandchildren]), not chosen current node + sum(children))
	
	O(n) time & space
*/
public class Solution {
    public int rob(TreeNode root) {
        Map<TreeNode, Integer> memo = new HashMap<>();
        return rob(root, memo);
    }
    private int rob(TreeNode root, Map<TreeNode, Integer> memo) {
        if(root == null) return 0;
        if(memo.containsKey(root)) return memo.get(root);
        int max = 0;
        if(root.left == null && root.right == null) max = root.val;
        else {
            max = Math.max(root.val + (root.left == null ? 0 : rob(root.left.left, memo) + rob(root.left.right, memo)) +
                (root.right == null ? 0 : rob(root.right.left, memo) + rob(root.right.right, memo)), 
                rob(root.left, memo) + rob(root.right, memo));
        }
        memo.put(root, max);
        return max;
    }
}