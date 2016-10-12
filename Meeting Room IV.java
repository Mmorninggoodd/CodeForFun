/*
Weighted Meeting Schedule

Given N jobs where every job is represented by following three elements of it.

Start Time
Finish Time
Profit or Value Associated

Find the maximum profit subset of jobs such that no two jobs in the subset overlap.

*/

/*
	First sort by end time.
	Then use dp.
	
	dp[i] = max weight can gain from [0:i] meetings
	dp[i] = max(dp[j] + weight[i] (chosen i; and j is the largest index that ending time <= start time of i),
				dp[i - 1] (not chosen i))
				
	How to find j: Using binary search.
	
	If you need to output room chosen, then you need two more array to back track
	backtrack[i] = j or i - 1
	chosen[i] = true (in dp[i] we chose i) or false (not chosen)
	
	Time O(nlgn) Space O(n)

*/
class Interval{
	int start, end, weight;
	Interval(int start, int end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}
}
public static int weightedMeeting(Interval[] meetings) {
	Arrays.sort(meetings, (o1, o2) -> Integer.compare(o1.end, o2.end));  // sort by end time
	int[] maxWeights = new int[meetings.length];
	for(int i = 0; i < meetings.length; i++) {
		int j = binarySearch(meetings, i, meetings[i].start);  // find j
		maxWeights[i] = Math.max((j == -1 ? 0 : maxWeights[j]) + meetings[i].weight,
				i == 0 ? 0 : maxWeights[i - 1]);
	}
	return maxWeights[maxWeights.length - 1];
}
private static int binarySearch(Interval[] meetings, int right, int time) {
	int left = 0;
	while(left < right) {
		int mid = (left + right + 1) >>> 1;
		if(meetings[mid].end > time) right = mid - 1;
		else left = mid;
	}
	if(meetings[left].end > time) return -1;
	return left;
}