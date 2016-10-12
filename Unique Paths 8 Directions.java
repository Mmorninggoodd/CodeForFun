/*
	Given an matrix, 0 means road, 1 means obstacle.
	Count unique paths from start point to end point in k steps.

*/
public static int uniquePaths(int[][] matrix, int k, int x1, int y1, int x2, int y2) {
	int n = matrix.length, m = matrix[0].length;
	int[][] dp = new int[n][m], tmp = new int[n][m];
	if(matrix[x1][y1] == 1) return 0;
	dp[x1][y1] = 1;
	for(; k > 0; k--) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(matrix[i][j] != 1) {
					tmp[i][j] = getValue(dp, i - 1, j - 1) + getValue(dp, i - 1, j) + getValue(dp, i, j - 1) + getValue(dp, i + 1, j - 1)
							+ getValue(dp, i - 1, j + 1) + getValue(dp, i + 1, j) + getValue(dp, i, j + 1) + getValue(dp, i + 1, j + 1);
				}
			}
		}
		int[][] tmp2 = dp;
		dp = tmp;
		tmp = tmp2;
	}
	return dp[x2][y2];
}
private static int getValue(int[][] dp, int x ,int y) {
	if(x < 0 || x >= dp.length || y < 0 || y >= dp[0].length) return 0;
	return dp[x][y];
}