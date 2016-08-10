/*

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

*/


public class Solution {
	
	/*
		Just use a stack. Time space O(n)
	*/
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for(char c : s.toCharArray()) {
            if(c == '{' || c == '[' || c == '(') stack.push(c);
            else if(c == ')' && (stack.isEmpty() || stack.pop() != '(')) return false;
            else if(c == '}' && (stack.isEmpty() || stack.pop() != '{')) return false;
            else if(c == ']' && (stack.isEmpty() || stack.pop() != '[')) return false;
        }
        return stack.isEmpty();
    }
}