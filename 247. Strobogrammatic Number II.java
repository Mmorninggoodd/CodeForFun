/*

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

For example,
Given n = 2, return ["11","69","88","96"].

Hint:

Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.

*/

/*
	Just recursion. Note that 0110 is not a valid number, but 0 is.
*/
public List<String> findStrobogrammatic(int n) {
	return findStrobogrammatic(n, n);
}

private List<String> findStrobogrammatic(int n, int max) {
	List<String> res = new ArrayList<>();
	if(n == 0) {
		res.add("");
		return res;
	}
	if(n == 1) {
		res.add("0");
		res.add("1");
		res.add("8");
		return res;
	}
	List<String> smaller = findStrobogrammatic(n - 2, max);
	for(String s : smaller) {
		if(n != max) res.add('0' + s + '0');
		res.add('1' + s + '1');
		res.add('8' + s + '8');
		res.add('6' + s + '9');
		res.add('9' + s + '6');
	}
	return res;
}