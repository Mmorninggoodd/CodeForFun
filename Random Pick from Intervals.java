/*

	Given a list of interval, using reservoir sampling to pick one random number.

*/

/*
	Weighted Reservoir Sampling
	Time O(k) where k is number of intervals
*/
public static int randomPick(List<int[]> intervals) {
	int chosen = 0, count = 0;
	Random random = new Random();
	for(int[] interval : intervals) {
		int r = random.nextInt(count + interval[1] - interval[0] + 1);   // generate from [0, counts until current interval)
		if(r >= count) chosen = interval[0] + r - count;    // only update when r is outside of [0, counts until last interval)
		count += interval[1] - interval[0] + 1;             // add current interval count
	}
	return chosen;
}