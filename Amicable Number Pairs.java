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
		int sum1 = sumOfDivisors(i), sum2 = sumOfDivisors(sum1);
		if(sum1 <= upper && i < sum1 && sum2 == i) {  // i < sum1 to avoid duplicates
			pairs.add(Arrays.asList(i, sum1));
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


