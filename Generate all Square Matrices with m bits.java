/*
	Given n and m, print all square matrix of size n*n filled with m bits.
	
	For example, n = 2, m = 1, return
		10
		00
		
		01
		00
		
		00
		10
		
		00
		01

	Follow up: How to remove duplicates if symmetric is treated as duplicates?
	For example,
	10					00
	00  is duplicate of 01
*/

/*
	Using backtracking to generate all matrices.
	Using BitSet to store in HashSet.
	
	Note that symmetric case should be output as well.

	Time Complexity:
		There are n*n choose m possible solution, and print each matrix takes O(n*n) time.
		So total time complexity is ((n*n)! * (n*n)) / (m! * (n*n - m)!)
	Space Complexity is the same as time complexity (if need to remove duplicate)
					 is O(n*n) if don't need to remove duplicates
*/
private static void printSquare(int n, int m) {
	if(m > n*n) return;
	//HashSet<>
	printSquare(m, new int[n][n], 0, new HashSet<>());
}
private static void printSquare(int m, int[][] square, int start, HashSet<BitSet> res) {
	if(m == 0) {
		if(!isDuplicate(square, res)) {
			printMatrix(square);
		}
		return;
	}
	int n = square.length;
	for(int next = start; next < n * n - m + 1; next++) {
		square[next / n][next % n] = 1;
		printSquare(m - 1, square, next + 1, res);
		square[next / n][next % n] = 0;
	}
}
private static boolean isDuplicate(int[][] matrix, HashSet<BitSet> res) {
	if(!res.add(hashMatrix(matrix))) return true;
	// diagonally
	if(!reverseMatrixDiagonally(matrix)) {   // not symmetric
		if(!res.add(hashMatrix(matrix))) return true;
		reverseMatrixDiagonally(matrix);
	}
	return false;
}
private static boolean reverseMatrixDiagonally(int[][] matrix) {  // return true, if it is symmetric matrix
	int n = matrix.length, left = 0, right = n * n - 1;
	boolean isSymmetric = true;
	for(; left < right; left++, right--) {
		if(matrix[left / n][left % n] != matrix[right / n][right % n]) isSymmetric = false;
		int tmp = matrix[left / n][left % n];
		matrix[left / n][left % n] = matrix[right / n][right % n];
		matrix[right / n][right % n] = tmp;
	}
	return isSymmetric;
}
private static BitSet hashMatrix(int[][] matrix) {
	int n = matrix.length;
	BitSet hashcode = new BitSet(n*n);
	for(int i = 0; i < n * n; i++) {
		if(matrix[i / n][i % n] == 1) hashcode.set(i, true);
	}
	return hashcode;
}
private static void printMatrix(int[][] matrix) {
	for(int[] row : matrix) {
		System.out.println(Arrays.toString(row));
	}
	System.out.println();
}