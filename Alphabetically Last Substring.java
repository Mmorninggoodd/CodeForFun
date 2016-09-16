/*
	Given a string, return the alphabetically last substring.
	
	For alphabetical order:
		a < b
		abc < abd
		abc < abcd

	For example, 
	"ab" -> "b"
	"ba" -> "ba"
	"ababa" -> "baba"
	"acacad" -> "d"
*/

/*
	BFS 
	First find all indexes of largest char.
	Then try to advance all candidates one index each time.
	If any one of them reach the end or is smaller than others, then remove it.
	Repeat it until there is only one candidate.
*/
public static String compute(String s) {
	if(s.length() == 0) return "";
	Set<Integer> candidate = new HashSet<>();
	char highest = (char) (s.charAt(0) - 1);
	for(int i = 0; i < s.length(); i++) {  // find all candidates
		char c = s.charAt(i);
		if(highest < c) {
			candidate.clear();
			highest = c;
		}
		if(highest == c) candidate.add(i);
	}
	int advance = 0;
	while(candidate.size() > 1) {  // while there are multiple candidates, try to remove some of them
		highest = (char) ('a' - 1);
		for (Iterator<Integer> i = candidate.iterator(); i.hasNext();) { // get highest char in current index
			Integer index = i.next();
			if (index + advance < s.length() && s.charAt(index + advance) >= highest) {
				highest = s.charAt(index + advance);
			}
			else i.remove();
		}
		for (Iterator<Integer> i = candidate.iterator(); i.hasNext();) {  // remove remaining smaller candidates
			if (s.charAt(i.next() + advance) < highest) i.remove();
		}
		advance++;  // advance the index
	}
	String res = null;
	for(Integer index : candidate) {
		return s.substring(index);
	}
	return res;
}