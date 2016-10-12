/*
	Give some CIDR rules, and given an IP, return true iff it satisfy any one of rules.
	
	rules = {"7.0.0.0/8", "208.130.28.0/22", "7.7.0.0/16"}
	For example, 7.1.1.1 satisfy 7.0.0.0/8, then return true

*/

/*
	If there are a lot of rules, then we can use trie tree.
	
	Follow up: implement remove rule.
	How to resolve thread safe issue.

*/

public class IPFilter {
    private static class TrieNode {
        TrieNode[] children = new TrieNode[2];
        boolean isRule = false;
        TrieNode(){}
    }
    private TrieNode root = new TrieNode();
    public IPFilter(String[] rules) {
        for(String rule : rules) addRule(rule);
    }
    public void addRule(String rule) {
        String[] components = rule.split("/");
        int numBit = Integer.parseInt(components[1]);
        String binaryIP = toBinaryIP(components[0]).substring(0, numBit);
        TrieNode node = root;
        for(char bit : binaryIP.toCharArray()) {
            int nextBit = bit - '0';
            if(node.children[nextBit] == null) node.children[nextBit] = new TrieNode();
            node = node.children[nextBit];
        }
        node.isRule = true;
    }
    public void removeRule(String rule) {
        String[] components = rule.split("/");
        int numBit = Integer.parseInt(components[1]);
        String binaryIP = toBinaryIP(components[0]).substring(0, numBit);
        removeRule(binaryIP, root, 0);
    }
    private boolean removeRule(String binaryIP, TrieNode node, int index) {
        if(index == binaryIP.length()) {  // reach the end
            node.isRule = false;
            return node.children[0] == null && node.children[1] == null;
        }
        int nextBit = binaryIP.charAt(index) - '0';
        if(node.children[nextBit] == null || (index == binaryIP.length()&& !node.isRule)) return false;
        boolean canRemove = removeRule(binaryIP, node.children[nextBit], index + 1);
        if(canRemove) node.children[nextBit] = null;  // remove child node
        canRemove |= node.children[nextBit ^ 1] == null || !node.isRule;  // can remove current node or not
        return canRemove;
    }
    private static String toBinaryIP(String IP) {
        String[] blocks = IP.split("\\.");
        StringBuilder sb = new StringBuilder();
        for(String block : blocks) {
            String binaryBlock = Integer.toBinaryString(Integer.parseInt(block));
            for(int i = 0; i < 8 - binaryBlock.length(); i++) sb.append('0');
            sb.append(binaryBlock);
        }
        return sb.toString();
    }
    public boolean isValid(String ip) {
        String binaryIP = toBinaryIP(ip);
        TrieNode node = root;
        for(char bit : binaryIP.toCharArray()) {
            int nextBit = bit - '0';
            if(node.children[nextBit] == null) return false;
            node = node.children[nextBit];
            if(node.isRule) return true;
        }
        return false;
    }


    public static void main(String[] args) {
        IPFilter filter = new IPFilter(new String[]{"7.0.0.0/9", "208.130.28.0/22", "7.7.0.0/16"});
        filter.removeRule("7.0.0.0/9");
        System.out.println(filter.isValid("7.1.1.1"));
        System.out.println(filter.isValid("8.1.1.1"));
    }
}

