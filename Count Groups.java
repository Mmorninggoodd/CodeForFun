/*
	Counting groups

	Given an 2-dim array only contains 0 or 1 in each cell.
	Two cells are in the same group, if they are 1's and adjacent (abs(x1 - x2) <= 1 && abs(y1 - y2) <= 1)
	
	Given an array b contains different sizes, you need to return another array of same length that whose ith element is number of groups of size bi.
	
	For example,
	1 0
	0 1
	
	b: [0 1 2 5 5]
	
	return [2 2 0 0 0] (2 groups of size 0, 2 groups of size 1, no group of size 2, no group of size 5)
*/

/*
	Just DFS. O(n) where n is number of elements in the matrix.
	
	One issue: Might Overflow.
*/
public int[] countGroups(int[][] matrix, int[] b) {
	HashMap<Integer, List<Integer>> sizeToIndex = new HashMap<>();
	int[] res = new int[b.length];
	for(int i = 0; i < b.length; i++) {
		int size = b[i];
		if(!sizeToIndex.containsKey(size)) sizeToIndex.put(size, new ArrayList<Integer>());
		List<Integer> list = sizeToIndex.get(size);
		list.add(i);
	}
	for(int x = 0; x < matrix.length; x++) {
		for(int y = 0; y < matrix[0].length; y++) {
			if(matrix[x][y] != 1) continue;
			int size = dfs(matrix, x, y);
			if(sizeToIndex.containsKey(size)) {
				for(Integer index : sizeToIndex.get(size)) res[index]++;
			}
		}
	}
	return res;
}
private int dfs(int[][] matrix, int x, int y) {
	if(x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length || matrix[x][y] != 1) return 0;
	matrix[x][y] = 0;
	return 1 + dfs(matrix, x + 1, y) + dfs(matrix, x, y + 1) + dfs(matrix, x - 1, y) + dfs(matrix, x, y - 1);
}