/*


Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

For example, Given [[0, 30],[5, 10],[15, 20]], return 2.

*/


/*
	Bruce Force:
		Compare every start and end time one by one. Then maximum number of overlapping 
		is the number of rooms needed.
	Time O(n^2) Space O(1)
*/

/*
	Greedy idea: Each time choose earliest start meeting to an available room.
	
	Sort intervals by start time.
	Using a minheap to store occupied time of each room. (sort by end time)
	Each time try to insert a meeting in a meeting room that has earliest end time.
	If we cannot, then add one more room.
	
	Prove:
		Assume there is another solution S' that use less rooms. 
		1. Each time insert into an available room:
		Suppose when we need to insert a new interval into current rooms, 
		and S' doesn't insert it into a available room. Then it has to 
		open a new room while our solution S don't need to. So there is a contradiction.
		
		2. Each time choose earliest start room:
		Suppose S' doesn't insert a earliest start interval to an available room, then 
		if we safely replace this interval with an earliest start interval. Since in S',
		this earliest start interval can be inserted latter without adding more room than S.
		So both solution use same rooms, which is a contradiction.
	
	Time O(nlgn) Space O(n)
*/
public int minMeetingRooms(Interval[] intervals) {
	if(intervals == null || intervals.length == 0) return 0;
	Arrays.sort(intervals, (o1, o2) -> Integer.compare(o1.start, o2.start));
	PriorityQueue<Interval> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.end, o2.end));
	pq.offer(intervals[0]);
	for(int i = 1; i < intervals.length; i++) {
		if(intervals[i].start >= pq.peek().end) pq.peek().end = intervals[i].end;  // add to this meeting room
		else pq.offer(intervals[i]);    // add one more meeting room
	}
	return pq.size();
}