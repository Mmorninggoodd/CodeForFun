/*
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples: 
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
For example:

add(1)
add(2)
findMedian() -> 1.5
add(3) 
findMedian() -> 2

*/

/*
	Use two heap to maintain larger half and smaller half.
	O(lgn) addNum O(1) findMedian
	O(n) space
*/
public class MedianFinder {
    PriorityQueue<Integer> large, small;
    MedianFinder(){
        this.large = new PriorityQueue<>();
        this.small = new PriorityQueue<>(Collections.reverseOrder());
    }
    // Adds a number into the data structure.
    public void addNum(int num) {   // tricky part
        this.large.offer(num);
        this.small.offer(large.poll());
        if(small.size() > large.size()) large.offer(small.poll());
    }

    // Returns the median of current data stream
    public double findMedian() {
        if(small.size() == large.size()) return ((double)small.peek() + large.peek()) / 2.0;
        return large.peek();
    }
};

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf = new MedianFinder();
// mf.addNum(1);
// mf.findMedian();