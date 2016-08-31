/*
Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

For example,

add(1); 
add(3); 
add(5);
find(4) -> true
find(7) -> false

*/

public class TwoSum {
	/*
		Use a hashmap to record occurrence of each number (only need 1 or 2)
	*/
	private Map<Integer, Integer> map = new HashMap<>();
	public void add(int number) {
		map.put(number, map.containsKey(number) ? 2 : 1);  // Only need 1 or 2
	}
 
	public boolean find(int value) {
		for (Integer i : map.keySet()) {
			int target = value - i;
			if (map.containsKey(target)) {
				if (i == target && map.get(target) < 2) {  // same number case
					continue;
				}
				return true;
			}
		}
		return false;
	}
}