/*
	Return max(n & x)
	where x < n, and n & x <= k

 */
 
/*
	Two cases:
	(1) n > k, then traverse one bit by one bit from most significant bit.
		If n is set while k isn't, then unset that bit.
		Otherwise, we go to next bit.
		If n become <= k, then just return n.
	(2) n <= k, then just unset the last set bit in n.
	
	Time O(lgn) or O(32) = O(1) Space O(1)
*/
public static int getMax(int n, int k) {
	int bit = 1 << 31;
	if(n > k) {
		while(n > k) {
			if((n & bit) > (k & bit)) n &= ~bit;
			bit >>>= 1;
		}
		return n;
	}
	return n & (n - 1);
}