/*

Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
*/

public class Solution {
	/*
		Just be careful that n is not zero-based, so it needs n - 1 for all calculations.
	*/
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while(n != 0) {
            sb.append((char) ('A' + (n - 1) % 26));
            n = (n - 1) / 26;
        }
        return sb.reverse().toString();
    }
}