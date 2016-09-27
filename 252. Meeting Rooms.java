/*

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

For example, Given [[0, 30],[5, 10],[15, 20]], return false.

*/

/*
	Sort by start time.
	Then record current largest end.
	
	Time O(nlgn) Space O(1)
*/
public boolean canAttendMeetings(Interval[] intervals) {
	if(intervals == null || intervals.length == 0) return true;
	Arrays.sort(intervals, (o1, o2) -> Integer.compare(o1.start, o2.start));
	int end = intervals[0].end;
	for(int i = 1; i < intervals.length; i++) {
		if(intervals[i].start < end) return false;
		end = Math.max(end, intervals[i].end);
	}
	return true;
}