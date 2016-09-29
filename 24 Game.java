/*
	24 game

*/


/*
	Backtracking (DFS)
	Each time try to choose two remaining numbers, and use any one operator.
	
	Then put it in start + 1, latter on, restore it.
	
	Six types of operations:
	(1) a + b
	(2) a - b 
	(3) b - a 
	(4) a * b 
	(5) a / b 
	(6) b / a
	
	Need to use double to handle case like (5 - 11/7) * 7 = 24 (only solution)
*/
public static boolean twentyFourPoint(double[] cards) {
	return twentyFourPoint(cards, 0, new StringBuilder());
}
private static boolean twentyFourPoint(double[] cards, int start, StringBuilder path) {
	if(start == cards.length - 1) {  // reach the end
		if(Math.abs(cards[cards.length - 1] - 24.0) < 0.001) {
			System.out.print(path);
			return true;
		}
		return false;
	}
	int len = path.length();
	for(int i = start; i < cards.length - 1; i++) {
		for(int j = i + 1; j < cards.length; j++) {
			swap(cards, start, i);
			swap(cards, start + 1, j);
			double tmp = cards[start + 1];   // store value

			cards[start + 1] = cards[start] + tmp;   // a + b
			if(twentyFourPoint(cards, start + 1, path.append(cards[start]).append("+").append(tmp).append(" "))) return true;
			path.setLength(len);

			cards[start + 1] = cards[start] - tmp;   // a - b
			if(twentyFourPoint(cards, start + 1, path.append(cards[start]).append("-").append(tmp).append(" "))) return true;
			path.setLength(len);

			cards[start + 1] = tmp - cards[start];    // b - a
			if(twentyFourPoint(cards, start + 1, path.append(tmp).append("-").append(cards[start]).append(" "))) return true;
			path.setLength(len);

			cards[start + 1] = cards[start] * tmp;   // a * b
			if(twentyFourPoint(cards, start + 1, path.append(cards[start]).append("*").append(tmp).append(" "))) return true;
			path.setLength(len);

			if(tmp != 0){
				cards[start + 1] = cards[start] / tmp;   // a / b
				if(twentyFourPoint(cards, start + 1, path.append(cards[start]).append("/").append(tmp).append(" "))) return true;
				path.setLength(len);
			}

			if(cards[start] != 0) {
				cards[start + 1] = tmp / cards[start];   // b / a
				if(twentyFourPoint(cards, start + 1, path.append(tmp).append("/").append(cards[start]).append(" "))) return true;
				path.setLength(len);
			}

			cards[start + 1] = tmp;   // restore value
			swap(cards, start + 1, j);
			swap(cards, start, i);
		}
	}
	return false;
}
private static void swap(double[] cards, int i, int j) {
	double tmp = cards[i];
	cards[i] = cards[j];
	cards[j] = tmp;
}