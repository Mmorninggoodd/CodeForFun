/*

A group of N people wishes to go across a river with only one boat, which can at most carry two persons. Therefore some sort of shuttle arrangement must be arranged in order to row the boat back and forth so that all people may cross. Each person has a different rowing speed; the speed of a couple is determined by the speed of the slower one. Your job is to determine a strategy that minimizes the time for these people to get across.


*/

/*
	Basically we need to find an arrangement that takes smallest time.
	
	Greedy O(n) time O(1) space
	
	We can analysis the following cases:
	1. Only one people need to cross river: total cost = that people
	2. Only two people: total cost = max(them)
	3. Only three people a < b < c: Optimal solution: a, c first cross, a come back, then a, b cross river.
									Total cost = a + b + c
	4. Four people a < b < c < d: There are two possible solutions:
								  a. Each time a send and go back. total cost = 2 * a + b + c + d
								  b. First a, b cross, a go back; Then c, d cross, b go back; Finally a, b cross.
								     Total cost = a + b * 3 + d
								So total cost = min(2 * a + b + c + d, a + b * 3 + d)
	Now we know that in order to get a optimal solution, it need to satisfy:
		(1) The fastest two first cross river so that they can go back.
		(2) Let slowest one cross river as least as possible.
	5. Five or more people: We will find that we need to first send fastest two, and it will be reduce
	to case of four people, but going back with faster one. So each time we can send slowest two people.
	min(b + a + d + b, d + a + c + a)  (after this a and b will still stay left side, while c and d on the other side)
	(First one: a, b cross, a back; c, d cross, b back) (Second one: a, d cross, a back, a, c cross, a back)
*/
public static int crossRiver(int[] times) {
	int total = 0, n = times.length;
	Arrays.sort(times);
	while(n > 3) {
		total += Math.min(2 * times[1] + times[0] + times[n - 1], 2 * times[0] + times[n - 1] + times[n - 2]);
		n -= 2;
	}
	if(n == 3) total += times[0] + times[1] + times[2];
	else if(n == 2) total += times[1];
	else if(n == 1) total += times[0];
	return total;
}