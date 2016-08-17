/*

Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".

*/

public class Solution {
	/* 4ms 63%
		Similar with #66 Plus One
		Just keep in mind there three components:
			1. carry 2. current bit of a 3. current bit of b
	*/

    public String addBinary(String a, String b) {
        boolean carry = false;
        StringBuilder sb = new StringBuilder();
        for(int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = (carry ? 1 : 0) + (i >= 0 && a.charAt(i) == '1' ? 1 : 0) + (j >= 0 && b.charAt(j) == '1' ? 1 : 0);  // Three components
            sb.append(sum % 2);
            carry = sum > 1;
        }
        if(carry) sb.append('1');
        return sb.reverse().toString();
    }
}