/*
	Design a class UndirectedUnweightedGraph,
	and implement:
		addEdge(), hasEdge(), numberOfEdges()

	Follow up: Write serialize() and deserialize()
*/

/*
	Can use HashMap<Integer, HashSet<Integer>> to store edges.
	Since we don't need to traverse the graph, we just use smaller index as key, larger index as value,
	so that we can reduce space usage by 50%.
	
	Each time check edge, just check the smaller index as key.
	
	For serialize, for one node's edges, we just separate by space; for different nodes, separate by comma.
	e.g. 
	
	  a -- b  -- c
	  |    |
	  d -- e
	  
    "a b d,b c e,d e" 
*/