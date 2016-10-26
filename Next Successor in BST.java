/*
	Search next inorder successor in BST

	
	1) If right subtree of node is not NULL, then succ lies in right subtree. Do following.
	Go to right subtree and return the node with minimum key value in right subtree.
	2) If right sbtree of node is NULL, then start from root and us search like technique. Do following.
	Travel down the tree, if a node’s data is greater than root’s data then go right side, otherwise go to left side.
*/

Node inOrderSuccessor(Node root, Node n)
{
	Node succ = null;
    // step 1 of the above algorithm
    if( n.right != null ) {
		succ = n;
        while(succ.left != null) succ = succ.left;
		return succ;
	}
 
    // Start from root and search for successor down the tree
    while (root != null) {
        if (n.val < root.val) {
            succ = root;
            root = root.left;
        }
        else if (n.val > root.val)
            root = root.right;
        else
           break;
    }
 
    return succ;
}