/*
Write a function to generate the generalized abbreviations of a word.

Example:

Given word = "word", return the following list (order does not matter):

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]

*/

/*
	Backtracking.
	Each time we can insert current char or not.
		If we insert, then we need to insert count first;
		If not, then we increase count.
	There are 2^n possible solutions.
*/
public static List<String> generateAbbreviations(String word){
	List<String> res = new ArrayList<>();
	generateAbbreviations(word, res, 0, new StringBuilder(), 0);
	return res;
}
private static void generateAbbreviations(String word, List<String> res, int count, StringBuilder sb, int index){
	int oldLen = sb.length();
	if(index == word.length()) {
		if(count > 0) sb.append(count);
		res.add(sb.toString());
	}
	else {
		generateAbbreviations(word, res, count + 1, sb, index + 1);  // not insert current char
		if(count > 0) sb.append(count);
		generateAbbreviations(word, res, 0, sb.append(word.charAt(index)), index + 1); // insert
	}
	sb.setLength(oldLen);
}