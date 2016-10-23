/*

给一个target串，判断另外source串中有没有substring满足edit distance of target and that substring <= 1

*/

/*
	O(nm) time
	Each time take out substring length of m from source, and compute edit distance in O(m).
	
	Be careful about case that source string is shorter than target, such as "ab" "abc"
*/
public static boolean existsSubOneEdit(String source, String target) {
	if(source.length() < target.length()) return oneEdit(source, target);
	for(int start = 0; start <= source.length() - target.length(); start++) {
		if(oneEdit(source.substring(start, start + target.length()), target)) return true;
	}
	return false;
}
private static boolean oneEdit(String s1, String s2) {
	if(Math.abs(s1.length() - s2.length()) > 1) return false;
	for(int i = 0; i < s1.length() && i < s2.length(); i++) {
		if(s1.charAt(i) != s2.charAt(i)) {
			if(s1.length() == s2.length()) return s1.substring(i + 1).equals(s2.substring(i + 1));
			if(s1.length() > s2.length()) return s1.substring(i + 1).equals(s2.substring(i));
			return s2.substring(i + 1).equals(s1.substring(i));
		}
	}
	return true;
}