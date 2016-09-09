/*
You are given an integer X. You must choose two adjacent digits and replace them with the larger of these two digits.

For example, from the integer X = 233614, you can obtain:
33614 (by replacing 23 with 3);
23614 (by replacing 33 with 3 or 36 with 6);
23364 (by replacing 61 with 6 or 14 with 4);

You want to find the smallest number that can be obtained from X by replacing two adjacent digits with the larger of the two. In the above example, the smallest such number is 23364.

Write a function:

class Solution {public int solution (int X);}
that, given a positive integer X, returns the smallest number that can be obtained from X by replacing two adjacent digits with the larger of the two.

For example, given X = 233614, the function should return 23364, as explained above.

Assume that:

X is an integer within the range [10..1,000,000,000].
In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.

*/

/*
	A brute force way
*/
public static int combineAdjacent(int X) {
	assert X >= 10;  // make sure smallest won't be null at the end
	String x = String.valueOf(X), smallest = null;
	for(int i = 0; i < x.length() - 1; i++) {
		int j = i + (x.charAt(i) > x.charAt(i + 1) ? 1 : 0); // let j points to the char to be removed
		String newStr = x.substring(0, j) + x.substring(j + 1);
		if(smallest == null || smallest.compareTo(newStr) > 0) {
			smallest = newStr;
		}
	}
	return Integer.valueOf(smallest);
}