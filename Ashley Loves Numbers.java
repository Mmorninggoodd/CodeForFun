/* Ashley Loves Numbers
	Given lower and higher bound (inclusive), return count of all non-repeating digit numbers.
	
	For example,
	12 is non-repeating digit number, while 110 or 11 or 121 are not.
	
	lower bound: 1, higher bound: 100
	In range of 1:9, there are 9.
	In range of 10:99, there are 9*9 = 81. (zero cannot be placed in the first digit)
	In range of 100, there is zero.
	
	So totally there are 81 + 9 = 90 numbers satisfied.

	Note that this methods will be called many times.
	1 <= lower <= higher <= 10^6
*/

/*
	Brute Force Way
	Use backtracking to try every combination.
	Time O(n) Space O(lgn)
	
*/
public static int findLovelyNumber2(int lower, int higher) {
	boolean[] usedDigit = new boolean[10];
	return findLovelyNumber2(lower, higher, 0, usedDigit);
}
public static int findLovelyNumber2(int lower, int higher, int cur, boolean[] usedDigit) {
	int count = 0;
	if(cur > higher) return count;
	if(cur >= lower && cur <= higher) return 1;
	for(int digit = 0; digit <= 9; digit++) {
		if(cur == 0 && digit == 0) continue;
		if(usedDigit[digit]) continue;
		usedDigit[digit] = true;
		count += findLovelyNumber2(lower, higher, cur * 10 + digit, usedDigit);
		usedDigit[digit] = false;
	}
	return count;
}

/*
	Use number theory.
	
	First build a dp array.
	dp[0] = 1
	dp[1] = 9
	dp[2] = 9*8
	...
	dp[10] = 9*8*7*...*1 
	
	Then for simplicity I only compute higher bound. So call(higher) - call(lower - 1) is what we want.
	
	For each call, take one digit from the least significant digit each time. And use dp array.
	
	For example, higher bound: 5012
		1-999 = 9 * (dp[0] + dp[1] + dp[2]) = 738
		
		1000-4999 = (5 - 1) * dp[3] = 2016
		^
		5000-5000 = 0
		 ^
		5000-5009 = 0
		  ^
		5010-5012 = nDigitCanUse (= 2 + 1 - nUsedLeft = 1) * 1
		   ^
		Total = 2755
	
		Note nUsedLeft = number of digits used before current index. (only count those use previously available digits)
	
	
	Time O((lgn)^2) or O(1) Space O(1)
*/
public static int findLovelyNumber(int lower, int higher){
	int[] dp = new int[11];
	int count = 1;
	dp[0] = 1;  // boundary
	for(int i = 0; i < 11; i++) {
		dp[i] = count;
		count *= (9 - i);
	}
	return findLovelyNumber(higher, dp) - findLovelyNumber(lower - 1, dp);
}
private static int findLovelyNumber(int higher, int[] dp){
	if(higher <= 0) return 0;
	if(higher <= 10) return higher;
	int count = 0;
	ArrayList<Integer> digits = new ArrayList<>();
	HashSet<Integer> set = new HashSet<>();
	for(int tmp = higher; tmp > 0; tmp  /= 10) digits.add(tmp % 10);
	boolean[] isDuplicate = new boolean[10];
	for(int i = digits.size() - 1; i >= 0; i--) 
		if(i != 0 && (isDuplicate[i] || !set.add(digits.get(i)))) 
			isDuplicate[i - 1] = true;
	for(int digit = 0; digit < digits.size(); digit++) {
		int nLeftDigit = digits.size() - digit, nUsedLeft = 0, curDigit = digits.get(digit), permutation = 1;
		for(int i = digit + 1; i < digits.size(); i++) 
			if((digit == 0 && digits.get(i) <= curDigit) || (digit != 0 && digits.get(i) < curDigit)) 
				nUsedLeft++;
		for(int i = 0; i < digit; i++) permutation *= (10 - nLeftDigit - i);
		if(digit != digits.size() - 1) {  // except the most significant digit
			count += 9 * dp[digit];
			int nDigitCanUse = (digit == 0 ? (curDigit + 1 - nUsedLeft) : (curDigit - nUsedLeft));
			if(nDigitCanUse <= 0 || isDuplicate[digit]) continue;
			if(digit == 0) count += nDigitCanUse * dp[0];   // the least significant digit
			else count += nDigitCanUse * permutation;
		}
		else count += (curDigit - 1) * dp[digit];  // the most significant digit
	}
	return count;
}