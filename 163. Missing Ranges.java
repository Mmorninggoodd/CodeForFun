/*

Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.

For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].

*/

/*
	move lower bound each time meet a new number
	finally check upper bound
*/
public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
	List<String> res = new ArrayList<>();
	for(int num : nums) {
		if(lower != num) res.add(toRangeString(lower, num - 1));  // check lower bound
		lower = num + 1;
	}
	if(lower != upper) res.add(toRangeString(lower, upper));  // check upper bound
	return res;
}
private static String toRangeString(int start, int end) {  // inclusive
	if(start == end) return String.valueOf(start);
	return String.valueOf(start) + "->" + String.valueOf(end);
}