/*

Given a tokenizer object

getNextToken()
token contains name and tag, where tag is one of {open,close,text}

Build a tree from all tokens.

(“html”,“open”) (“user”,“open”) (“id”,“open”) (“aa”,“text”) (“id”,“close”) (“meta”,“open”) (“bb”,“text”) (“meta”,“close”) (“user”,“close”) (“html”,“close”)

->

<html>
	<user>
		<id>aa</id>
		<meta>bb</meta>
	</user>
</html>

Follow up: how to verify the input token is correct?
		
*/

/*
	Use Stack to store each level of node
*/
class TreeNode {
	String name, text;
	List<TreeNode> children;
	TreeNode (String name) {
		this.name = name;
		this.text = null;
		this.children = new ArrayList<>();
	}
}
public static TreeNode xmlParser(Tokenizer tokenizer) {
	Deque<TreeNode> level = new ArrayDeque<>();
	TreeNode root = null;
	while(tokenizer.hasNextToken()) {
		Token token = tokenizer.getNextToken();
		if(token.tag.equals("OPEN")) {
			TreeNode newNode = new TreeNode(token.val);
			if(root == null) root = newNode;
			if(!level.isEmpty()) level.peek().children.add(newNode);
			level.push(newNode);
		}
		else if(token.tag.equals("CLOSE")) {
			if(!level.peek().name.equals(token.val)) throw new IllegalArgumentException("Invalid tokens.");
			level.pop();
		}
		else level.peek().text = token.val;
	}
	return root;
}