/*

Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.


Example 1
Input: "2-1-1".

((2-1)-1) = 0
(2-(1-1)) = 2
Output: [0, 2]


Example 2
Input: "2*3-4*5"

(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
Output: [-34, -14, -10, -10, 10]
*/

/* 2ms 99.80%
	Time & Space Exponential (Catalan Number)
	use a HashMap to store calculated results.
	Basically each time choose one sign as delimiter.
	
	1 + 1 * 3 - 9
	we just iterate:
	(1) + (1 *3 - 9)
	(1 + 1) * (3 - 9)
	(1 + 1 * 3) - (9)
	
	Then for each substring, recursively call this function again.

*/
public class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        return diffWaysToCompute(input, new HashMap<String, List<Integer>>());
    }
    private List<Integer> diffWaysToCompute(String input, Map<String, List<Integer>> map) {
        if(map.containsKey(input)) return map.get(input);
        List<Integer> res = new ArrayList<>();
        for(int i = 1; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c == '*' || c == '+' || c == '-') {
                List<Integer> leftList = diffWaysToCompute(input.substring(0, i), map);
                List<Integer> rightList = diffWaysToCompute(input.substring(i + 1), map);
                for(Integer left : leftList) {
                    for(Integer right : rightList) {
                        switch (c) {
                            case '+':
                                res.add(left + right);
                                break;
                            case '-':
                                res.add(left - right);
                                break;
                            case '*':
                                res.add(left * right);
                                break;
                        }
                    }
                }
            }
        }
        if(res.isEmpty()) res.add(Integer.valueOf(input));
        map.put(input, res);
        return res;
    }
}