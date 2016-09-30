/*
Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:
If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:
tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
Example 2:
tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.

*/

/*
	Basically it is to find Euler path by Hierholzer's algorithm.
	
	Euler path: A path that only use all edge once.
	
	For the existence of Eulerian trails it is necessary that zero or two vertices have an odd degree; this means the Königsberg graph is not Eulerian. If there are no vertices of odd degree, all Eulerian trails are circuits. If there are exactly two vertices of odd degree, all Eulerian trails start at one of them and end at the other. A graph that has an Eulerian trail but not an Eulerian circuit is called semi-Eulerian.
	
	Hierholzer's algorithm:
		1. Choose any starting vertex v, and follow a trail of edges from that vertex until returning to v. It is not possible to get stuck at any vertex other than v, because the even degree of all vertices ensures that, when the trail enters another vertex w there must be an unused edge leaving w. The tour formed in this way is a closed tour, but may not cover all the vertices and edges of the initial graph.
		2. As long as there exists a vertex u that belongs to the current tour but that has adjacent edges not part of the tour, start another trail from u, following unused edges until returning to u, and join the tour formed in this way to the previous tour.

	Time & Space O(number of tickets)
*/

public class Solution {
    public List<String> findItinerary(String[][] tickets) {
        HashMap<String, PriorityQueue<String>> map = new HashMap<>();
        for(String[] ticket : tickets) {
            PriorityQueue targets = map.getOrDefault(ticket[0], new PriorityQueue<>());
            targets.offer(ticket[1]);
            map.put(ticket[0], targets);
        }
        List<String> itinerary = new LinkedList<>();
        Deque<String> stack = new ArrayDeque<>();
        stack.push("JFK");
        while(!stack.isEmpty()) {
            while(map.containsKey(stack.peek()) && !map.get(stack.peek()).isEmpty()) 
                stack.push(map.get(stack.peek()).poll());
            itinerary.add(0, stack.pop());
        }
        return itinerary;
    }
}


/*
	Follow up:
	Now we don't give start airport.

*/

/*
	As explained above:
	(1) For Euler trail, start and end point must be of odd degree.
	We can calculate degree of each place, source -1, dest +1.
	Then start point must be -1, dest must be +1.
	
	(2) For Euler Cycle, all points have even degrees. Then we can choose any one
	of them as start point.
*/

public static List<String> findItinerary(String[][] tickets) {
	/*
		Find start point
	*/
	HashMap<String, List<String>> edges = new HashMap<>();
	HashMap<String, Integer> degrees = new HashMap<>();
	for(String[] edge : tickets) {
		List<String> neighbors = edges.getOrDefault(edge[0], new ArrayList<>());
		neighbors.add(edge[1]);
		edges.put(edge[0], neighbors);
		degrees.put(edge[0], degrees.getOrDefault(edge[0], 0) - 1);
		degrees.put(edge[1], degrees.getOrDefault(edge[1], 0) + 1);
	}
	String start = tickets[0][0];  // choose any one
	for(Map.Entry<String, Integer> degree : degrees.entrySet()) {
		if(degree.getValue() == -1) {
			start = degree.getKey();
			break;
		} 
	}
	
	/*
		Reconnect tickets
	*/
	List<String> itinerary = new LinkedList<>();
	Deque<String> stack = new ArrayDeque<>();
	stack.push(start);
	while(!stack.isEmpty()) {
		while(edges.containsKey(stack.peek()) && !edges.get(stack.peek()).isEmpty())
			stack.push(edges.get(stack.peek()).remove(0));
		itinerary.add(0, stack.pop());
	}
	return itinerary;
}