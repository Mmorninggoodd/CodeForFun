/*
	Given a 2-d array which store heights of each point, and start point and end point.
	Suppose you have a helicopter, you can only going up, and not able to go down for any time.

	Find a path that sum of heights is minimum.
*/

/*
	Can use Dijkstra's algorithm, but there are two problems:
	1. PriorityQueue in Java doesn't support decrease key operation:
		Solution 1: Just insert updated state. Because old state must has higher cost, which means it won't be used latter.
		Solution 2: Use a TreeSet. pollFirst() first() pollLast() last()
	2. Our helicopter can not go down, we need to record current height for each step.
*/