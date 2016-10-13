/*

	Find longest contiguous sequence in a undirected graph.
	
	Use Memorization to reduce duplicate calculations.
	
	Can handle disconnected components.
	
	Cannot handle cycles in graph.
*/

public static List<Integer> longestContiguousSequence(List<List<Integer>> pairs) {
	Map<Integer, Set<Integer>> adjacencyLists = new HashMap<>();
	for(List<Integer> pair : pairs) {
		Set<Integer> set1 = adjacencyLists.getOrDefault(pair.get(0), new HashSet<>());
		Set<Integer> set2 = adjacencyLists.getOrDefault(pair.get(1), new HashSet<>());
		set1.add(pair.get(1));
		set2.add(pair.get(0));
		adjacencyLists.put(pair.get(0), set1);
		adjacencyLists.put(pair.get(1), set2);
	}
	Map<List<Integer>, List<Integer>> maxPaths = new HashMap<>();
	List<Integer> longestPath = null;
	for(Integer startNode : adjacencyLists.keySet()) {
		List<Integer> curPath = dfs(startNode, adjacencyLists, new HashSet<>(), maxPaths);
		if(longestPath == null || curPath.size() > longestPath.size()) longestPath = curPath;
	}
	return longestPath;
}
private static List<Integer> dfs(Integer curNode, Map<Integer, Set<Integer>> adjacencyLists, Set<Integer> path, Map<List<Integer>, List<Integer>> maxPaths) {
	path.add(curNode);
	List<Integer> finalPath = new ArrayList<>();
	boolean isLeaf = true;
	for(int next : adjacencyLists.get(curNode)) {
		if(!path.contains(next)) {
			isLeaf = false;
			List<Integer> direction = Arrays.asList(curNode, next);
			if(!maxPaths.containsKey(direction)) {
				List<Integer> partialPath = new ArrayList<>(dfs(next, adjacencyLists, path, maxPaths));
				partialPath.add(curNode);
				maxPaths.put(direction, partialPath);
			}
			if(maxPaths.get(direction).size() > finalPath.size())
				finalPath = maxPaths.get(direction);
		}
	}
	if(isLeaf) finalPath.add(curNode);
	path.remove(curNode);
	return finalPath;
}