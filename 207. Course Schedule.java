/*

There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

2, [[1,0],[0,1]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.

click to show more hints.

Hints:
This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
Topological sort could also be done via BFS.

*/

/*
	There are two implementations for topological sort:
	DFS and BFS.

	Both ways need to transform the edge lists to (key: prerequisite course, value: child courses list) pairs.
	
*/

/* 9ms 82%
	1. BFS:
	Besides children lists, it also requires in-degree of each node. 
	When one node' in-degree is 0, then it doesn't have any prerequisite.
	
	So we just start from one of these courses each time and delete courses along its children.
	If there are some non-zero course's in-degree when there is no zero in-degree node left, then it means there exists cycle.
	
	Here we use a deque to store zero in-degree nodes, use an array to store in-degree of each node.
	Also each time we pop out one node from deque, we increase the count. Finally we check if count == length.

*/
public boolean canFinish(int numCourses, int[][] prerequisites) {
	int[] indegrees = new int[numCourses];
	List<Integer>[] children = new ArrayList[numCourses];
	for (int[] prerequisite : prerequisites) {   // transform list of edges to map
		int child = prerequisite[0], pre = prerequisite[1];
		if (children[pre] == null) children[pre] = new ArrayList<>();
		children[pre].add(child);
		indegrees[child]++;
	}
	Deque<Integer> deque = new ArrayDeque<>();
	for(int course = 0; course < numCourses; course++) if(indegrees[course] == 0) deque.push(course);
	int count = 0;
	while(!deque.isEmpty()) {
		int course = deque.pop();
		count++;
		if(children[course] == null) continue;
		for(int child : children[course]) {
			indegrees[child]--;
			if(indegrees[child] == 0) deque.push(child);
		}
	}
	return count == numCourses;
}


/* 5ms 98.5%
	2. DFS:
	For each course, we go along its children and find if there is any cycle in any branch.
	
	For example:
	 --> 4 -- |
	|         V   
	1 -> 2 -> 3

	Here 1,2,3 is a branch in which 4 is not belonged to it. So actually starting from 1, we will visit 3 twice from two branches respectively, but there is no cycle here. However, if we visit one node twice in one branch, then there must have cycle.
	
	We should use two visited set, one for global, one for current path.
	If we visit same node twice in current path, then there exists cycle.
	If we visit same node in global one, then we don't need to visit it again.
	
*/
public boolean canFinish(int numCourses, int[][] prerequisites) {
	boolean[] visited = new boolean[numCourses], path = new boolean[numCourses];
	List<Integer>[] nextCourses = new ArrayList[numCourses];
	for (int[] pre : prerequisites) {   // transform list of edges to map
		if (nextCourses[pre[1]] == null) nextCourses[pre[1]] = new ArrayList<>();
		nextCourses[pre[1]].add(pre[0]);
	}
	for(int i = 0; i < numCourses; i++) {
		if(hasCycle(nextCourses, visited, path, i)) return false;
	}
	return true;
}
private boolean hasCycle(List<Integer>[] nextCourses, boolean[] visited, boolean[] path, int course) {
	if(path[course]) return true;               // visited before by this dfs
	if(visited[course] || nextCourses[course] == null) return false; // until the end or visited before
	visited[course] = true;
	path[course] = true;
	for(int child : nextCourses[course]) {
		if(hasCycle(nextCourses, visited, path, child)) {
			path[course] = false;
			return true;
		}
	}
	path[course] = false;
	return false;
}