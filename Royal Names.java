/*
	Royal names
	An ordinal number is a word representing rank or sequential order.

	Here we give (Name Roman_Number) strings, you need to sort names lexicographically. 
	When names are the same, sort names by ascending ordinal numbers.
	
	Constraints: 1 <= Roman number <= 50.
	Each name[i] will be a single string composed of 2 space-separated values: firstName and ordinal.
*/

public static String[] getSortedList(String[] names) {
	final HashMap<Character, Integer> map = new HashMap<Character, Integer>();
	map.put('L', 50);
	map.put('X', 10);
	map.put('V', 5);
	map.put('I', 1);
	Arrays.sort(names, new Comparator<String>(){
		@Override
		public int compare(String s1, String s2) {
			String[] s1s = s1.split(" ");
			String[] s2s = s2.split(" ");
			if(!s1s[0].equals(s2s[0])) return s1s[0].compareTo(s2s[0]);  // first compare firstName
			return Integer.compare(romanToInt(s1s[1], map), romanToInt(s2s[1], map));  // Then ordinal
		}
	});
	return names;
}
// convert roman -> int
private static int romanToInt(String s, Map<Character, Integer> map){
	int ans = 0;
	for(int i = 0; i < s.length(); i++){
		ans += map.get(s.charAt(i));
		if(i > 0 && map.get(s.charAt(i - 1)) < map.get(s.charAt(i))){
			ans -= 2 * map.get(s.charAt(i - 1));
		}
	}
	return ans;
}