/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

For example, the numbers "69", "88", and "818" are all strobogrammatic.
*/


public boolean isStrobogrammatic(String num) {
	int[] map = new int[10];
	Arrays.fill(map, -1);
	map[6] = 9; map[1] = 1; map[0] = 0; map[8] = 8; map[9] = 6;
	int left = 0, right = num.length() - 1;
	for(; left <= right; left++, right--) {
		if(map[num.charAt(left) - '0'] != (num.charAt(right) - '0')) return false;
	}
	return true;
}