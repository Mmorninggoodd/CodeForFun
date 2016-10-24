/*
	Similar to 290. Word Pattern.
	But here given a pattern and a dictionary, find all satisfied words.
	
	Here we only consider char maps to char.

	For example, pattern "abba" can match "cddc" or "rggr", cannot match "aaaa" or "avvv"
	
	Follow up: If we need to query many times without changing dictionary, how to optimize it?
*/
public static boolean wordPatternMatch(String pattern, String str) {
	String[] map = new String[26]; // mapping of characters 'a' - 'z'
	HashSet<String> set = new HashSet<>(); // mapped result of 'a' - 'z'
	return wordPatternMatch(pattern, str, map, set, 0, 0);
}
private static boolean wordPatternMatch(String pattern, String str, String[] map, HashSet<String> set, int start, int startP) {
	int endP = pattern.length() - 1, end = str.length() - 1;
	if(startP == endP + 1 && start == end + 1) return true; // both pattern and str are exhausted
	if(startP > endP || start > end) return false; // either of pattern or str is exhausted

	char ch = pattern.charAt(startP);
	String matched = map[ch-'a'];
	if(matched != null) { // ch is already mapped, then continue
		return str.startsWith(matched, start) // substring equals previously mapped string
				&& wordPatternMatch(pattern, str, map, set, start+matched.length(), startP + 1); // moving forward
	}
	else {
		int endPoint = end;
		for(int i = endP; i > startP; i--) {
			endPoint -= map[pattern.charAt(i)-'a'] == null ? 1 : map[pattern.charAt(i)-'a'].length();
		}
		for(int i = start; i <= endPoint; i++) { // try every possible mapping, from 1 character to the end
			matched = str.substring(start, i+1);
			if(set.contains(matched)) continue; // different pattern cannot map to same substring

			map[ch-'a'] = matched; // move forward, add corresponding mapping and set content
			set.add(matched);
			if(wordPatternMatch(pattern, str, map, set, i + 1, startP + 1)) return true;
			map[ch-'a'] = null;
			set.remove(matched);
		}
	}
	return false; // exhausted
}