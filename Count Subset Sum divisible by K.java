/*
	Count the number of continuous subset whose sum is divisible by k
*/

/*
	Calculate counts of modular remainder of prefix sums.
	Then use i * (i - 1) / 2 to get the counts.
	
	Time O(n) Space O(k)
*/
public static int numSubsetSumDivisibleByK(String s, int k) {
	int remainder = 0, total = 0;
	int[] counts = new int[k];
	counts[0] = 1;
	for(char c : s.toCharArray()) {
		remainder = (remainder * 10 + (c - '0')) % k;
		counts[remainder]++;
	}
	for(int count : counts) total += (count * (count - 1)) / 2;
	return total;
}