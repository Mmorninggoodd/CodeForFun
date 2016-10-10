/*


Write a program to find the nth super ugly number.

Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k. For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.

Note:
(1) 1 is a super ugly number for any given primes.
(2) The given numbers in primes are in ascending order.
(3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.

*/

/* 24 ms 85%
	Time O(kn) Space O(n)
	
	Note that there are many duplication calculations.
*/
public class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] uglyNumbers = new int[n];
        uglyNumbers[0] = 1;
        int[] indexes = new int[primes.length];
        for(int i = 1; i < n; i++) {
            int next = Integer.MAX_VALUE;
            for(int k = 0; k < primes.length; k++) {
                if(primes[k] * uglyNumbers[indexes[k]] == uglyNumbers[i - 1]) indexes[k]++;
                int cur = primes[k] * uglyNumbers[indexes[k]];
                if(cur < next) next = cur;
            }
            uglyNumbers[i] = next;
        }
        return uglyNumbers[n - 1];
    }
}

/* 18 ms 99%
	Here use one more values array to reduce duplicate calculations.

	Time O(kn) Space O(n)
*/
public class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] uglyNumbers = new int[n];
        uglyNumbers[0] = 1;
        int[] indexes = new int[primes.length];
        int[] values = new int[primes.length];
        System.arraycopy(primes, 0, values, 0, primes.length);
        for(int i = 1; i < n; i++) {
            int next = Integer.MAX_VALUE;
            for(int k = 0; k < primes.length; k++) {
                if(values[k] == uglyNumbers[i - 1]) values[k] = uglyNumbers[++indexes[k]] * primes[k];
                if(values[k] < next) next = values[k];
            }
            uglyNumbers[i] = next;
        }
        return uglyNumbers[n - 1];
    }
}

/*
	When k is large, we can use minHeap to fetch min each time.
	Just define a new class that can keep track of current prime number, value, and index.
	
	Time O(nlgk) Space O(n)
*/
public class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] uglyNumbers = new int[n];
        uglyNumbers[0] = 1;
        PriorityQueue<UglyState> pq = new PriorityQueue<UglyState>(primes.length);
        for(int prime : primes) {
            pq.offer(new UglyState(prime, prime, 0));
        }
        for(int i = 1; i < n; i++) {
            UglyState next = pq.peek();
            uglyNumbers[i] = next.value;
            while(pq.peek().value == uglyNumbers[i]) {
                next = pq.poll();
                next.value = uglyNumbers[next.index++] * next.prime;
                pq.offer(next);
            }
        }
        return uglyNumbers[n - 1];
    }
    
    
}
class UglyState implements Comparable<UglyState>{
    int value, prime, index;
    public UglyState(int value, int prime, int index) {
        this.value = value;
        this.prime = prime;
        this.index = index;
    }
    public int compareTo(UglyState o2) {
        return Integer.compare(this.value, o2.value);
    }
}