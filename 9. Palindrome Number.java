/*
Determine whether an integer is a palindrome. Do this without extra space.

click to show spoilers.

Some hints:
Could negative integers be palindromes? (ie, -1)

If you are thinking of converting the integer to string, note the restriction of using extra space.

You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. How would you handle such case?

There is a more generic way of solving this problem.

*/

public class Solution {
	
	/*
		Only reverse half of number
		so that we don't need to take care of overflow
	
	*/
	public boolean isPalindrome(int x) {
        if(x < 0 || (x % 10 == 0 && x != 0)) return false;
        int y = 0;
        while(x > y) {
            y = x % 10 + y * 10;
            x /= 10;
        }
        return y == x || (x == y / 10);
    }
	
	
	/*
		Just reverse whole number
	*/
    public boolean isPalindrome(int x) {
        if(x < 0) return false;
        long y_l = 0, x_l = (long) x;
        while(x_l != 0) {
            y_l = x_l % 10 + y_l * 10;
            x_l /= 10;
            if(y_l > Integer.MAX_VALUE) return false;
        }
        return (int) y_l == x;
    }
}