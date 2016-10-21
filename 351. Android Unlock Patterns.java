/*
Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the total number of unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.

Rules for a valid pattern:

Each pattern must connect at least m keys and at most n keys.
All the keys must be distinct.
If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
The order of keys used matters.
 



Explanation:

| 1 | 2 | 3 |
| 4 | 5 | 6 |
| 7 | 8 | 9 |
 

Invalid move: 4 - 1 - 3 - 6 
Line 1 - 3 passes through key 2 which had not been selected in the pattern.

Invalid move: 4 - 1 - 9 - 2
Line 1 - 9 passes through key 5 which had not been selected in the pattern.

Valid move: 2 - 4 - 1 - 3 - 6
Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern

Valid move: 6 - 5 - 4 - 1 - 9 - 2
Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.

Example:
Given m = 1, n = 1, return 9.

*/

/*
	Backtracking, but need to handle the extra constraint: only to select middle number when we want 
	to select its neighbors together.
	
	Note that you cannot first define their neighbors, for example if you choose 5 before, then you can
	select 9 when starting from 1.
	
	Exponential Time. O(1)/ O(10*10) space.
*/
public static int numberOfPatterns(int m, int n) {
	int[][] skip = new int[10][10];
	skip[1][3] = skip[3][1] = 2;
	skip[1][7] = skip[7][1] = 4;
	skip[3][9] = skip[9][3] = 6;
	skip[7][9] = skip[9][7] = 8;
	skip[1][9] = skip[9][1] = skip[2][8] = skip[8][2] = skip[3][7] = skip[7][3] = skip[4][6] = skip[6][4] = 5;
	boolean[] selected = new boolean[10];
	int res = 0;
	for(int i = m; i <= n; i++) {
		res += dfs(1, selected, skip, i) * 4;
		res += dfs(2, selected, skip, i) * 4;
		res += dfs(5, selected, skip, i);
	}
	return res;
}
private static int dfs(int curNum, boolean[] selected, int[][] skip, int remain) {
	if(remain == 0) return 1;
	selected[curNum] = true;
	int count = 0;
	for(int next = 1; next <= 9; next++) {
		if(!selected[next] && (skip[curNum][next] == 0 || selected[skip[curNum][next]])) {
			count += dfs(next, selected, skip, remain - 1);
		}
	}
	selected[curNum] = false;
	return count;
}