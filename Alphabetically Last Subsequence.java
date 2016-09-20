/*
	Given a string and a integer k, return a alphabetical last subsequence of length k.
	
	For example, "apple" , k = 3 -> "ale"

*/

/*
	Pass k scans, each time search one smallest character.
	Time O(k*n) Space O(k)
*/
public static String minSubstring(String str, int k) {
	int i = 0;
	StringBuilder sb = new StringBuilder();
	for(; k > 0; k--) {
		char c = 128;
		for(int j = i; j <= str.length() - k; j++) {
			if(c > str.charAt(i)) {
				c = str.charAt(i);
				i = j + 1;
			}
		}
		sb.append(c);
	}
	return sb.toString();
}

/*
	ArrayList[26] indexes to record all indexes of all characters
	Then iterate over this indexes to find candidates for each position.
	
	O(n) time O(n) space
*/
public static String minSubstring(String str, int k) {
	
}