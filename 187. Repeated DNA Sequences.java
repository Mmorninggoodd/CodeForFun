/*

All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].

*/

public class Solution {
	/* 61ms 11%
		A simple solution: use hashmap to store all existing sequences
	*/
    public List<String> findRepeatedDnaSequences(String s) {
        Map<String, Integer> map = new HashMap<>();
        ArrayList<String> res = new ArrayList<>();
        for(int i = 0; i < s.length() - 9; i++) {
            String seq = s.substring(i, i + 10);
            map.put(seq, map.getOrDefault(seq, 0) + 1);
            if(map.get(seq) == 2) res.add(seq);
        }
        return res;
    }
	
	/* 33ms 88%
		Use two hashsets
	
	*/
	public List<String> findRepeatedDnaSequences(String s) {
        Set<String> first = new HashSet<>(), second = new HashSet<>();
        for(int i = 0; i < s.length() - 9; i++) {
            String seq = s.substring(i, i + 10);
            if(!first.add(seq)) second.add(seq);
        }
        return new ArrayList<String>(second);
    }
	
	/* 33ms 88%
		Space saving version, though running time is the same
		Since there are only four different characters, we just encoding it into 00, 01, 10, 11.
		For 10-letter-long sequence, it takes 2*10 = 20 bits. (Though int is 32-bit)
	*/
	private static int[] mapping = new int[26];
    static {
        mapping['C' - 'A'] = 1;
        mapping['G' - 'A'] = 2;
        mapping['T' - 'A'] = 3;
    }
    public List<String> findRepeatedDnaSequences(String s) {
        Set<Integer> first = new HashSet<>();
        Set<String> second = new HashSet<>();
        for(int i = 0; i < s.length() - 9; i++) {
            int hash = DNAHash(s, i, i + 9);
            if(!first.add(hash)) second.add(s.substring(i, i + 10));
        }
        return new ArrayList<String>(second);
    }
    private int DNAHash(String s, int start, int end) {
        int hash = 0;
        while(start <= end) {
            hash = (hash << 2) + mapping[s.charAt(start) - 'A'];
            start++;
        }
        return hash;
    }
}