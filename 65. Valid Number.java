/*

Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.

Update (2015-02-10):
The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button  to reset your code definition.

*/

public class Solution {
	/* 3ms 96.24%
		
		Not a hard algorithmic problem, just make sure you consider all corner cases.
	
	*/
    public boolean isNumber(String s) {
        boolean hasNumber = false;
        boolean end = false;
        boolean exp = false;
        boolean dot = false;
        int i = 0, n = s.length();
        while(i < n && s.charAt(i) == ' ') i++; // skip leading spaces
        if(i == n) return false; // No number
        if(s.charAt(i) == '+' || s.charAt(i) == '-') i++; // handle sign
        for(; i < n; i++) {
            char c = s.charAt(i);
            if(c == ' ') {  // ending space
                if(!end) end = true;
                continue;
            }
            else if(end) return false;  // There are non-spaces chars after end
            
            if('0' <= c && c <= '9') hasNumber = true;
            else if(c == '.') {
                if(dot || exp) return false; // multiple dots or exits dot in exponent part
                dot = true;
            }
            else if(c == 'e') {
                if(exp || !hasNumber || i == n - 1) return false; // multiple exp or no digit before e or already arrive the end
                if(s.charAt(i + 1) == '+' || s.charAt(i + 1) == '-') i += 1;  // skip sign of exponent part
                hasNumber = false;  // avoid there is no number on exponent part
                exp = true;
            }
            else return false; // invalid chars
        }
        return hasNumber;
    }
}