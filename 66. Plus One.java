/*

Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.

*/

public class Solution {
	/* 0ms
		Just be careful for corner cases.
	*/
    public int[] plusOne(int[] digits) {
        boolean carry = true;  // Initially set to true to plus one
        for(int i = digits.length - 1; i >= 0 && carry; i--) {
            if(digits[i] == 9) carry = true;
            else carry = false;
            digits[i] = (digits[i] + 1) % 10;  // avoid exceed range
        }
        if(carry) {  // 9999 + 1 cases, need a large array
            int [] newDigits = new int[digits.length + 1];
            newDigits[0] = 1;
            return newDigits;
        }
        return digits;
    }
}