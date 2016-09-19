/*

Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...

Note:
n is positive and will fit within the range of a 32-bit signed integer (n < 231).

Example 1:

Input:
3

Output:
3
Example 2:

Input:
11

Output:
0

Explanation:
The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
*/

/*
	Number of numbers:   9    90      900      9000      ...
	Range:				1-9  10-99  100-999   1000-999   ...
	Number of digits:    1     2       3         4       ...
*/
public class Solution {
    public int findNthDigit(int n) {
        long count = 9;
        int nDigit = 1;
        while((long) n > nDigit * count) {  // find the range
            n -= nDigit * count;
            nDigit++;
            count *= 10;
        }
		// number: the number we locate at; position: the digit position of this number
        int position = (n - 1) % nDigit, number = (int) (count / 9) + (n - 1) / nDigit;
        return String.valueOf(number).charAt(position) - '0';
    }
}