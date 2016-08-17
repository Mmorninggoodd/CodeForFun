/*

Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].

*/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
	/*
		A brute force method is O(n^2), iterate over all intervals, and compare with other intervals.
		If there is a overlapping, then merge them.
	*/

	/* 15ms 56%
		A regular method. First sort all intervals by start time, then traverse all intervals.
		Time O(nlgn) (Sorting O(nlgn) Merging O(n)), Space O(n)
		
		Note1: Traverse over list, it would better to use either one of:
			(1) foreach loop
			(2) ListIterator<Interval> iter = intervals.listIterator();
				iter.hasNext(); iter.next();
			Otherwise, using get(i) would costs O(n) time for LinkedList. (Although we don't know what's implementation of input list.)
	*/
	
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() <= 1) return intervals;
        List<Interval> res = new ArrayList<>();
        Collections.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval i1, Interval i2) {
                return Integer.compare(i1.start, i2.start);
            }
        });
        int start = intervals.get(0).start, end = intervals.get(0).end;
        for(Interval interval : intervals) {
            if(interval.start <= end) {
                end = Math.max(end, interval.end);
            }
            else {
                res.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }
        res.add(new Interval(start, end));
        return res;
    }
}