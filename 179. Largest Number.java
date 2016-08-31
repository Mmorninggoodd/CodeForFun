/*

Given a list of non negative integers, arrange them such that they form the largest number.

For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

Note: The result may be very large, so you need to return a string instead of an integer.

*/

public class Solution {
	/*
		Construct a new comparator to sort the numbers.
	*/
    public String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for(int i = 0; i < nums.length; i++) strs[i] = String.valueOf(nums[i]); 
        Arrays.sort(strs, new Comparator<String>(){
            @Override
            public int compare(String a, String b) {
                return (b + a).compareTo(a + b);
            }
        });
		//  Arrays.sort(strs, (s1, s2) -> ((s2 + s1).compareTo(s1 + s2)));
        if(strs[0].charAt(0) == '0') return "0";  // avoid case ["0","0"]
        StringBuilder sb = new StringBuilder();
        for(String str : strs) sb.append(str);
        return sb.toString();
    }
}