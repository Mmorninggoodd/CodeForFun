/*
	Amicable Number Pairs

	Amicable numbers are two different numbers so related that the sum of the proper divisors of each is equal to the other number. (A proper divisor of a number is a positive factor of that number other than the number itself.)
	
	For example, (220, 284) is the first amicable numbers.
	1+2+4+5+10+11+20+22+44+55+110 = 284
	1+2+4+71+142 = 220
	
	Find all amicable number pairs with a given upper limit
*/


/*
	The key is to get sum of proper divisors of them, and then find if there exists a pair.
	
	Calculate sum of proper divisors of them one by one. Each number takes O(n^0.5) time. Then totally takes O(n^1.5) to calculate all sums. In the end using one pass to check if there exists any valid pair. i == sums[sums[i]]
	
	Total time O(n^1.5) space O(1)

*/

public static List<List<Integer>> amicableNumbers(int upper) {
	List<List<Integer>> pairs = new ArrayList<>();
	for(int i = 200; i <= upper; i++) {
		int sum1 = sumOfDivisors(i);
		if(sum1 <= upper && i < sum1) {  // i < sum1 to avoid duplicates
			int sum2 = sumOfDivisors(sum1);
			if(sum2 == i) pairs.add(Arrays.asList(i, sum1));
		}
	}
	return pairs;
}
private static int sumOfDivisors(int num) {  // n^0.5 to get sum
	int sum = 1;
	for(int i = 2; i * i <= num; i++) {
		if(num % i == 0) {
			sum += i;
			if(i * i != num) sum += num / i;
		}
	}
	return sum;
}


/*
	Method 2: First generate sums of numbers in [1:n] in O(nlgn)
			  Then find any valid pairs in O(n)
			  
	How to generate sums:
		1. First add 1 to each cell (except 1)  takes time O(n)
		2. Then add 2 to 2*k cells (except 2)   takes time O(n/2)
		3. Then add 3 to 3*k cells (except 3)   takes time O(n/3)
		Until n/2  takes time O(1)
    So overall time complexity is O(n + n/2 + n/3 + n/4 + ... + 1) = O(n * (1 + 1/2 + 1/3 + ... + 1/n) = O(nlgn)
    Space O(n)

*/
public static List<List<Integer>> amicableNumbers(int upper) {
	int[] dp = new int[upper + 1];
	for(int i = 1; i <= upper / 2; i++) {
		for(int j = 2; i * j <= upper; j++) {
			dp[i * j] += i;
		}
	}
	List<List<Integer>> pairs = new ArrayList<>();
	for(int i = 1; i <= upper; i++) {
		if(dp[i] <= upper && dp[dp[i]] == i && i < dp[i]) {
			pairs.add(Arrays.asList(i, dp[i]));
		}
	}
	return pairs;
}
