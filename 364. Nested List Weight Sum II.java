/*Nested List Weight Sum II
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

Example 1:
Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)

Example 2:
Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17) 

*/
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer,
 *     // rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds,
 *     // if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds,
 *     // if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
 /*
 
	Instead of multiplying by depth, add integers multiple times (by going level by level and adding the unweighted sum to the weighted sum after each level).

	But this will fail in case [1,2,[]] -> 3 while correct answer might be 6
 */
public static int depthReverseSum(List<NestedStruct> nested) {
	int sum = 0, unweighted = 0;
	Deque<NestedStruct> curLevel = new ArrayDeque<>();
	curLevel.addAll(nested);
	while(!curLevel.isEmpty()) {
		Deque<NestedStruct> nextLevel = new ArrayDeque<>();
		for(NestedStruct cur : curLevel) {
			if(cur.isInteger()) unweighted += cur.getInteger();
			else nextLevel.addAll(cur.getList());
		}
		curLevel = nextLevel;
		sum += unweighted;
	}
	return sum;
}


/*

	Follow up: It can be represented by a string. Resolve it using constant space.

	Two pass: one pass to get deepest level, another to get the sum
*/
public static int depthReverseSum(String s) {
	int curNum = 0, sum = 0, maxLevel = 0, curLevel = 0;
	for(char c : s.toCharArray()) {
		if(c == '[') {
			curLevel++;
			maxLevel = Math.max(maxLevel, curLevel);
		}
		else if(c == ']') curLevel--;
	}
	curLevel = 0;
	for(char c : s.toCharArray()) {
		if('0' <= c && c  <= '9') curNum = 10 * curNum + (c - '0');
		else {
			sum += curNum * (maxLevel - curLevel + 1);
			curNum = 0;
			if(c == '[') curLevel++;
			else if(c == ']') curLevel--;
		}
	}
	return sum;
}