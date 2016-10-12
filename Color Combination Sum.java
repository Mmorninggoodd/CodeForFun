/*
	Given an array of colors (array of size 3), find if there exists a combination that sum to target color.
	Each color can only be used once, and must use at least one color.
	
	For example,
	[[30, 20, 10], [3, 4, 5], [10, 3, 4]]
	target [33, 24, 15] -> true
	target [33, 24, 16] -> false
	target [60, 40, 20] -> false
	target [0, 0, 0] -> false
*/

/*
	Just similar to 39. Combination Sum
	Backtracking.
*/

public static boolean colorCombination(int[][] colors, int[] target) {
	int[] cur = new int[3];
	for(int i = 0; i < colors.length; i++) {
		if(colorCombination(colors, i, cur, target)) return true;
	}
	return false;
}
private static boolean colorCombination(int[][] colors, int start, int[] cur, int[] target) {
	for(int i = 0; i < 3; i++) cur[i] += colors[start][i];
	if(Arrays.equals(cur, target)) return true;
	if(start == colors.length - 1) return false;
	for(int i = start + 1; i < colors.length; i++) {
		if(colorCombination(colors, i, cur, target)) return true;
	}
	for(int i = 0; i < 3; i++) cur[i] -= colors[start][i];
	return false;
}