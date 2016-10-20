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

/*
	Follow up: How to solve it, if graph is very large.
	
	Use distributed algorithm.
	
	http://stackoverflow.com/questions/18314250/optimized-algorithm-to-schedule-tasks-with-dependency
	http://cs.stackexchange.com/questions/2524/getting-parallel-items-in-dependency-resolution/2525#2525
	
	instead of appending, merge all items of the same "depth" to one set. You get a list of sets, each of which contains items you can execute/install in parallel. 
	
	for i=0 to k
	  parallel foreach T in S_k
		execute T
		
	parallel foreach T in S_0
		recursive_execute T
		
	where,
	recursive_execute T {
	  atomic { if T.count++ < T.indeg then return }
	  execute T
	  parallel foreach T' in T.succ
		recursive_execute T'
}
*/