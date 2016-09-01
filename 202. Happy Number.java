/*

Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example: 19 is a happy number

12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1

*/

public class Solution {
	/*
		Method 1: Use a HashSet to record intermediate numbers.
		O(loop) space O(loop) time
	*/
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        while(n != 1) {
            if(!set.add(n)) return false;
            n = nextNum(n);
        }
        return true;
    }
    private int nextNum(int n) {
        int res = 0;
        while(n != 0) {
            int curDigit = (n % 10);
            res += (curDigit * curDigit);
            n /= 10;
        }
        return res;
    }
	
	/*
		Method 2: Use cycle-detection algorithm (slow-fast pointers)
		O(1) space and O(loop) time
	*/
	public boolean isHappy(int n) {
        int slow = n, fast = n;
        while(true){
            slow = nextNum(slow);
            fast = nextNum(fast);
            fast = nextNum(fast);
            if(fast == 1) break;
            if(fast == slow) return false;
        }
        return true;
    }
}