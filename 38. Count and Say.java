/*

The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.

*/

public class Solution {
	
	/*
		Very simple problem. Time O(n) space O(1) (not related with n)
		Note that access of String is faster than StriingBuilder.
	
	*/
    public String countAndSay(int n) {
        String cur = "1";
        for(int i = 1; i < n; i++){
            cur = countAndSay(cur);
        }
        return cur;
    }
    private String countAndSay(String str){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < str.length(); i++){
            int count = 1;
            char say = str.charAt(i);
            while(i + 1 < str.length() && str.charAt(i + 1) == say){
                i++;
                count++;
            }
            sb.append(count);
            sb.append(say);
        }
        return sb.toString();
    }
}