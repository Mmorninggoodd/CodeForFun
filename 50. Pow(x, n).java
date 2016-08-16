/*
Implement pow(x, n).
*/

public class Solution {
	/*
		1ms
		Basic idea: We can decompose this math problem into:
			x^n = x^i * x^j, where i + j = n
			Now assume, we already has res = x^i, then in order to speed up, if j is an even number, we can decompose x^j into again: x^j = (x^2)^(j/2)
			Similarly, for odd case: x^j = (x^2)^(j/2) * x
			
		Then we can only O(lgn) operations to complete x^n which would takes O(n) operations if you only use res *= x.
		
		Be aware of some corner cases:
		n can be Integer.MIN_VALUE, 0, 1, Integer.MAX_VALUE
		x can be any floating number, negtaive, 0, positive
	*/
    public double myPow(double x, int n) {
        if(n < 0) {
            x = 1 / x;
            n = -n;
        }
        double res = 1;
        while(n != 0) {
            if(n % 2 != 0) res *= x;		// Odd case
            n /= 2;							// Will round out odd case
            x *= x;							// Note that here multiple x, not res.
            if(x == 1 || res == 0) break;  	// For speed up
        }
        return res;
    }
}