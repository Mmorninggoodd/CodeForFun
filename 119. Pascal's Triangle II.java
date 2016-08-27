/*

Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?

*/

public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>(rowIndex + 1);
        for(int row = 0; row <= rowIndex; row++) {
            res.add(1);
            for(int col = row - 1; col > 0; col--) {
                res.set(col, res.get(col) + res.get(col - 1));
            }
        }
        return res;
    }
}