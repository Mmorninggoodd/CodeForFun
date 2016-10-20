/*

	Given a maze, any point on the left is entrance, any point on the right is exit.
	Find shortest path.

*/

import java.util.*;

class Coordinate {
    int x, y;
    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class Solution {
    /*
        BFS
		O(n*m) time O(1) / O(n*m) space
    */
    public static int findPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int n = matrix.length, m = matrix[0].length;
        final int ROAD = 1, WALL = 0, VISITED = 2;
        final int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
		Coordinate[][] backtrace = new Coordinate[n][m];
        Deque<Coordinate> queue = new ArrayDeque<>();
        int len = 1;
        for(int i = 0; i < n; i++) {
            if(matrix[i][0] == ROAD) {
                queue.offer(new Coordinate(i, 0));
                matrix[i][0] = VISITED;
            }
        }
		LinkedList<Coordinate> path = new LinkedList<>();
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                Coordinate cur = queue.poll();
                if(cur.y == m - 1) {  // reach destination
					path.add(0, cur);
					while(cur.y != 0) {
						cur = backtrace[cur.x][cur.y];
						path.add(0, cur);
					}
					return path.size();
					//return len; 
				}
                for(int[] dir : dirs) {
                    int x = cur.x + dir[0], y = cur.y + dir[1];
                    if(x < 0 || x >= n || y < 0 || y >= m || matrix[x][y] != ROAD) continue;
                    queue.offer(new Coordinate(x, y));
                    matrix[x][y] = VISITED;
					backtrace[x][y] = cur;
                }
            }
            //len++;
        }
        return -1;
    }
    public static void main(String[] args) {
        int rt = findPath(new int[][] {
                {1, 1, 1, 1, 0}, 
                {1, 1, 1, 1, 1}});
        System.out.print(rt);
        
    }
}