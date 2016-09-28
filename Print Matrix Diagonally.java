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