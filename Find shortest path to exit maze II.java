/*
	Find shortest path to exit maze II

	Now give a start point and destination. There are WALLs and ROADs.
	
	0: ROAD 1: start 5: wall 9: destination
	
	Now you can break the wall with a extra cost of 1.
	Find the path with the least costs. (breaks the least walls)
*/

/*
	DP + BFS
*/
class Coordinate {
	int x, y;
	Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public String toString() {
		return "[" + this.x + "," + this.y +"]";
	}
}
public static int findPath(int[][] maze, Coordinate start, Coordinate dest) {
	int n = maze.length, m = maze[0].length;
	final int WALL = 5, ROAD = 0;
	int[][] costs = new int[n][m];
	Coordinate[][] backtrace = new Coordinate[n][m];
	for(int[] costRow : costs) Arrays.fill(costRow, Integer.MAX_VALUE);
	costs[start.x][start.y] = 0;
	int[][] dirs = new int[][]{{0,1},{1,0},{-1,0},{0,-1}};
	Deque<Coordinate> queue = new ArrayDeque<>();
	queue.offer(start);
	while(!queue.isEmpty()) {
		Coordinate cur = queue.poll();
		for(int[] dir : dirs) {
			Coordinate next = new Coordinate(cur.x + dir[0], cur.y + dir[1]);
			if(next.x < 0 || next.x >= n || next.y < 0 || next.y >= m ||
					(maze[next.x][next.y] == ROAD && costs[next.x][next.y] <= costs[cur.x][cur.y]) ||
					(maze[next.x][next.y] == WALL && costs[next.x][next.y] <= costs[cur.x][cur.y] + 1))  // be careful here
				continue;
			costs[next.x][next.y] = costs[cur.x][cur.y] + (maze[next.x][next.y] == WALL ? 1 : 0);
			backtrace[next.x][next.y] = cur;
			queue.offer(next);
		}
	}
	LinkedList<Coordinate> path = new LinkedList<>();
	getPath(dest, start, path, backtrace);
	System.out.print(path);
	return costs[dest.x][dest.y];
}
private static void getPath(Coordinate start, Coordinate dest, LinkedList<Coordinate> path, Coordinate[][] backtrace) {
	path.addFirst(start);
	if(start.x == dest.x && start.y == dest.y) return;
	getPath(backtrace[start.x][start.y], dest, path, backtrace);
}
public static void main(String[] args) {
	findPath(new int[][]{
			{0,0,0,0},
			{5,5,5,5},
			{0,0,5,5},
			{5,0,0,0}
	}, new Coordinate(0,0), new Coordinate(3,3));
}