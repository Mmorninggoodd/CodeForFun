/*

Given a list of user pairs, return whether or not it's possible all the pairs are of opposite gender.
E.g. oppositeGender([(A, B), (A, C), (E, F), (A, C)]) should return false because if (A, B) is of opposite gender, (B, C) is of opposite gender, then (A, C) can NOT of opposite gender.

*/

/*
	First build a undirected graph, then run BFS.
	
	Be careful about there exists several connected components.
*/
public static boolean isBipartite(List<List<String>> pairs) {
	Map<String, Set<String>> adjacencyLists = new HashMap<>();
	for(List<String> pair : pairs) {
		if(!adjacencyLists.containsKey(pair.get(0))) adjacencyLists.put(pair.get(0), new HashSet<>());
		if(!adjacencyLists.containsKey(pair.get(1))) adjacencyLists.put(pair.get(1), new HashSet<>());
		adjacencyLists.get(pair.get(0)).add(pair.get(1));
		adjacencyLists.get(pair.get(1)).add(pair.get(0));
	}
	Map<String, Integer> genders = new HashMap<>();
	for(List<String> pair : pairs) {    // only need to check any one of this pair
		String src = pair.get(0);
		if(!genders.containsKey(src)) { // not explored this connected component before
			genders.put(src, 0);
			Deque<String> queue = new ArrayDeque<>();
			queue.offer(src);
			while(!queue.isEmpty()) {
				String cur = queue.poll();
				int gender = genders.get(cur);
				for(String next : adjacencyLists.get(cur)) {
					if(genders.containsKey(next)) {                     // explored before
						if(genders.get(next) == gender) return false;   // conflict
					}
					else {
						genders.put(next, gender ^ 1);
						queue.offer(next);
					}
				}
			}
		}
	}
	return true;
}