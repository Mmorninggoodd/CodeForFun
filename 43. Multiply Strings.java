/*
Given two numbers represented as strings, return multiplication of the numbers as a string.

Note:
The numbers can be arbitrarily large and are non-negative.
Converting the input string to integer is NOT allowed.
You should NOT use internal library such as BigInteger.
*/

public class Solution {
    /*
        Time O(m*n) Space O(m+n)
        num1[i] * num2[j] -> res[i+j:i+j+1]
        https://discuss.leetcode.com/topic/30508/easiest-java-solution-with-graph-explanation
    
    */
    public String multiply(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int[] res = new int[num1.length() + num2.length()];
        for(int i = num1.length() - 1; i >= 0 ; i--) {
            for(int j = num2.length() - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                res[i+j+1] += mul;
                res[i+j] += res[i+j+1] / 10;
                res[i+j+1] %= 10;
            }
        }
        for(int num : res) if(num != 0 || sb.length() != 0) sb.append(num);
        return sb.length() == 0 ? "0" : sb.toString();
    }
}