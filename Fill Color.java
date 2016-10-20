/*

	Fill a color around one point. Fill all points that with the same color of target point.

	3 3 3 3
	2 2 1 4
	3 2 3 4
	
	Fill (1,1) as color 5
	
	->
	3 3 3 3
	5 5 1 4
	3 5 3 4
*/


/*
	A simple BFS/DFS can finish.

*/
static final int[][] dirs = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
public static void fill(int[][] image, int x, int y, int color) {
	dfs(image, x, y, image[x][y], color);
}
private static void dfs(int[][] image, int x, int y, int oldColor, int color) {
	if(x < 0 || x >= image.length || y < 0 || y >= image[0].length || image[x][y] != oldColor) return;
	image[x][y] = color;
	for(int[] dir : dirs) {
		dfs(image, x + dir[0], y + dir[1], oldColor, color);
	}
}