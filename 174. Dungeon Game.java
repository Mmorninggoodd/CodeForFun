/*

The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.


Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.

-2 (K)	-3	3
-5	-10	1
10	30	-5 (P)

Notes:

The knight's health has no upper bound.
Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
*/

public class Solution {
    /*
        A typical path DP problem but need record maximum during path.
		However, this requires to be filled from destination to start point,
		Because in order to compute HP[i][j], you will need to make sure of two things:

		1. your HP[i][j]+dungeon[i][j] should be >0
		2. your HP[i][j]+dungeon[i][j] should be large enough, so that it will be sufficient to cover the minimum requirement on HP for the next step, be it right or down (take whichever requires smaller HP).

		Because of the second requirement, your calculation of HP[i][j] will depend on later steps that you could take.

		Also make sure that HP maintains to be higher than 0
		
        dp[i,j] = min HP needed to get to destination from [i,j]
        dp[i,j] = max( -dungeon[i,j] + min(dp[i + 1, j], dp[i, j + 1]) , 1)
        
        boundary:
        dp[0,0] = max(1, -dungeon[0,0] + 1)
        
        Can be optimized to O(m) space O(m*n) time
    */
    public int calculateMinimumHP(int[][] dungeon) {
        if(dungeon.length == 0 || dungeon[0].length == 0) return 0;
        int n = dungeon.length, m = dungeon[0].length;
        int[] minHP = new int[m];
        for(int i = n - 1; i >= 0; i--) {
            for(int j = m - 1; j >= 0; j--) {
                if(i == n - 1 && j == m - 1) minHP[j] = Math.max(1, - dungeon[i][j] + 1);
				else if(i == n - 1) minHP[j] = Math.max(1, - dungeon[i][j] + minHP[j + 1]);
				else if(j == m - 1) minHP[j] = Math.max(1, - dungeon[i][j] + minHP[j]);
                else minHP[j] = Math.max(1, - dungeon[i][j] + Math.min(minHP[j], minHP[j + 1]));
            }
        }
        return minHP[0];
    }
}