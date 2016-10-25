/*
给两个string的数组和一个pattern数组，判断将两个string数组分别和pattern转化后的结果是否相同。例如s1={"abc", "a", "ccc"}, s2={"bc", "aa", "d"}, pattern={1, 0}，则s1和p的转化结果是"aabc"，s2和p的转化结果也是是"aabc"，则返回true。如果pattern是{0, 1}，则转化结果分别是"abca", "bcaa"，则返回false。followup是，如果给定s1和s2，判断是否存在一个pattern使得转化结果一致。 过程中要求分析算法复杂度。


s1: ["abc", "a", "cc"]
s2: ["bc", "aa", "c"]

如果输入pattern是[1,0], 则根据s1可以导出"a"+"abc" -> "aabc"，根据s2可以导出"aa"+"bc" -> "aabc"，结果相同。
如果输入pattern是[0,1], 则根据s1可以导出"abc"+"a" -> "abca"，根据s2可以导出"bc"+"aa" -> "bcaa"，结果不同。

解法其实不难，如果你能根据s1和s2分别构建出一个基于pattern的iterator，那么你就直接比较两个iterator是否hasNext()，以及next()的值是是否相等。

followup的解法其实很直接，你先从index 0开始，然后比较对应的字符串是否一个是另一个的前缀，如果不是就去下一个index，如果是，则去短的对应的list里面找prefix匹配长的suffix，如果找到了，就比较两边各自拼接后的结果，直到形成一个完全一致的结果位置
*/

/*
	A Brute force way is to construct two strings from pattern, and check equal.
	But this takes O(n) space.
	
	So we can use four pointers to move to next char. O(1) space.
*/
public static boolean isSamePattern(String[] s1, String[] s2, int[] index) {
	int i1 = 0, i2 = 0, c1 = 0, c2 = 0;
	while(i1 < index.length && i2 < index.length && c1 < s1[index[i1]].length() && c2 < s2[index[i2]].length()) {
		if(s1[index[i1]].charAt(c1) != s2[index[i2]].charAt(c2)) return false;
		if(++c1 == s1[index[i1]].length()) {  // check if we need to advance to next string
			c1 = 0;
			i1++;
		}
		if(++c2 == s2[index[i2]].length()) {
			c2 = 0;
			i2++;
		}
	}
	return i1 == index.length && i2 == index.length;
}

/*
	Follow up
	
	Time Complexity: If we can choose same index again, then complexity would be infinite, because there are infinite
	possible solutions, like [2,1,3], [2,1,1,3], [2,1,1,1,3] ...
	s1: [a, ba, c] s2: [a, b, ac]
	If we cannot use one index again, then there are n + n*(n-1) + ... + n! which is omega(n^n) possible solutions.
	
	So I am sure that we need to use idea of iterative deepening dfs here, try to increase depth by one each time.
	Suppose we choose depth of k, then there are n * (n-1) * ... * (n-k+1) possible solutions.
*/
public static List<Integer> findPattern(String[] s1, String[] s2) {
	List<Integer> pattern1 = new ArrayList<>(), pattern2 = new ArrayList<>();
	for(int depth = 1; depth <= 2; depth++) {
		for(int startIndex = 0; startIndex < s1.length; startIndex++) {  // choose a start point
			pattern1.add(startIndex);
			pattern2.add(startIndex);
			if(findPattern(s1, s2, pattern1, pattern2, 0, 0, depth)) return pattern1;
			pattern1.clear();
			pattern2.clear();
		}
	}
	return pattern1;
}
private static boolean findPattern(String[] s1, String[] s2, List<Integer> pattern1 , List<Integer> pattern2, int c1, int c2, int depth) {
	if(pattern1.size() > depth || pattern2.size() > depth) return false;
	int i1 = pattern1.get(pattern1.size() - 1), i2 = pattern2.get(pattern2.size() - 1);
	while(c1 < s1[i1].length() && c2 < s2[i2].length()) {
		if(s1[i1].charAt(c1++) != s2[i2].charAt(c2++)) return false;
	}
	if(pattern1.size() == pattern2.size() && c1 == s1[i1].length() && c2 == s2[i2].length()) return true;  // done
	if(c1 == s1[i1].length()) {        // only need to take care of s1, because I reverse s1 and s2 in next iteration
		for(int next = 0; next < s1.length; next++) {
			pattern1.add(next);
			if(findPattern(s2, s1, pattern2, pattern1, c2, 0, depth)) return true;
			pattern1.remove(pattern1.size() - 1);
		}
	}
	return false;
}