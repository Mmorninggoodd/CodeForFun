/*
	Given a 2-d array which store heights of each point, and start point and end point.
	Suppose you have a helicopter, you can only going up, and not able to go down for any time.

	Find a path that sum of heights is minimum.
*/

/*
	Can use Dijkstra's algorithm, but there are two problems:
	1. PriorityQueue in Java doesn't support decrease key operation:
		Possible Solution 1: Just insert updated state. Because old state must has higher cost, which means it won't change other nodes latter.
		Possible Solution 2: Use a TreeSet. But TreeSet doesn't allow duplicates, so this method won't work.
	2. Our helicopter can not go down, we need to record current height for each step.
*/

/*
	One assumption here: the helicopter can only fly to same level or higher place, so 
	that we don't need to consider actual height of the helicopter at each location.
	
	11111
	55555
	11111
	
	so here (0,0) can never go to (2,0), since it is impossible to from 5 to 1.
*/
static class Coordinate {
	int x, y;
	Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public static int helicopterPath(int[][] heights, Coordinate start, Coordinate end) {
	final int[][] dirs = new int[][]{{0,1}, {1,0}, {-1,0}, {0,-1}};
	int n = heights.length, m = heights[0].length;
	int[][] totalCost = new int[n][m];
	Coordinate[][] prev = new Coordinate[n][m];
	for(int[] row : totalCost) Arrays.fill(row, Integer.MAX_VALUE);
	PriorityQueue<Coordinate> frontier = new PriorityQueue<>((o1, o2) -> Integer.compare(totalCost[o1.x][o1.y], totalCost[o2.x][o2.y]));
	frontier.add(start);
	totalCost[start.x][start.y] = heights[start.x][start.y];
	while(!frontier.isEmpty()) {
		Coordinate cur = frontier.poll();
		if(cur.equals(end)) break;  // find destination
		for(int[] dir : dirs) {
			Coordinate next = new Coordinate(cur.x + dir[0], cur.y + dir[1]);
			if(next.x >= 0 && next.x < n && next.y >= 0 && next.y < m && heights[next.x][next.y] >= heights[cur.x][cur.y]) {
				int newCost = heights[next.x][next.y] + totalCost[cur.x][cur.y];
				if(newCost < totalCost[next.x][next.y]) {
					totalCost[next.x][next.y] = newCost;
					frontier.add(next);
					prev[next.x][next.y] = cur;  // add for trace
				}
			}
		}
	}
	List<Coordinate> res = new LinkedList<>();
	if(totalCost[end.x][end.y] != Integer.MAX_VALUE)  {
		traceBack(end, start, prev, res);
		for(Coordinate point : res) {
			System.out.print("[" + point.x + "," + point.y + "]");
		}
	}
	System.out.println("\nTotal cost:" + totalCost[end.x][end.y]);
	return totalCost[end.x][end.y];
}
private static void traceBack(Coordinate start, Coordinate end, Coordinate[][] prev, List<Coordinate> res) {
	if(start == null) return;
	res.add(0, start);
	if(start.equals(end)) return;
	traceBack(prev[start.x][start.y], end, prev, res);
}
public static void main(String[] args) {
	helicopterPath(new int[][]{
			{1,1,1,2,5},
			{1,5,1,5,5},
			{1,1,1,1,1}
	}, new Coordinate(2, 4), new Coordinate(0, 4));
}