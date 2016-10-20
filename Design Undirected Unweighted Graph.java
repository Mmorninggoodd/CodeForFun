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

class UndirectedUnweightedGraph {
	int totalEdges = 0;
	HashMap<Integer, HashSet<Integer>> edges = new HashMap<>();
	UndirectedUnweightedGraph(){}
	void addEdge(Integer node1, Integer node2) {
		if(node1 > node2) addEdge(node2, node1);
		else {
			if(!edges.containsKey(node1)) edges.put(node1, new HashSet<>());
			if(edges.get(node1).add(node2)) totalEdges++;
		}
	}
	boolean hasEdge(Integer node1, Integer node2) {
		if(node1 > node2) return hasEdge(node2, node1);
		return edges.containsKey(node1) && edges.get(node1).contains(node2);
	}
	int numberOfEdges() {
		return totalEdges;
	}
	static String serialize(UndirectedUnweightedGraph graph) {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Integer, HashSet<Integer>> entry : graph.edges.entrySet()) {
            sb.append(entry.getKey());
            for(Integer next : entry.getValue()) {
                sb.append(' ').append(next);
            }
            sb.append(',');
        }
        if(sb.length() != 0) sb.setLength(sb.length() - 1);
        return sb.toString();
    }
    static UndirectedUnweightedGraph deserialize(String encoded) {
        UndirectedUnweightedGraph graph = new UndirectedUnweightedGraph();
        for(String str : encoded.split(",")) {
            String[] nodes = str.split(" ");
            HashSet<Integer> nexts = new HashSet<>();
            for(int i = 1; i < nodes.length; i++) {
                nexts.add(Integer.parseInt(nodes[i]));
            }
            graph.edges.put(Integer.parseInt(nodes[0]), nexts);
        }
        return graph;
    }

    public static void main(String[] args) {
        UndirectedUnweightedGraph graph = new UndirectedUnweightedGraph();
        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(0,2);
        graph.hasEdge(0,1);
        graph.hasEdge(0,4);
        assert 3 == graph.numberOfEdges();
        String encoded = serialize(graph);
        assert encoded.equals(serialize(deserialize(encoded)));
	}
}
    