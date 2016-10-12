/*

	class ChainSnap { 
		List recipients; 
		boolean hasCycle(); 
	}

	Implement hasCycle() : whether has cycle
	
	Follow up: Can you implement it in constant extra space, if you are able to change the class?
*/



static class ChainSnap {
	List<ChainSnap> recipients;
	ChainSnap(List<ChainSnap> recipients) {
		this.recipients = recipients;
	}
	/*
		DFS version of topological sort O(n)
		Use visited to avoid repeated visits
		Use path to find if there is any back edge in current path
	*/
	boolean hasCycle() {
		return hasCycle(this, new HashSet<>(), new HashSet<>());
	}
	static boolean hasCycle(ChainSnap node, Set<ChainSnap> visited, Set<ChainSnap> path) {
		visited.add(node);
		path.add(node);
		for(ChainSnap recipient : node.recipients) {
			if(path.contains(recipient) || (!visited.contains(recipient) && hasCycle(recipient, visited, path))) return true;
		}
		path.remove(node);
		return false;
	}
	
	/*
		BFS of topological sort can be used as well. See 207. Course Schedule.
	*/
}

/*
	Follow up: If you can change the class, what's the better way?
	
	Just store three states of each node in class member.
	
*/
static class ChainSnap {
	List<ChainSnap> recipients;
	enum State {
		VISITED_PERMANENT, UNVISITED, VISITED_TEMP;
	}
	State state;
	ChainSnap(List<ChainSnap> recipients) {
		this.recipients = recipients;
		this.state = State.UNVISITED;
	}
	boolean hasCycle() {
		if(this.state == State.VISITED_PERMANENT) return false;
		this.state = State.VISITED_TEMP;
		for(ChainSnap recipient : this.recipients) {
			if(recipient.state == State.VISITED_TEMP || recipient.hasCycle()) return true;
		}
		this.state = State.VISITED_PERMANENT;
		return false;
	}
}