/*
A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.

Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river by landing on the last stone. Initially, the frog is on the first stone and assume the first jump must be 1 unit.

If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.

Note:

The number of stones is ≥ 2 and is < 1,100.
Each stone's position will be a non-negative integer < 231.
The first stone's position is always 0.
Example 1:

[0,1,3,5,6,8,12,17]

There are a total of 8 stones.
The first stone at the 0th unit, second stone at the 1st unit,
third stone at the 3rd unit, and so on...
The last stone at the 17th unit.

Return true. The frog can jump to the last stone by jumping 
1 unit to the 2nd stone, then 2 units to the 3rd stone, then 
2 units to the 4th stone, then 3 units to the 6th stone, 
4 units to the 7th stone, and 5 units to the 8th stone.
Example 2:

[0,1,2,3,4,8,9,11]

Return false. There is no way to jump to the last stone as 
the gap between the 5th and 6th stone is too large.

*/


/* 116 ms
	dp[i] store a list of all possible last jump step lengths
	
	for example, dp[i] = {1,3,5} means it is possible to jump to i using 1, 3, 5 jump step lengths from previous positions
	
	dp[i] add (i - j) if dp[j] contains k that i - j - 1 <= k <= i - j + 1
	
	For simplicity, we can add this in forward manner.
	
	boundary: dp[1] = {1} (ignore dp[0])
	
	If dp[n-1] has any elements, then it can be arrived
	
	Note, in order to save memory, we only store dp[i] if i has a stone. (Which can be achieved by using HashMap<Integer, HashSet<Integer>> to store (stone index, jump step lengths) pairs)
	
	Time & Space exponential
*/
public boolean canCross(int[] stones) {
	if(stones == null || stones.length == 0) return false;
	if(stones.length == 1) return true;
	int n = stones[stones.length - 1];
	if(n > (stones.length * (stones.length + 1)) / 2) return false;
	HashMap<Integer, HashSet<Integer>> dp = new HashMap<>();
	for(int stone : stones) dp.put(stone, new HashSet<>());
	if(!dp.containsKey(1)) return false;
	dp.get(1).add(1);
	for(int i = 1; i < stones.length; i++) {
		HashSet<Integer> set = dp.get(stones[i]);
		if(set.isEmpty()) continue;
		for(int step : set) {
			for(int offset = -1; offset <= 1; offset++) {
				int nextIndex = stones[i] + step + offset;
				if(step + offset <= 0 || !dp.containsKey(nextIndex)) continue;
				if(nextIndex == n) return true;
				dp.get(nextIndex).add(step + offset);
			}
		}
	}
	return !dp.get(n).isEmpty();
}
