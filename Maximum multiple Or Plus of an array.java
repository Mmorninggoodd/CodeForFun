/*
	Given an non-negative array, you add * or + and (, ) among them such that the result is maximized.

*/

/*
	Only works for non-negative integers.
	
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

/*
	DP version 2
	
	Works for float numbers.
	
	dp[i,j] = max value can be obtained from array[i:j]
	
	dp[i,j] = max(dp[i,k] + dp[k + 1, j], dp[i, k] * dp[k + 1, j]) for i <= k < j
	
	Filling from right to left (and bottom up)
	
	Time O(n^2) Space O(n^2)
*/
public static double maxMultipleOrPlus2(double[] array) {
	if(array == null || array.length == 0) return 0;
	int n = array.length;
	double[][] memo = new double[n][n];
	for(int i = n - 1; i >= 0; i--) {
		for(int j = i; j < n; j++) {           // start from i
			if(i == j) memo[i][j] = array[j];  // boundary
			for(int k = i; k < j; k++) {
				memo[i][j] = Math.max(memo[i][j], Math.max(memo[i][k] + memo[k+1][j], memo[i][k] + memo[k+1][j]));
			}
		}
	}
	return memo[0][n-1];
}

/*
	memorization version.
*/
public static double maxMultipleOrPlus2(double[] array) {
	if(array == null || array.length == 0) return 0;
	HashMap<Integer, Double> map = new HashMap<>();
	return maxMultipleOrPlus2(array, 0, array.length - 1, map);
}
public static double maxMultipleOrPlus2(double[] array, int i, int j, HashMap<Integer, Double> map) {
	int key = i + j * array.length;
	if(map.containsKey(key)) return map.get(key);
	if(i == j) return array[i];  // boundary
	double max = 0;
	for(int k = i; k < j; k++) {
		double left = maxMultipleOrPlus2(array, i, k, map), right = maxMultipleOrPlus2(array, k + 1, j, map);
		max = Math.max(max, Math.max(left * right, left + right));
	}
	map.put(key, max);
	return max;
}

/*
	Follow up: What if there exists negative numbers.
	Maintain max and min together.
	
	min[i][j] = min(min[i][k] + min[k+1][j], min[i][k] * max[k+1][j], max[i][k] * min[k+1][j])
	max[i][j] = max(max[i][k] + max[k+1][j], max[i][k] * max[k+1][j], min[i][k] * min[k+1][j])
*/
public static double maxMultipleOrPlus2(double[] array) {
	if(array == null || array.length == 0) return 0;
	int n = array.length;
	double[][] max = new double[n][n], min = new double[n][n];
	for(int i = n - 1; i >= 0; i--) {
		for(int j = i; j < n; j++) {           // start from i
			if(i == j) {                       // boundary
				min[i][j] = array[j];
				max[i][j] = array[j];
			}
			for(int k = i; k < j; k++) {
				min[i][j] = Math.min(min[i][j], Math.min(min[i][k] + min[k+1][j], Math.min(max[i][k] * min[k+1][j], min[i][k] * max[k+1][j])));
				max[i][j] = Math.max(max[i][j], Math.max(max[i][k] + max[k+1][j], Math.max(max[i][k] * max[k+1][j], min[i][k] * min[k+1][j])));
			}
		}
	}
	return max[0][n-1];
}

/*
	Follow up: Back trace to get the String expression of maximum.

*/

