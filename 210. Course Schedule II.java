/*

There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]

4, [[1,0],[2,0],[3,1],[3,2]]
There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.

*/

public class Solution {
	/*
		Just a few lines change from 207
	*/
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        int[] sequence = new int[numCourses];
        List<Integer>[] children = new ArrayList[numCourses];
        for(int[] pre : prerequisites) {
            if(children[pre[1]] == null) children[pre[1]] = new ArrayList<>();
            children[pre[1]].add(pre[0]);
            indegree[pre[0]]++;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int count = 0;
        for(int i = 0; i < numCourses; i++) if(indegree[i] == 0) stack.push(i);
        while(!stack.isEmpty()) {
            int course = stack.pop();
            sequence[count++] = course;
            if(children[course] == null) continue;
            for(int child : children[course]) {
                indegree[child]--;
                if(indegree[child] == 0) stack.push(child);
            }
        }
        if(count != numCourses) return new int[0];
        return sequence;
    }
}