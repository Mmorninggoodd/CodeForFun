/*
	Given string array2 (dict) which might has millions of words.
	Then given another string array1, search all anagram combinations that
	use all chars in array1.
	
	Words in dict can be used multiple times.

*/


import java.util.*;
public class DictAnagram {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null;
        TrieNode(){}
    }
    TrieNode root;
    private void insert(String word) {
        TrieNode node = root;
        for(char c : word.toCharArray()) {
            if(node.children[c - 'A'] == null) {
                node.children[c - 'A'] = new TrieNode();
            }
            node = node.children[c - 'A'];
        }
        node.word = word;
    }
    public DictAnagram(String[] dict) {
        root = new TrieNode();
        for(String word : dict) insert(word);
    }
    public Set<List<String>> getAnagrams(String[] strs) {
        Set<List<String>> res = new HashSet<>();
        int[] counts = new int[26];
        for(String word : strs) {
            for(char c : word.toCharArray()) {
                counts[c - 'A']++;
            }
        }
        dfs(counts, new ArrayList<>(), res, root);
        return res;
    }
    private void dfs(int[] counts, List<String> path, Set<List<String>> res, TrieNode node) {
        boolean isDone = true;
        for(int i = 0; i < counts.length; i++) {
            if(counts[i] > 0) isDone = false;
            if(counts[i] > 0 && node.children[i] != null) {
                counts[i]--;
                dfs(counts, path, res, node.children[i]);
                counts[i]++;
            }
        }
        if(node.word != null) {
            path.add(node.word);
            if(isDone) {
                List<String> list = new ArrayList<>(path);
                Collections.sort(list);
                res.add(list);
            }
            else dfs(counts, path, res, root);
            path.remove(node.word);
        }
    }
    public static void main(String[] args) {
        DictAnagram dicts = new DictAnagram(new String[]{"GAT","DOC","CD","GOAT","BAD","COOL"});
        Set<List<String>> res = dicts.getAnagrams(new String[]{"CAT","DOG"});
        System.out.print(res);
        //System.out.print(dicts.getAnagrams(new String[]{"GAT","GAT"}));
    }
}
