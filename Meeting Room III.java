/*
	Follow up to Meeting Room
	
	Need to print out all meetings in each room

*/

/*
	First sort meetings by start time
	Room: List<Integer> in minheap which sort it by end time of last interval
	
	Then each time try to insert a meeting into first available room
*/
import java.util.*;

class Interval {
    int start, end;
    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
public class Solution {
    public static List<List<Interval>> schedule(Interval[] intervals) {
        List<List<Interval>> res = new ArrayList<>();
        if(intervals == null || intervals.length == 0) return res;
        Arrays.sort(intervals, (o1, o2) -> Integer.compare(o1.start, o2.start));
        PriorityQueue<List<Interval>> pq = new PriorityQueue<>((o1, o2) ->  Integer.compare(o1.get(o1.size() - 1).end, o2.get(o2.size() - 1).end));
        pq.offer(new ArrayList<>());
        pq.peek().add(intervals[0]);
        for(int i = 1; i < intervals.length; i++) {
            List<Interval> firstRoom = pq.poll();   // must be polled, since you might change its value. Changing inside pq won't reorder its position.
            if(firstRoom.get(firstRoom.size() - 1).end <= intervals[i].start) {
                firstRoom.add(intervals[i]);
            }
            else {
                List<Interval> newRoom = new ArrayList<>();
                newRoom.add(intervals[i]);
                pq.offer(newRoom);
            }
            pq.offer(firstRoom);
        }
        int roomIndex = 1;
        for(List<Interval> room : pq) {
            System.out.print("Room" + roomIndex + ": ");
            for(Interval meeting : room) {
                System.out.print("[" + meeting.start + "," + meeting.end + "]");
            }
            System.out.println();
            roomIndex++;
        }
        return new ArrayList<>(pq);
    }
    public static void main(String[] args) {
        schedule(new Interval[]{new Interval(0,2), new Interval(2,4), new Interval(1,3), new Interval(3,4)});
    }
}