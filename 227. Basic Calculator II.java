/*
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5
Note: Do not use the eval built-in library function.

*/


public class Solution {
    /*
        Think of this problem in two level:
            lower level: partial result of + -   preNum
            deeper level: partial result of * /   curNum nextNum
            
        op: 0: no pending * or /
            1: *
            -1: /
            
        sign:   1: +
                -1: -
				
		O(n) time O(1) space
    */
    public int calculate(String s) {
        int preNum = 0, curNum = 0, nextNum = 0, sign = 1, op = 0;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                nextNum = nextNum * 10 + (c - '0');
                if(i == s.length() - 1 || !Character.isDigit(s.charAt(i + 1))) {  // push nextNum to curNum (regardless there is any pending * or /)
                    curNum = (op == 0 ? nextNum : op == 1 ? curNum * nextNum : curNum / nextNum);
                    nextNum = 0;
                    op = 0;
                }
            }
            else if(c == '+' || c == '-') {  // indicate that deeper level can be pushed to lower level
                preNum += sign * curNum;
                curNum = 0;
                sign = (c == '+' ? 1 : -1);
            }
            else if(c == '*' || c == '/') {
                op = (c == '*' ? 1 : -1);
            }
        }
        return preNum + sign * curNum;
    }
}

/*
	Follow up:
	
	What if we can add one pair of parenthesis, return the maximum value we can get.
*/
/*
	A brute force way: 
		two for loop to choose every possible parenthesis places, then calculate three parts of 
		substrings.
		O(n^3) time
		
	Another way is calculate every possible substring's value, then calculate all possible results in O(n^2),
	but need O(n^2) space.
	
	Hint: Can use memo to reduce repeated calculations, and sort.
	"1+2+3" / "2+1+3" ->  "++123"
*/