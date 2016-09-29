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

/*
	Basically we only consider two numbers:
	
	preNum  preOperator  curNum  (1 + 2)
	
	where preOperator can only be + or -
	
	When we meet * or -, we can directly curNum * or / next number.
	So I store curNum to pendingNum in case of * or -, and store
	this operator to pendingOperator.
	
	Next time when we load finish reading next number (meet a new operator
	or reach the end of string), we just calculate pendingNum * or / curNum,
	where curNum actually is the next number we want.
	
	So pendingOperator can only be * or /.
	
	Time O(n) Space O(1)
*/
public class Solution {
    public int calculate(String s) {
        int curNum = 0, preNum = 0, pendingNum = 0;;
        char preOperator = '+', pendingOperator = '*';
        boolean tdPending = false;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c >= '0' && c <= '9') curNum = curNum * 10 + (c - '0');
            if(("+-*/".indexOf(c) >= 0 || i == s.length() - 1) && tdPending) {
                if(pendingOperator == '*') curNum = pendingNum * curNum;
                else curNum = pendingNum / curNum;
                tdPending = false;
                pendingNum = 0;
            }
            if(i == s.length() - 1 || c == '+' || c == '-') {
                if(preOperator == '+') preNum += curNum;
                else preNum -= curNum;
                curNum = 0;
                preOperator = c;
            }
            else if(c == '*' || c == '/') {
                tdPending = true;
                pendingNum = curNum;
                pendingOperator = c;
                curNum = 0;
            }
        }
        return preNum;
    }
}