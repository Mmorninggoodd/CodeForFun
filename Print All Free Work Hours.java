/*
Given a list of meeting times, return all free time slots between 7:00 and 19:00.

For example,
Given 8-11, 12:30-17:00, 15:00-17:30
output: 7-8, 11-12:30, 5:30-19:00

*/

/*
	Just sort the meetings by start/end time. 
	
	If they could be overlapped, then sort by start time, and add new interval when lastEndTime < current start time.
*/
class Interval {
	int[] start, end;
	Interval(int[] start, int[] end) {
		this.start = start;
		this.end = end;
	}
	public String toString() {
		return this.start[0] + ":" + String.format("%02d", this.start[1]) + "-"
				+ this.end[0] + ":" + String.format("%02d", this.end[1]);
	}
}
public static List<Interval> getFreeTime(Interval[] meetings) {
	Arrays.sort(meetings, (o1, o2) -> {
		if(o1.start[0] != o2.start[0]) return Integer.compare(o1.start[0], o2.start[0]);
		else return Integer.compare(o1.start[1], o2.start[1]);
	});
	int[] lastEndTime = new int[]{7,0}, endTime = new int[]{19,0};
	ArrayList<Interval> freeTimeSlots = new ArrayList<>();
	for(Interval meeting : meetings) {
		if(isLatter(meeting.start, lastEndTime)) {
			if(meeting.start[0] < 19) freeTimeSlots.add(new Interval(lastEndTime, meeting.start));
			else freeTimeSlots.add(new Interval(lastEndTime, endTime));
		}
		if(isLatter(meeting.end, lastEndTime)) lastEndTime = meeting.end;
	}
	if(isLatter(endTime, lastEndTime)) freeTimeSlots.add(new Interval(lastEndTime, endTime));
	System.out.print(freeTimeSlots);
	return freeTimeSlots;
}
private static boolean isLatter(int[] time1, int[] time2) {
	return time1[0] > time2[0] || (time1[0] == time2[0] && time1[1] > time2[1]);
}
public static void main(String[] args) {
	getFreeTime(new Interval[]{new Interval(new int[]{5,0},new int[]{9,0}),
			new Interval(new int[]{14,30}, new int[]{15,0}),
			new Interval(new int[]{15,15}, new int[]{18,59}),
			new Interval(new int[]{14,40}, new int[]{14,50}),
			new Interval(new int[]{15,10}, new int[]{18,50}),
			new Interval(new int[]{19,0}, new int[]{19,30})
	});
}