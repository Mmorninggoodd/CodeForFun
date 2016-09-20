/*
Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

For example:

Given s = "aabb", return ["abba", "baab"].

Given s = "abc", return [].

Hint:

If a palindromic permutation exists, we just need to generate the first half of the string.
To generate all distinct permutations of a (half of) string, use a similar approach from: Permutations II or Next Permutation.

*/

/*
	A typical backtracking.
	First check whether string is valid, then reuse the counts to generate.
	
	Can use HashMap<Character, Integer> as well
*/
public static List<String> generatePalindromes(String s) {
	if(s.length() == 0) return new ArrayList<>();
	int[] counts = new int[256];
	for(char c : s.toCharArray()) counts[c]++;
	int odd = 0;
	String center = "";
	for(int i = 0; i < counts.length; i++) {  // count occurrence
		if(counts[i] % 2 == 1) {
			odd++;
			center = String.valueOf((char) i);
		}
		counts[i] /= 2;
	}
	List<String> res = new ArrayList<>();
	if(odd <= 1) generatePalindromes(counts, res, new StringBuilder(), center);  // only generate when it is valid
	return res;
}
private static void generatePalindromes(int[] counts, List<String> res, StringBuilder sb, String center) {
	boolean isDone = true;
	for(int i = 0 ; i < counts.length; i++) {  // try to take one char each time
		if(counts[i] == 0) continue;
		isDone = false;
		counts[i]--;
		sb.append((char) i);
		generatePalindromes(counts, res, sb, center);
		sb.setLength(sb.length() - 1);
		counts[i]++;
	}
	if(isDone) {  // no char can be chosen
		res.add(sb.toString() + center + sb.reverse().toString());
	}
}