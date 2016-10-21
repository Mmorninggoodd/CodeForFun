/*
A string such as "word" contains the following abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Given a target string and a set of strings in a dictionary, find an abbreviation of this target string with the smallest possible length such that it does not conflict with abbreviations of the strings in the dictionary.

Each number or letter in the abbreviation is considered length = 1. For example, the abbreviation "a32bc" has length = 4.

Note:

In the case of multiple answers as shown in the second example below, you may return any one of them.
Assume length of target string = m, and dictionary size = n. You may assume that m ≤ 21, n ≤ 1000, and log2(n) + m ≤ 20.
 

Examples:

"apple", ["blade"] -> "a4" (because "5" or "4e" conflicts with "blade")

"apple", ["plain", "amber", "blade"] -> "1p3" (other valid answers include "ap3", "a3e", "2p2", "3le", "3l1").

*/

/*
	A efficient way is first to build a Trie tree using dictionary.
	
	Then each time we need to find a minimum unique word abbreviation for a target word,
	we try to generate shortest abbreviations until we find it is valid.

	This is a NP-C problem.
*/
public class MinWordAbbr {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord = false;
    }
    TrieNode root = new TrieNode();


    public MinWordAbbr(String[] dict) {
        for(String word : dict) add(word);
    }
    public String getMinAbbr(String target) {
        for(int len = 1; len <= target.length(); len++) {
            List<String> candidates = new ArrayList<>();
            getAllAbbr(target, candidates, new StringBuilder(), 0, len, 0);
            for(String candidate : candidates) {
                if(!match(this.root, candidate, 0, 0)) return candidate;  // If can match, then it is not unique
            }
        }
        return "";  // there is exactly same word in dictionary
    }
    private void add(String word) {
        TrieNode node = this.root;
        for(char c : word.toCharArray()) {
            if(node.children[c - 'a'] == null) node.children[c - 'a'] = new TrieNode();
            node = node.children[c - 'a'];
        }
        node.isWord = true;
    }
    private void getAllAbbr(String word, List<String> res, StringBuilder sb, int count, int len, int index) {
        if(len < 0) return;
        int oldLen = sb.length();
        if(index == word.length()) {
            if(count == 0 && len == 0) res.add(sb.toString());
            else if(count != 0 && len == 1) res.add(sb.append(count).toString());
        }
        else {
            getAllAbbr(word, res, sb, count + 1, len, index + 1); // not add current char
            if(count > 0) sb.append(count);
            getAllAbbr(word, res, sb.append(word.charAt(index)), 0, len - (count == 0 ? 1 : 2), index + 1);  // add
        }
        sb.setLength(oldLen);
    }
    private boolean match(TrieNode node, String abbr, int index, int count) {
        if(node == null) return false;
        if(count == 0 && index == abbr.length()) {
            return node.isWord;
        }
        while(index < abbr.length() && Character.isDigit(abbr.charAt(index))) {
            count = 10 * count + abbr.charAt(index++) - '0';
        }
        if(count > 0) {
            for(TrieNode child : node.children) {
                if(match(child, abbr, index, count - 1)) return true;
            }
            return false;
        }
        return match(node.children[abbr.charAt(index) - 'a'], abbr, index + 1, count);
    }

    public static void main(String[] args) {
        MinWordAbbr obj = new MinWordAbbr(new String[]{"plain", "amber", "blade"});
        String res = obj.getMinAbbr("apple");
        System.out.print(res);
    }
}
