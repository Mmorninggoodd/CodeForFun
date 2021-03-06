/*

Given a positive integer n and you can do operations as follow:

If n is even, replace n with n/2.
If n is odd, you can replace n with either n + 1 or n - 1.
What is the minimum number of replacements needed for n to become 1?

Example 1:

Input:
8

Output:
3

Explanation:
8 -> 4 -> 2 -> 1
Example 2:

Input:
7

Output:
4

Explanation:
7 -> 8 -> 4 -> 2 -> 1
or
7 -> 6 -> 3 -> 2 -> 1

*/

/*
	When n is even: right shift
	When n is odd: last two digits are 11: n++ (must > 3, otherwise it won't benefit from increment)
				   last two digits are 01: n--

	Time O(lgn)
*/
public class Solution {
    public int integerReplacement(int n) {
        int count = 0;
        while(n != 1) {
            if(n % 2 == 0) n >>>= 1;
            else  if(n > 3 && ((n >>> 1) & 1) == 1) n++;
            else n--;
            count++;
        }
        return count;
    }
}
