/*
	Given two strings, output a new string.
	"google" + "algorithm" -> "lggooe"
	
	Try to find the transformation rule and implement this function.
*/

/*
	Basically reorder the first string according to the order of character in string2.
	For example, 'l' is the first character in string2 which is also a character in string1,
	so we first append it. Then 'g' , 'o' and remaining characters keep original order.
*/

public static String transform(String str1, String str2) {
	StringBuilder sb = new StringBuilder();
	HashMap<Character, Integer> map = new HashMap<>();
	for(char c : str1.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);
	for(char c : str2.toCharArray()) {
		if(map.containsKey(c)) {
			int n = map.get(c);
			for(int i = 0; i < n; i++) sb.append(c);
			map.remove(c);
		}
	}
	for(char c : str1.toCharArray()) {  // remaining chars keep original order
		if(map.containsKey(c)) sb.append(c);
	}
	return sb.toString();
}