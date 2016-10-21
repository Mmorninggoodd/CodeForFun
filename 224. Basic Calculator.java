/*


Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:
"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23
Note: Do not use the eval built-in library function.

*/

/*
https://discuss.leetcode.com/topic/15816/iterative-java-solution-with-stack/6

Principle:

(Sign before '+'/'-') = (This context sign);
(Sign after '+'/'-') = (This context sign) * (1 or -1);
Algorithm:

Start from +1 sign and scan s from left to right;
if c == digit: This number = Last digit * 10 + This digit;
if c == '+': Add num to result before this sign; This sign = Last context sign * 1; clear num;
if c == '-': Add num to result before this sign; This sign = Last context sign * -1; clear num;
if c == '(': Push this context sign to stack;
if c == ')': Pop this context and we come back to last context;
Add the last num. This is because we only add number after '+' / '-'.


*/
public class Solution {
    public int calculate(String s) {
		if(s == null) return 0;
        Deque<Integer> operators = new ArrayDeque<>();
		int res = 0, curNum = 0, sign = 1;
        operators.push(sign);
        for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
            if(c == '+' || c == '-') {
				res += sign * curNum;
				sign = operators.peek() * (c == '+' ? 1 : -1);
				curNum = 0;  // reset current number
			}
            else if(c == '(') {
				operators.push(sign);
            }
            else if(c == ')') {
				operators.pop();
            }
            else if(Character.isDigit(c)){
                curNum = curNum * 10 + (c - '0');
            }
        }
		res += sign * curNum;
        return res;
    }
}

/*
	Follow up: support + - * / ( )
*/