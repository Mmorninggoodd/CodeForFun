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

/*
	Another easier way, but slower.
*/
public boolean canCross(int[] stones) {
	if(stones == null || stones.length == 0) return false;
	HashMap<Integer, HashSet<Integer>> steps = new HashMap<>();
	for(int stone : stones) {
		steps.put(stone, new HashSet<>());
	}
	steps.get(0).add(0);
	int lastStone = stones[stones.length - 1];
	for(int stone : stones) {
		HashSet<Integer> curSteps = steps.get(stone);
		for(int step : curSteps) {
			if(stone + step - 1 <= lastStone && lastStone <= stone + step + 1) return true;
			if(step - 1 > 0 && steps.containsKey(stone + step - 1)) steps.get(stone + step - 1).add(step - 1);
			if(step > 0 && steps.containsKey(stone + step)) steps.get(stone + step).add(step);
			if(steps.containsKey(stone + step + 1)) steps.get(stone + step + 1).add(step + 1);
		}
	}
	return false;
}
	
	
/* 56 ms
	dp[i] store a list of all possible last jump step lengths
	
	for example, dp[i] = {1,3,5} means it is possible to jump to i using 1, 3, 5 jump step lengths from previous positions
	
	dp[i] add (i - j) if dp[j] contains k that i - j - 1 <= k <= i - j + 1
	
	For simplicity, we can add this in forward manner.
	
	boundary: dp[1] = {1} (ignore dp[0])
	
	If dp[n-1] has any elements, then it can be arrived
	
	Note, in order to save memory, we only store dp[i] if i has a stone. (Which can be achieved by using HashMap<Integer, List<Integer>> to store (stone index, jump step lengths) pairs)
	
	Time & Space exponential
*/
public boolean canCross(int[] stones) {
	if(stones == null || stones.length == 0) return false;
	int n = stones[stones.length - 1];
	if(stones[1] != 1 || n > (stones.length * (stones.length + 1)) / 2) return false;
	HashMap<Integer, List<Integer>> dp = new HashMap<>();
	for(int stone : stones) dp.put(stone, new ArrayList<>());
	dp.get(1).add(1);
	for(int i = 1; i < stones.length; i++) {
		List<Integer> list = dp.get(stones[i]);
		if(list.isEmpty()) continue;
		int lowestK = Integer.MAX_VALUE;
		for(int step : list) {
			for(int offset = 1; offset >= -1; offset--) {  // let ks stored in each stone in descending order
				int nextIndex = stones[i] + step + offset;
				if(step + offset <= 0 || step + offset >= lowestK || !dp.containsKey(nextIndex)) continue;
				if(nextIndex == n) return true;
				dp.get(nextIndex).add(step + offset);
				lowestK = step + offset;
			}
		}
	}
	return !dp.get(n).isEmpty();
}
