/*

Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
Note that you can only put the bomb at an empty cell.

Example:
For the given grid

0 E 0 0
E 0 W E
0 E 0 0

return 3. (Placing a bomb at (1,1) kills 3 enemies)

*/
/*
	Bruce force, try to place bomb to every possible place, and then calculate number of enemies can be destroyed.
	Takes O(n*m*(n+m)) time O(1) space
*/
/*
	O(n^2) time, O(1) space
	col[i][j] = max number of enemies can be destroyed on the column j from [i,j]
	row[i][j] = max number of enemies can be destroyed on the row i from [i,j]
	
	The trick here is to eliminate duplicate calculation of col[i][j] and row[i][j]
	We only update them when meet 'W'.
	
	if j == 0 || matrix[i][j - 1] == 'W', recalculate row[i][j]
	otherwise, row[i][j] = row[i][j - 1]
	
	Similarly,
	if i == 0 || matrix[i - 1][j] == 'W', recalculate col[i][j]
	otherwise, col[i][j] = col[i - 1][j]
	
	max number of enemies can be destroyed on matrix[i][j] = col[i][j] + row[i][j] if [i,j] is '0'
	
	From analysis above, we know that we only need to keep 1-d array.
*/
public int maxKilledEnemies(char[][] grid) {
	if(grid.length == 0 || grid[0].length == 0) return 0;
	int[] col = new int[grid[0].length];
	int row = 0, max = 0;
	for(int i = 0; i < grid.length; i++) {
		for(int j = 0; j < grid[0].length; j++) {
			if(j == 0 || grid[i][j - 1] == 'W') {  // recalculate row[i][j]
				row = 0;
				for(int k = j; k < grid[0].length && grid[i][k] != 'W'; k++) {
					if(grid[i][k] == 'E') row++;
				}
			}
			if(i == 0 || grid[i - 1][j] == 'W') {  // recalculate col[i][j]
				col[j] = 0;
				for(int k = i; k < grid.length && grid[k][j] != 'W'; k++) {
					if(grid[k][j] == 'E') col[j]++;
				}
			}
			if(grid[i][j] == '0') max = Math.max(max, col[j] + row);  // update max when meet '0'
		}
	}
	return max;
}
