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
public static List<Interval> weightedMeeting(Interval[] meetings) {
	Arrays.sort(meetings, (o1, o2) -> Integer.compare(o1.end, o2.end));  // sort by end time
	int[] maxWeight = new int[meetings.length];
	int[] prev = new int[meetings.length];
	for(int i = 0; i < meetings.length; i++) {
		prev[i] = findPrev(meetings, meetings[i].start, i - 1);
		maxWeight[i] = Math.max(meetings[i].weight + (prev[i] == -1 ? 0 : maxWeight[prev[i]]), i == 0 ? 0 : maxWeight[i - 1]);
		if(maxWeight[i] == (i == 0 ? 0 : maxWeight[i - 1])) {  // not chosen current meeting
			prev[i] = i;
		}
	}
	List<Interval> res = new ArrayList<>();
	backtrace(meetings, res, maxWeight, prev, meetings.length - 1);
	return res;
}
private static int findPrev(Interval[] meetings, int target, int right) {
	int left = 0;
	while(left < right) {
		int mid = (left + right + 1) >>> 1;
		if(meetings[mid].end > target) right = mid - 1;
		else left = mid;
	}
	if(meetings[left].end > target) return -1;
	return left;
}
private static void backtrace(Interval[] meetings, List<Interval> res, int[] maxWeight, int[] prev, int index) {
	if(index < 0) return;
	if(prev[index] != index) {  // chosen current meeting
		res.add(0, meetings[index]);
		backtrace(meetings, res, maxWeight, prev, prev[index]);
	}
	else {  // not chosen current meeting
		backtrace(meetings, res, maxWeight, prev, index - 1);
	}
}