/*

Problem Description:

There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of words from the dictionary, wherewords are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

For example,
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Note:

You may assume all letters are in lowercase.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.

*/


/*
	First iterate the words to get edges (c1 -> c2)
	Then use topological sort BFS to get the result.
	
	O(n*len(word)) Time O(number of distinct char) Space
*/
public static String alienOrder(String[] words) {
	Map<Character, Integer> degrees = new HashMap<>();
	Map<Character, Set<Character>> edges = new HashMap<>();
	for(String word : words) {
		for(char c : word.toCharArray()) {
			if(degrees.containsKey(c)) continue;
			degrees.put(c, 0);
			edges.put(c, new HashSet<>());
		}
	}
	for(int i = 0; i < words.length - 1; i++) {
		int len = Math.min(words[i].length(), words[i + 1].length());
		for(int j = 0; j < len; j++) {
			char c1 = words[i].charAt(j), c2 = words[i + 1].charAt(j);
			if(c1 != c2) {
				if(edges.get(c1).add(c2)) {  // add c1 -> c2 edge
					degrees.put(c2, degrees.get(c2) + 1);
				}
			}
		}
	}

	/*
		Topological sort BFS to find order
	*/
	Deque<Character> q = new ArrayDeque<>();
	StringBuilder sb = new StringBuilder();
	for(Map.Entry<Character, Integer> degree : degrees.entrySet()) {
		if(degree.getValue() == 0) {
			q.offer(degree.getKey());
		}
	}
	while(!q.isEmpty()) {
		Character c = q.poll();
		sb.append(c);
		for(Character next : edges.get(c)) {
			int degree = degrees.get(next) - 1;
			if(degree == 0) q.offer(next);
			else degrees.put(next, degree);
		}
	}
	if(edges.size() != sb.length()) return "";  // exists conflict (cycle)
	return sb.toString();
}