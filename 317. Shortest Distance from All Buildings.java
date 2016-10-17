/*
You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.
For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):

1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.

Note:
There will be at least one building. If it is not possible to build such house according to the above rules, return -1.

*/

/*
	
	Use counts[][] to record how many building can access to this point.
	dist[][] to record current sum of minimum distance from all buildings
	
	Run BFS from each building, then update dist and counts.
	
	Note counts[][] here can be served as "visited" matrix, and avoid visiting points that cannot visited by every building.
*/
final static int[][] dirs = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};
public static int shortestDistance(int[][] grid) {
	if(grid == null || grid.length == 0 || grid[0].length == 0) return -1;
	int minDist = -1, n = grid.length, m = grid[0].length, count = 1;
	int[][] dist = new int[n][m];
	int[][] counts = new int[n][m];
	for(int i = 0; i < n; i++) {
		for(int j = 0; j < m; j++) {
			if(grid[i][j] == 1) minDist = bfs(grid, dist, counts, count++, i, j);
		}
	}
	return minDist;
}
private static int bfs(int[][] grid, int[][] dist, int[][] counts, int count, int i, int j) {
	int n = grid.length, m = grid[0].length, depth = 1, minDist = -1;
	Deque<int[]> queue = new ArrayDeque<>();
	queue.offer(new int[]{i, j});
	while(!queue.isEmpty()) {
		int size = queue.size();
		while(size-- > 0) {
			int[] coordinate = queue.poll();
			for(int[] dir : dirs) {
				int x = coordinate[0] + dir[0], y = coordinate[1] + dir[1];
				if(x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 0 && counts[x][y] == count - 1) {
					queue.offer(new int[]{x, y});
					dist[x][y] += depth;
					counts[x][y] = count;
					if(minDist == -1 || minDist > dist[x][y]) minDist = dist[x][y];
				}
			}
		}
		depth++;
	}
	return minDist;
}