/*
In:  Google Mail
Out: Gaegil Mloo

reorder string1's chars given in second string while keeping other chars in original place.

*/
String generate(String input) {
	return alphabetize(input, "abcdefghijklmnopqrstuvwxyz");
}
String alphabetize(String input, String chars) {
	if(input == null) return null;
	Map<Character, Integer> counts = new HashMap<>();
	for(char c : chars.toCharArray()) counts.put(c, 0);
	for(char c : input.toCharArray()) {
		if(counts.containsKey(c)) {
			counts.put(c, counts.getOrDefault(c, 0) + 1);
		}
	}

	StringBuilder sb = new StringBuilder();
	int lowest = 0;
	for(char c : input.toCharArray()) {
		if(dict.contains(c)) {
			while(lowest < chars.length()) {
				char newC = chars.charAt(lowest);
				if(counts.containsKey(c)) {
					sb.append(newC);
					int curCount = counts.get(newC);
					if(curCount == 1) counts.remove(newC);
					else counts.put(newC, curCount - 1);
					break;
				}
				else lowest++;
			}
		}
		else sb.append(c);
	}
	return sb.toString();
}