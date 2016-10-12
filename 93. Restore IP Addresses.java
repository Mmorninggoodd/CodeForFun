/*

Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
*/

public class Solution {
	
	/* 3ms 88%
		DFS more cleaner.
	*/
	public static List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        restoreIpAddresses(s, 0, res, new StringBuilder(), 0);
        return res;
    }
    private static void restoreIpAddresses(String s, int start, List<String> res, StringBuilder path, int k) {
        if(k == 4) {
            if(start == s.length()) res.add(path.toString());
            return;
        }
        int len = path.length();
        int maxIndex = (start < s.length() && s.charAt(start) == '0') ? start + 1: start + 3;
        for(int end = start + 1; end <= maxIndex && end <= s.length(); end++) {
            String sub = s.substring(start, end);
            if(Integer.parseInt(sub) > 255) continue;
            path.append(sub);
            if(k != 3) path.append('.');
            restoreIpAddresses(s, end, res, path, k + 1);
            path.setLength(len);
        }
    }
	
	
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