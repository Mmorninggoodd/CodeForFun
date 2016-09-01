/*

Implement a trie with insert, search, and startsWith methods.

Note:
You may assume that all inputs are consist of lowercase letters a-z.

*/

/*  14ms 97%
	Assumption: Only lowercase characters -> use an TrieNode[] array

*/
class TrieNode {
    public boolean isWord;  // Whether this node is word
    public TrieNode[] children = new TrieNode[26];
    public TrieNode() {}
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode cur = root;
        for(char c : word.toCharArray()) {
            if(cur.children[c - 'a'] == null) cur.children[c - 'a'] = new TrieNode();
            cur = cur.children[c - 'a'];
        }
        cur.isWord = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode node = find(word);
        return node != null && node.isWord;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        return find(prefix) != null;
    }
    
    private TrieNode find(String word) {
        TrieNode cur = root;
        for(char c : word.toCharArray()) {
            if(cur.children[c - 'a'] == null) return null;
            cur = cur.children[c - 'a'];
        }
        return cur;
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");