/*

There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb. On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). For the ith round, you toggle every i bulb. For the nth round, you only toggle the last bulb. Find how many bulbs are on after n rounds.

Example:

Given n = 3. 

At first, the three bulbs are [off, off, off].
After first round, the three bulbs are [on, on, on].
After second round, the three bulbs are [on, off, on].
After third round, the three bulbs are [on, off, off]. 

So you should return 1, because there is only one bulb is on.

*/

/*
	Math problem.
	The ith bulb will be toggled in nth round, iff i % n == 0 (n is i's divisor)
	Then after nth round, ith bulb is on iff there exists odd number of divisors in [1:n]
	For every divisor, you can find its pair partner in [1:n] except square numbers.
	For example, 1*12, 2*6, 3*4
	
	So this question actually asks for the number of square numbers in [1:n]
	Which can be obtained from rounded of sqrt(n)
*/
public class Solution {
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }
}