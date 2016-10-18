/*
	Return software installation sequence, given some dependency.
	
	Just a topological problem. Similar wtih #207 Course Schedule
	
	Can use dfs/bfs version.
*/
public static List<String> installationSequence(String[][] dependencies) {
	HashMap<String, List<String>> children = new HashMap<>();
	for(String[] dependency : dependencies) {
		if(!children.containsKey(dependency[1])) {
			children.put(dependency[1], new ArrayList<>());
		}
		children.get(dependency[1]).add(dependency[0]);
	}
	LinkedList<String> result = new LinkedList<>();
	HashSet<String> visited = new HashSet<>(), path = new HashSet<>();
	for(String start : children.keySet()) {
		if(dfs(children, start, visited, path, result)) {
			return new LinkedList<>();   // contains cycle
		}
	}
	return result;
}
private static boolean dfs(HashMap<String, List<String>> children, String cur, HashSet<String> visited, HashSet<String> path, LinkedList<String> result) {
	if(path.contains(cur)) return true; // has cycle
	if(!visited.add(cur)) return false;  // visited before
	path.add(cur);
	if(children.containsKey(cur)) {
		for(String child : children.get(cur)) {
			if(dfs(children, child, visited, path, result)) return true; // has cycle
		}
	}
	path.remove(cur);
	result.addFirst(cur);  // append to head each time
	return false;
}