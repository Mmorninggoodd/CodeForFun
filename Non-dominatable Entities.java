/*
Given are pairs of integers (a1,b1),...,(an,bn). Pair i is "dominated" by pair j if ai < aj and bi < bj.

Count the number of non-dominatable numbers in the list.

*/

/*
	First sort according to ai, if tie, compare bi in descending order. O(nlgn)
	Then traverse the list in descending order, and record maxA and maxB all the time. 
	Each time you meet a == maxA or b >= maxB, increase count. O(n)
	
	Time O(nlgn) No extra Space
*/
public static int countNonDominate(int[][] input) {
	Arrays.sort(input, new Comparator<int[]>() {
		@Override
		public int compare(int[] o1, int[] o2) {
			if(o1[0] != o2[0]) return Integer.compare(o2[0], o1[0]);  // First compare the first digit
			return Integer.compare(o2[1], o1[1]);
		}
	});
	int count = 1, maxA = input[0][0], maxB = input[0][1];
	for(int i = 1; i < input.length; i++) {
		if(maxA == input[i][0] || input[i][1] >= maxB) count++;
		maxB = Math.max(maxB, input[i][1]);
	}
	return count;
}