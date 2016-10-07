/*Nested List Weight Sum
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)

Example 2:
Given the list [1,[4,[6]]], return 27. (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27)

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
	A simple recursion O(n) time O(depth) space
 */
public int depthSum(List<NestedInteger> nestedList) {
	return depthSum(nestedList, 1);
}
private int depthSum(List<NestedInteger> nestedList, int depth) {
	int sum = 0;
	for(NestedInteger cur : nestedList) {
		if(cur.isInteger()) sum += cur.getInteger() * depth;
		else sum += depthSum(cur.getList(), depth + 1);
	}
	return sum;
}


/*
	Follow up 1: Implement Nested List

*/
static class NestedStruct {
	private Object content;
	NestedStruct(Object content) {
		this.content = content;
	}
	public boolean isInteger(){
		return this.content instanceof Integer;
	}
	public Integer getInteger() {
		return (Integer) this.content;
	}
	public List<NestedStruct> getList() {
		return (List<NestedStruct>) this.content;
	}
	public void add(Object subContent) {
		((List<NestedStruct>) this.content).add((NestedStruct) subContent);
	}
}
public static List<NestedStruct> construct(String s) {
	List<NestedStruct> struct = new ArrayList<>();
	for(int i = 1; i < s.length() - 1; i++) {
		char c = s.charAt(i);
		if('0' <= c && c <= '9') {
			int start = i;
			while(s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9') i++;
			struct.add(new NestedStruct(Integer.parseInt(s.substring(start, i + 1))));
		}
		else if(c == '[') {
			int start = i;
			i = s.lastIndexOf(']', s.length() - 1);
			struct.add(new NestedStruct(construct(s.substring(start, i + 1))));
		}
	}
	return struct;
}

/*

	Follow up 2: It can be represented by a string. Resolve it using constant space.

*/
public static int depthSum(String s) {
	int depth = 0, curNum = 0, sum = 0;
	for(char c : s.toCharArray()) {
		if('0' <= c && c  <= '9') curNum = 10 * curNum + (c - '0');
		else {
			sum += curNum * depth;
			curNum = 0;
			if(c == '[') depth++;
			else if(c == ']') depth--;
		}
	}
	return sum;
}