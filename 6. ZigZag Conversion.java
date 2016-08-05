The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".

public class Solution {
	
	/*
		A simple math way. O(n) time and space
	
	*/
    public String convert(String s, int numRows) {
        if(numRows <= 1) return s;
        StringBuilder sb = new StringBuilder();
        int step = 2 * numRows - 2;
        for(int row = 0; row < numRows; row++) {
            for(int i = row; i < s.length(); i += step) {
                sb.append(s.charAt(i));
                if (row != 0 && row != numRows - 1 && i + step - 2 * row < s.length()) {
                    sb.append(s.charAt(i + step - 2 * row));
                }
            }
        }
        return sb.toString();
    }
}