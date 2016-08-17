/*

Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].

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
		For this problem, many people proposed so-called O(lgn) solutions. However, none of them is true O(lgn), since get() is O(n) for LinkedList or remove() is O(n) for ArrayList (and LinkedList if you use remove(i)).
		
		A relative short Binary Search solution can be found: https://discuss.leetcode.com/topic/41004/my-binary-search-approach-implementation-2ms
		
		Skip list might be a potential choice, whose search and delete operations are average O(lgn) worst O(n), however, I cannot find a existing Java provided class for it.
		
		Another possible solution is using a hashmap to map indexes to nodes of LinkedList, so that we can achieve O(1) access and O(1) modify operation. However, building hashmap takes O(n) and constant factor of hash operation is much larger. This solution is only feasible when insertion will be called multiple time.
	*/
	
	/* 2ms 95.54%
		A simple O(n) solution.
	
	*/
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        if (intervals.size() == 0 || (intervals.get(0).start > newInterval.start && intervals.get(intervals.size()-1).end < newInterval.end)) {
            res.add(newInterval);
            return res;
        }
        int index = 0;
        for(Interval interval : intervals) {  // foreach loop is preferred for List
            if(interval.start <= newInterval.end && interval.end >= newInterval.start) {
                newInterval.start = Math.min(newInterval.start, interval.start);
                newInterval.end = Math.max(newInterval.end, interval.end);
            }
            else if(newInterval.end < interval.start) res.add(interval);
            else {
                res.add(interval);
                index++;
            }
        }
        res.add(index, newInterval);
        return res;
    }
}