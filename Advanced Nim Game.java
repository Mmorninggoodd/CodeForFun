/*
	Given a number N. Each time you and the other player choose one number which to be added to a sum variable.
	Then the player who first let the sum variable exceeds target wins.
	
	Give you N and target, return true if you can win. Otherwise return false.

*/

/*
	Similar with repeated DNA sequences. We have to reduce space complexity of memorizations.
	
*/

public static boolean canWin(int N, int target) {
	if(((1 + N) * N) / 2 < target) return false;
	if(N >= target) return true;
	int[] choices = new int[N];
	for(int i = 1; i <= N; i++) choices[i - 1] = i;
	return canWin(choices, 0, target, new BitSet(N), new HashMap<>());
}
private static boolean canWin(int[] choices, int start, int target, BitSet chosen, Map<BitSet, Boolean> memo) {
	if(memo.containsKey(chosen)) return memo.get(chosen);
	for(int i = start; i < choices.length; i++) {
		swap(choices, start, i);
		chosen.set(i, true);
		if(choices[start] >= target || !canWin(choices, start + 1, target - choices[start], chosen, memo)) {
			chosen.set(i, false);
			memo.put((BitSet) chosen.clone(), true);
			swap(choices, start, i);
			return true;
		}
		chosen.set(i, false);
		memo.put((BitSet) chosen.clone() , false);
		swap(choices, start, i);
	}
	return false;
}
private static void swap(int[] array, int i, int j) {
	int tmp = array[i];
	array[i] = array[j];
	array[j] = tmp;
}