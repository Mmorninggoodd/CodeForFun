/*

There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique.

*/

public class Solution {
	/*
		Two observations:
		
		1. sum(gas[i] - cost[i]) >= 0, then it can complete, and must have a solution; else it cannot complete.
		2. If it can complete, and if we divide the loop into two part: [0, i] and [i + 1, n - 1]. Then if sum([0, i]) < 0 and sum([i + 1, n - 1]) > 0, then the latter summation must be larger than the absolution value of previous summation. This implies that if we start from i + 1, then we must be able to complete the whole loop.
		
		So what I wanna do is to find the largest index such that sum([0, i]) < 0, then start index would be i + 1.
		
		Also, we can record total remaining gas to know if it satisfy observation 1.
	*/
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int total = 0, tank = 0, start = 0;
        for(int i = 0; i < gas.length; i++) {
            tank += gas[i] - cost[i];
            if(tank < 0) {
                total += tank;
                start = i + 1;
                tank = 0;
            }
        }
        return total + tank >= 0 ? start : -1;  // check if it can complete
    }
}