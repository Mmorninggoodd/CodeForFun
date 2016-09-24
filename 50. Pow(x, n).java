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
        double res = 1;
		int m = n;
        while(m != 0) {
            if((m & 1) == 1) res *= x;  // odd case
			m /= 2;                     // Cannot use m >>= 1 in negative case ( (-1 >> 1) == -1)
			x *= x;
        }
        return n < 0 ? 1 / res : res;   // We don't n = abs(n), because MIN_VALUE = - MIN_VALUE
    }
}