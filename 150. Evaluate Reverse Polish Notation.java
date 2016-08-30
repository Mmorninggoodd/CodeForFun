/*

Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
  
*/

public class Solution {
	/*
		use a stack. O(n) time & space
	*/
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for(String s : tokens) {
            if(s.equals("+")) stack.push(stack.pop() + stack.pop());
            else if(s.equals("*")) stack.push(stack.pop() * stack.pop());
            else if(s.equals("-")) {
                int num1 = stack.pop(), num2 = stack.pop();
                stack.push(num2 - num1);
            }
            else if(s.equals("/")) {
                int num1 = stack.pop(), num2 = stack.pop();
                stack.push(num2 / num1);
            }
            else stack.push(Integer.valueOf(s));
        }
        return stack.pop();
    }
}