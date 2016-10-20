/*
	Auto complete
	
	Given a dictionary and a word, return how many words can be used for auto-complete.
*/

/*
	Just use a Trie, and search for prefix.
*/

class TrieNode {
	TrieNode[] children = new TrieNode[26];
	String word = null;
	TrieNode(){}
}
TrieNode root = new TrieNode();
public void buildTree(String[] dict) {
	for(String word : dict) {
		TrieNode node = this.root;
		for(char c : word.toCharArray()) {
			if(node.children[c - 'a'] == null) node.children[c - 'a'] = new TrieNode();
			node = node.children[c - 'a'];
		}
		node.word = word;
	}
}
public List<String> getCandidates(String prefix) {
	TrieNode node = this.root;
	List<String> candidates = new ArrayList<>();
	for(char c : prefix.toCharArray()) {
		if(node.children[c - 'a'] == null) return candidates;
		node = node.children[c - 'a'];
	}
	dfs(node, candidates);
	return candidates;
}
private static void dfs(TrieNode node, List<String> candidates) {
	if(node.word != null) candidates.add(node.word);
	for(TrieNode next : node.children) {
		if(next != null) dfs(next, candidates);
	}
}