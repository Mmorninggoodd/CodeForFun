/*

	Given a matrix, if matrix[i][j] == 1, then i knows j.
	
	This matrix is symmetric.
	
	Find the number of group of friends. (Anyone in this group must be known by someone in this group)
	
*/
import java.util.*;
public class Solution {
    /*
        Similar with number of islands, but only explore in current row and column.
		DFS
    */
    public static int findGroupOfFriends(int[][] matrix) {
        int numGroup = 0;
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 1) {
                    findFriends(matrix, i, j);
                    numGroup++;
                }
            }
        }
        return numGroup;
    }
    private static void findFriends(int[][] matrix, int x, int y) {
        matrix[x][y] = 0;
        for(int i = x + 1; i < matrix.length; i++) {  // explore current column
            if(matrix[i][y] == 1) findFriends(matrix, i, y);
        }
        for(int j = y + 1; j < matrix[0].length; j++) {  // explore current row
            if(matrix[x][j] == 1) findFriends(matrix, x, j);
        }
    }
    public static void main(String[] args) {
        int rt = findGroupOfFriends(new int[][] {
                {1,1,0,0},
                {1,1,1,0},
                {0,1,1,0},
                {0,0,0,1}});
        System.out.print(rt);
        
    }
}

/*
	Follow up:
	Output each group.

*/

public static List<List<Integer>> findGroupOfFriends(int[][] matrix) {
	List<List<Integer>> groups = new ArrayList<>();
	for(int i = 0; i < matrix.length; i++) {
		for(int j = 0; j < matrix[0].length; j++) {
			if(matrix[i][j] == 1) {
				HashSet<Integer> set = new HashSet<>(); // Use HashSet to remove duplicate
				findFriends(matrix, i, j, set);
				groups.add(new ArrayList<>(set));  // convert to list
			}
		}
	}
	return groups;
}
private static void findFriends(int[][] matrix, int x, int y, HashSet<Integer> set) {
	matrix[x][y] = 0;
	set.add(x);  // add in group
	set.add(y);
	for(int i = x + 1; i < matrix.length; i++) {
		if(matrix[i][y] == 1) findFriends(matrix, i, y, set);
	}
	for(int j = y + 1; j < matrix[0].length; j++) {
		if(matrix[x][j] == 1) findFriends(matrix, x, j, set);
	}
}