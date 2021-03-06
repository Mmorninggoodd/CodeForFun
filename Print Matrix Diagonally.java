/*

Print Matrix Diagonally
Given a 2D matrix, print all elements of the given matrix in diagonal order. For example, consider the following 5 X 4 input matrix.

    1     2     3     4
    5     6     7     8
    9    10    11    12
   13    14    15    16
   17    18    19    20
Diagonal printing of the above matrix is

    1
    5     2
    9     6     3
   13    10     7     4
   17    14    11     8
   18    15    12
   19    16
   20
   
*/

/*
	O(m*n) time O(1) space
	There are m + n - 1 diagonal line.
	For ith line, there are min(n + m - line, line, m, n) elements on the diagonal direction.
	Their start coordinate is (max(line - m, 0), max(m - line, 0))
*/
public static void printDiagonal(int[][] matrix) {
	if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
	int n = matrix.length, m = matrix[0].length, nline  = n + m - 1, maxElements = Math.min(m, n);
	for(int line = 1; line <= nline; line++) {
		int count = Math.min(n + m - line, Math.min(maxElements, line));
		for(int row = Math.max(line - m, 0), col = Math.max(m - line, 0); count > 0; count--) {
			System.out.print(matrix[row][col]);
			System.out.print(" ");
			row++;
			col++;
		}
		System.out.println();
	}
}
	
/*
	Just append result to corresponding index (i + j)
	Note that there are n + m - 1 rows.
	Start filling from left bottom to right top.
	
	Time O(n*m) Space O(n*m)
*/
public static List<String> printDiagonal(int[][] matrix) {
	if(matrix == null || matrix.length == 0) return new ArrayList<>();
	int n = matrix.length, m = matrix[0].length;
	ArrayList<StringBuilder> sbs = new ArrayList<>(m + n - 1);
	for(int i = 0; i < m + n - 1; i++) sbs.add(new StringBuilder());
	for(int j = 0; j < m; j++) {
		for(int i = n - 1; i >= 0; i--) {
			sbs.get(i + j).append(matrix[i][j]);
			if(i != 0) sbs.get(i + j).append(" ");
		}
	}
	ArrayList<String> res = new ArrayList<>();
	for(StringBuilder sb : sbs) res.add(sb.toString());
	return res;
}