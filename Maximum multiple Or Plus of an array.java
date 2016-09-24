/*
	Given an array, you add * or + and (, ) among them such that the result is maximized.

*/

/*
	dp[i] = max value can be obtained from first i numbers (must use all numbers)
	
	dp[i + 1] = max(nums[i] * dp[i], sum(j, i) * dp[j]) for all 0 <= j <= i
	
	Boundary:
	dp[0] = 1 (no number, in case j == 0, such that sum(j, i) * dp[j] == sum(i,i))
	
	Time O(n^2) Space O(n)
*/
public static int maxMultipleOrPlus(int[] array) {
	if(array == null || array.length == 0) return 0;
	int[] dp = new int[array.length + 1];
	dp[0] = 1;
	for(int i = 0; i < array.length; i++) {
		dp[i + 1] = array[i] * dp[i];
		for(int j = i, sum = 0; j >= 0; j--) {
			sum += array[j];
			dp[i + 1] = Math.max(dp[i + 1], sum * dp[j]);
		}
	}
	return dp[array.length];
}