/*
	Given a DAG, return the kth inorder node.
	
	Note that in this graph, each node only has at most 2 outgoing edges but might have a lot of incoming edges.

*/

/*
	Strictly speaking, the worst complexity is O(k), but if k is very large,
	then the time complexity would be exponential w.r.t. number of nodes.
	Since we probably will revisit a node a lot of time.
	
	So we need to store total count if we going down through this node for each node,
	then if we meet this node again, we can just return the count.
	
	Each node at most will be visited twice, the second time must be the final path.
	
	Time & Space Complexity should O(min(n,k))
*/
class Node {
	int val;
	List<Node> children;
	Node(int val) {
		this.val = val;
		children = new ArrayList<>();
	}
}
public static Node DAGInOrder(Node root, int k) {
	Node[] res = new Node[1];
	inorder(root, k, new HashMap<>(), res);
	if(res[0] != null) System.out.println(res[0].val);
	else System.out.println("Null");
	return res[0];
}
private static int inorder(Node root, int k, HashMap<Node, Integer> counts, Node[] res) {
	//if(root == null) return 0;  // reach the end
	if(k == 0) {      			// found and return -1
		res[0] = root;
		return -1;
	}
	Integer count  = counts.get(root);
	if(count != null && count < k) return count;  // visited before (but if target is in current path, then just keep going)
	count = 1;
	for(Node next : root.children) {
		int childCount = inorder(next, k - count, counts, res);
		if(childCount == -1) return -1; // found in child branch
		count += childCount;
	}
	counts.put(root, count);
	return count;
}

public static void main(String[] args) {
	Node node0 = new Node(0);
	Node node1 = new Node(1);
	Node node2 = new Node(2);
	Node node3 = new Node(3);
	Node node4 = new Node(4);
	Node node5 = new Node(5);
	node0.children.add(node1); node0.children.add(node5);
	node1.children.add(node2); node1.children.add(node3);
	node2.children.add(node3);
	node3.children.add(node4);
	node5.children.add(node2); node5.children.add(node3);
	DAGInOrder(node0, 13); // expected Null
	DAGInOrder(node0, 12); // expected 4
	DAGInOrder(node0, 11); // expected 3
	DAGInOrder(node0, 10); // expected 4
	DAGInOrder(node0, 9); // expected 3
	DAGInOrder(node0, 8); // expected 2
	DAGInOrder(node0, 7); // expected 5
	DAGInOrder(node0, 6); // expected 4
	DAGInOrder(node0, 5); // expected 3
	DAGInOrder(node0, 4); // expected 4
	DAGInOrder(node0, 3); // expected 3
}