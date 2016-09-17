/*

Given two 1d vectors, implement an iterator to return their elements alternately.

For example, given two 1d vectors:

v1 = [1, 2]
v2 = [3, 4, 5, 6]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].

Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

Clarification for the follow up question - Update (2015-09-18): The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example, given the following input:

[1,2,3]
[4,5,6,7]
[8,9]
It should return [1,4,8,2,5,9,3,6,7].

*/

/*
	Using build-in iterators
	Can be used in k > 2 easily
*/
public class ZigzagIterator {
    private Deque<Iterator<Integer>> iters;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        iters = new ArrayDeque<>();
        if(!v1.isEmpty()) this.iters.offer(v1.listIterator());
        if(!v2.isEmpty()) this.iters.offer(v2.listIterator());
    }
    public int next() {
        Iterator<Integer> next = this.iters.poll();
        int res = next.next();
        if(next.hasNext()) this.iters.offer(next);
        return res;
    }
    public boolean hasNext() {
        return !iters.isEmpty();
    }
}