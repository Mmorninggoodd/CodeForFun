/*

Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
*/

public class Solution {
	/* 3ms 88%
		Just three loops
	
	*/
    public static List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        for(int first = 1; first <= 3; first++) {
            if(first >= s.length() || (first > 1 && s.charAt(0) == '0')) break;
            String sub1 = s.substring(0, first);
            if(Integer.valueOf(sub1) > 255) continue;
            for(int second = 1; second <= 3; second++) {
                if(first + second >= s.length() || (second > 1 && s.charAt(first) == '0')) break;
                String sub2 = s.substring(first, first + second);
                if(Integer.valueOf(sub2) > 255) continue;
                for(int third = 1; third <= 3; third++) {
                    int forth = s.length() - first - second - third;
                    if(forth > 3) continue;
                    if(first + second + third >= s.length() || (third > 1 && s.charAt(first + second) == '0')) break;
                    String sub3 = s.substring(first + second, first + second + third);
                    String sub4 = s.substring(first + second + third);
                    if((forth > 1 && s.charAt(first + second + third) == '0') || Integer.valueOf(sub3) > 255 || Integer.valueOf(sub4) > 255) continue; 
                    res.add(sub1 + '.' + sub2 + '.' + sub3 + '.' + sub4);
                }
            }
        }
        return res;
    }
}