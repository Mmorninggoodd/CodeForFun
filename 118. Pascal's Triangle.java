/*

Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return

[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]

*/

public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for(int row = 0; row < numRows; row++) {
            res.add(new ArrayList<Integer>());
            for(int col = 0; col <= row; col++) {
                if(col == 0 || col == row) res.get(row).add(1);
                else res.get(row).add(res.get(row - 1).get(col - 1) + res.get(row - 1).get(col));
            }
        }
        return res;
    }
}