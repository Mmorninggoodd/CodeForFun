/*
Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:

Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 

Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

Hint:

1. Consider implement these two helper functions:
　　i. getPredecessor(N), which returns the next smaller node to N.
　　ii. getSuccessor(N), which returns the next larger node to N.
2. Try to assume that each node has a parent pointer, it makes the problem much easier.
3. Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
4. You would need two stacks to track the path in finding predecessor and successor node separately.

*/


/*
	A trivial solution using O(n)

	Traverse BST in in-order, while maintaining a window of size k.
*/
public static List<Integer> closestKValues(TreeNode root, double target, int k) {
	LinkedList<Integer> window = new LinkedList<>(k);
	closestKValues(root, target, k, window);
	return window;
}
private static void closestKValues(TreeNode root, double target, int k, LinkedList<Integer> window) {
	closestKValues(root.left, target, k, window);
	if(window.size() == k) {
		if(Math.abs(window.peekFirst() - target) > Math.abs(root.val - target)) window.pollFirst();
		else return;  // found
	}
	window.add(root.val);
	closestKValues(root.right, target, k, window);
}

/*
	O(lgn + k) solution
*/