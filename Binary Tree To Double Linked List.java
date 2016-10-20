/*
	binary tree to double linked list
	
*/

/*
	Use two global variables: pre and root.
	Then use inorder traverse.
*/
class Node {
	Node left = null, right = null;
	int val;
	Node(int val) {
		this.val = val;
	}
}
public static Node flattenBinaryTree(Node root) {
	Node[] start = new Node[1];
	flattenBinaryTree(root, new Node[1], start);
	for(Node cur = start[0]; cur != null; cur = cur.right) {
		System.out.println("cur: " + cur.val + ", pre: " + (cur.left == null ? "null" : cur.left.val));
	}
	return start[0];
}
private static void flattenBinaryTree(Node cur, Node[] pre, Node[] start) {
	if(cur == null) return;
	flattenBinaryTree(cur.left, pre, start);
	if(pre[0] == null) start[0] = cur;
	else {
		pre[0].right = cur;
		cur.left = pre[0];
	}
	pre[0] = cur;
	flattenBinaryTree(cur.right, pre, start);
}
public static void main(String[] args) {
	Node root = new Node(1);
	root.left = new Node(2);
	root.right = new Node(3);
	root.left.right = new Node(4);
	root.left.right.left = new Node(5);
	
	flattenBinaryTree(root);
}