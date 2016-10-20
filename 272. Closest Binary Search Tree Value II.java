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
	A trivial solution using O(n) time O(k) space

	Traverse BST in in-order, while maintaining a window of size k.
*/
public static List<Integer> closestKValues(TreeNode root, double target, int k) {
	LinkedList<Integer> window = new LinkedList<>(k);
	closestKValues(root, target, k, window);
	return window;
}
private static boolean closestKValues(TreeNode root, double target, int k, LinkedList<Integer> window) {
	if(root == null) return false;
	if(closestKValues(root.left, target, k, window)) return true;
	if(window.size() == k) {
		if(Math.abs(window.peekFirst() - target) > Math.abs(root.val - target)) window.pollFirst();
		else return true;  // found
	}
	window.add(root.val);
	return closestKValues(root.right, target, k, window);
}

/*
	O(max(lgn, k)) solution
	First initialize two stacks that store from root to the leaf that is closest to target. O(lgn)
	Then utilize these two stacks to getPredecessor and getSuccessor. O(max(k, lgn))
*/

public static List<Integer> closestKValues(TreeNode root, double target, int k) {
	ArrayDeque<TreeNode> pred = new ArrayDeque<>(), succ = new ArrayDeque<>();
	initStack(pred, succ, target, root);
	List<Integer> res = new ArrayList<>();
	while(k-- > 0) {
		if(succ.isEmpty() || (!pred.isEmpty() && Math.abs(pred.peek().val - target) < Math.abs(succ.peek().val - target))) {
			res.add(pred.peek().val);
			getPredecessor(pred);
		}
		else {
			res.add(succ.peek().val);
			getSuccessor(succ);
		}
	}
	return res;
}
private static void initStack(ArrayDeque<TreeNode> pred, ArrayDeque<TreeNode> succ, int target, TreeNode root) {
	while(root != null) {
		if(root.val <= target) {
			pred.push(root);
			root = root.right;
		}
		else {
			succ.push(root);
			root = root.left;
		}
	}
}
private static void getPredecessor(ArrayDeque<TreeNode> pred) {
	TreeNode node = pred.pop();
	if(node.left != null) {
		pred.push(node.left);
		while(pred.peek().right != null) pred.push(pred.peek().right);
	}
}
private static void getSuccessor(ArrayDeque<TreeNode> succ) {
	TreeNode node = pred.pop();
	if(node.right != null) {
		pred.push(node.right);
		while(pred.peek().left != null) pred.push(pred.peek().left);
	}
}


