/*

Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

Note:
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.
Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.

*/

/*
	Basically remove those digits start descending
	Using StringBuidler as Stack
	
	O(n) Time O(n) space
*/
public class Solution {
    public static String removeKdigits(String num, int k) {
        StringBuilder sb = new StringBuilder();
        for(char c : num.toCharArray()) {
            while(k > 0 && sb.length() != 0 && sb.charAt(sb.length() - 1) > c) {
                sb.setLength(sb.length() - 1);
                k--;
            }
            if(sb.length() != 0 || c != '0') sb.append(c);  // Only append when it is not leading zero
        }
        if(k >= sb.length()) return "0";
        sb.setLength(sb.length() - k);  // use all remaining k to remove last few digits
        return sb.toString();  
    }
}

/*
	Follow up: What if there exists negative number?
	
	Then we need to remove all digits before negative number or remove all negative numbers,
	otherwise, that would be a invalid number.
	
	If we go with method 1: remove all digits before negative number, then we want the remaining number
	to be absolute maximum.
	If we go with method 2: Then question keeps the same except new k = k - number of negative numbers.
	
	Note that in method 1, we need to choose smallest negative digit as leading digit.
	e.g. {2,1,-3,3,-2},4 should return -3 instead of -2
	
	Other test cases:
	{-2,-1,-5,-3,-2},4 return -5
	
*/

public static String removeKdigits(int[] digits, int k) {
	if(digits == null || digits.length == 0 || k >= digits.length) return "0";
	int negativeCount = 0, lastNegativeIndex = 0, smallestNegative = 0;
	for(int i = 0; i < digits.length; i++) {   // count # of negative digits
		if(digits[i] < 0) {
			negativeCount++;
			lastNegativeIndex = i;
			smallestNegative = Math.min(smallestNegative, digits[i]);
		}
	}
	if(negativeCount > k) throw new IllegalArgumentException();
	StringBuilder sb = new StringBuilder();
	if(negativeCount > 0 && lastNegativeIndex <= k) {  // case 1: remove all digits before last negative digit
		k -= lastNegativeIndex;
		sb.append(smallestNegative);
		for(int i = lastNegativeIndex + 1; i < digits.length; i++) {
			while(sb.length() != 2 && (sb.charAt(sb.length() - 1) - '0') < digits[i] && k > 0) {
				sb.setLength(sb.length() - 1);
				k--;
			}
			sb.append(digits[i]);
		}
		if(k >= sb.length() - 1) return "0";
		sb.delete(2, 2 + k);   // delete remaining chars
		return sb.toString();
	}
	
	// case 2: remove all negative digits
	k -= negativeCount;    // first decrease count, otherwise [8,5,1,-2,9] will cause negative k
	for (int digit : digits) {
		if (digit >= 0) {
			while (sb.length() != 0 && (sb.charAt(sb.length() - 1) - '0') > digit && k > 0) {
				sb.setLength(sb.length() - 1);
				k--;
			}
			if (digit != 0 || sb.length() != 0) sb.append(digit);
		}
	}
	if(k >= sb.length()) return "0";
	sb.setLength(sb.length() - k);
	return sb.toString();
}